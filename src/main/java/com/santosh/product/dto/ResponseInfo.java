package com.santosh.product.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id", "productName", "productQuantity", "productPrice"})
public class ResponseInfo {
	
	private long id;
	private int productQuantity;
	private double productPrice;
	private String productName;
	

}
