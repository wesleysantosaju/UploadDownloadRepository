package com.example.uploaddownload.model;

public class UploadFileResponse {
	//atributos
	private String fileType;
	private String fileName;
	private long size;
	private String fileDownloadUri;
	
	// getters e setters
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}
	
	// constructor
	public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
		super();
		this.fileType = fileType;
		this.fileName = fileName;
		this.size = size;
		this.fileDownloadUri = fileDownloadUri;
	}



	

}
