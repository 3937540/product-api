package com.santosh.product.service;

import com.santosh.product.dto.RequestDTO;
import org.springframework.stereotype.Service;

import com.santosh.product.dto.ResponseInfo;
import com.santosh.product.entity.ProductEntity;

@Service
public interface AdditionalProductService {

	ResponseInfo getProduct(Long productId, String productNm);

	ProductEntity getProductByName(String productNm);

	ResponseInfo getProductById(Long productId);

	ResponseInfo saveProduct(RequestDTO product);

}
