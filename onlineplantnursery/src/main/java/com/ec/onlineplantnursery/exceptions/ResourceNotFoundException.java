package com.ec.onlineplantnursery.exceptions;

public class ResourceNotFoundException extends Exception {

	private String commonName;
	
	public ResourceNotFoundException(String commonName) {
		super();
		this.commonName = commonName;
	}


	public ResourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public ResourceNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}


	public String getCommonName() {
		return commonName;
	}


	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	


}
