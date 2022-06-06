package com.example.uploaddownload.exception;

public class FileStorageException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	//constructor
	public FileStorageException(String message) {
		super(message);
	}
	
	//novo construtor para observar a causa da exceção
	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}

}
