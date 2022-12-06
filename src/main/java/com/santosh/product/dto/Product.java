package com.santosh.product.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Product implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private String productName;
	private int productQuantity;
	private double productPrice;
}
