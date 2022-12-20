package com.santosh.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.santosh.product.dto.ProductDTO;
import com.santosh.product.dto.RequestDTO;
import com.santosh.product.dto.ResponseInfo;
import com.santosh.product.entity.ProductEntity;

@Service
public interface ProductService {
	
	ResponseInfo saveProduct(RequestDTO product);

	List<ResponseInfo> getAllProducts();

	ResponseInfo getProduct(Long productId);

	ResponseInfo updateProduct(ProductDTO productDTO);

	void deleteProduct(Long id);

	ProductEntity updateProductByField(Long id, Map<String, Object> fields);

}
