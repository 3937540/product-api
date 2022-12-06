package com.santosh.product.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ProductRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Product> products;
	

}
