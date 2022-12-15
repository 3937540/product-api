package com.santosh.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santosh.product.dto.ResponseInfo;
import com.santosh.product.entity.ProductEntity;
import com.santosh.product.repository.ProductRepository;
import com.santosh.product.util.ProductUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AdditionalProductServiceImpl implements AdditionalProductService{
	

	private static final Logger LOGGER = LoggerFactory.getLogger(AdditionalProductServiceImpl.class);
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ProductUtil utility;
	
	@Override
	public ResponseInfo getProduct(Long productId, String productNm) {
		
		LOGGER.info("AdditionalProductServiceImpl:: Product ID: {}, Product NM: {}", productId, productNm);
		ProductEntity entity = repository.findProductByProductIdAndProductName(productId, productNm);
		return utility.createResponseInfo(entity);
	}

}
