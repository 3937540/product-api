package com.santosh.product.exception;

import java.util.Date;
import java.util.List;

import lombok.Data;


@Data
public class ErrorDetails {

	private Date timestamp;
	private String message;
	private List<String> details;
	
	public ErrorDetails(Date timestamp, String message, List<String> details) {
		
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
}
