package com.santosh.product.service;

import java.util.List;

import com.santosh.product.dto.ProductsResponse;
import com.santosh.product.dto.RequestDTO;
import com.santosh.product.dto.ResponseInfo;

public interface ProductService {
	
	ResponseInfo saveProduct(RequestDTO product);

	List<ResponseInfo> getAllProducts();

}
