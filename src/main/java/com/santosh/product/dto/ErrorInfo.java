package com.santosh.product.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"errorCode", "errorMessage"})
public class ErrorInfo {

	private String errorCode;
	private String errorMessage;
}
