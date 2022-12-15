package com.santosh.product.service;

import org.springframework.stereotype.Service;

import com.santosh.product.dto.ResponseInfo;

@Service
public interface AdditionalProductService {

	ResponseInfo getProduct(Long productId, String productNm);
	
	

}
