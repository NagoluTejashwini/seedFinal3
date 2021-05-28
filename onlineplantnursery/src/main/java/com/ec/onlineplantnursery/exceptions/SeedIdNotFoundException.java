package com.ec.onlineplantnursery.exceptions;

public class SeedIdNotFoundException extends Exception {

	private Integer seedId;
	
	public SeedIdNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	
	public SeedIdNotFoundException(Integer seedId) {
		super();
		this.seedId = seedId;
	}


	public SeedIdNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public SeedIdNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SeedIdNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public Integer getSeedId() {
		return seedId;
	}

	public void setSeedId(Integer seedId) {
		this.seedId = seedId;
	}
	
	

}
