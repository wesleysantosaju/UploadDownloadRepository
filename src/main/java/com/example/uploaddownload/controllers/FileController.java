package com.example.uploaddownload.controllers;

import java.io.IOException;
//import java.net.http.HttpHeaders;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.uploaddownload.model.UploadFileResponse;
import com.example.uploaddownload.service.FileStorageService;



@RestController
public class FileController {

	// criar uma propriedade para servir de log de manipulação a partir do controller
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileStorageService fileStorageService;

	// uso da annotation @PostMapping
	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file){
		String fileName = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFile/")
				.path(fileName)
				.toUriString();
		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
		}

	//método para "uppar" 2 ou mais arquivos
	@PostMapping("/uploadMultipleFiles")
	public List<UploadFileResponse> uploadMultiplesFiles(@RequestParam("files")MultipartFile[] files){
		return Arrays.asList(files)
				.stream()
				.map(file -> uploadFile(file))
				.collect(Collectors.toList());
	}
	// criar a estrutura lógica que possibilitará o download de arquivos
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(
			@PathVariable String fileName, HttpServletRequest request){
		// criar uma propriedade para acessar o método que executa os downs
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// tentativa de determinar o tipo de conteudo do arquivo
		String contentType = null;
		//bloco try/catch
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		}catch(IOException ex) {
			logger.info("Não foi possivel determinar o tipo do conteudo do arquivo.");
		}

		// verficar se o tipo de conteudo não pode ser verificada porque a variavel não conseguiu acessa-lo
		if(contentType == null) {
			contentType = "application/octet-stream";
		}

		// implementação da declaração
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
				resource.getFilename() + "\"")
				.body(resource);
	}
}
