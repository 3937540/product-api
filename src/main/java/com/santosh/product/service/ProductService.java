package com.santosh.product.service;

import com.santosh.product.dto.Product;
import com.santosh.product.entity.ProductEntity;

public interface ProductService {
	
	ProductEntity saveProduct(Product product);

}
