package com.santosh.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santosh.product.dto.Product;
import com.santosh.product.dto.ProductRequest;
import com.santosh.product.dto.ProductResponse;
import com.santosh.product.dto.SingleProductResponse;
import com.santosh.product.entity.ProductEntity;
import com.santosh.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/Products")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	//Add products into DB.
	@PostMapping
	public ProductEntity addProducts(@RequestBody Product product) {
		
		ProductEntity response = null;
		if(null != product) {
			
			response = service.saveProduct(product);
			log.info("Product saved into DB Successfully.");
			return response;
		}else {
			log.info("Invalid Request.");
			return null;
		}
		
		
	}
	
	//Get all products from DB.
	@GetMapping
	public ProductResponse getProducts() {
		
		return null;
	}
	
	//Get any particular product from DB.
	@GetMapping(path = "${product.id}")
	public ProductResponse getProduct() {
		
		return null;
	}
	
	//Update any particular product in DB.
	@PutMapping(path = "${product.id}")
	public ProductResponse updateProduct() {
		
		return null;
	}
	
	//Delete a product from the DB.
	@DeleteMapping(path = "${product.id}")
	public ProductResponse deleteProduct() {
		
		return null;
	}

}
