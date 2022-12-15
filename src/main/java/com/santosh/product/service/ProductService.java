package com.santosh.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santosh.product.dto.ProductDTO;
import com.santosh.product.dto.RequestDTO;
import com.santosh.product.dto.ResponseInfo;

@Service
public interface ProductService {
	
	ResponseInfo saveProduct(RequestDTO product);

	List<ResponseInfo> getAllProducts();

	ResponseInfo getProduct(Long productId);

	ResponseInfo updateProduct(ProductDTO productDTO);

	void deleteProduct(Long id);

}
