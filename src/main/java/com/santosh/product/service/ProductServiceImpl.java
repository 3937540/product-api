package com.santosh.product.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santosh.product.dto.ProductsResponse;
import com.santosh.product.dto.RequestDTO;
import com.santosh.product.dto.ResponseInfo;
import com.santosh.product.entity.ProductEntity;
import com.santosh.product.repository.ProductRepository;
import com.santosh.product.util.ProductUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ProductUtil utility;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public ResponseInfo saveProduct(RequestDTO product) {

		LOGGER.info("Saving the product into DB.");
		ProductEntity productEntity = utility.createProductEntity(product, new ProductEntity());
		productEntity = repository.save(productEntity);
		LOGGER.info("Product saved into DB successfully.");
		return utility.createResponseInfo(productEntity);

	}

	@Override
	public List<ResponseInfo> getAllProducts() {
		
		List<ResponseInfo> response = null;
		LOGGER.info("Fetching all products from the DB.");
		List<ProductEntity> listOfProducts = repository.findAll();
		if(!listOfProducts.isEmpty()) {
			LOGGER.info("Fetched {} products from the DB.", listOfProducts.size());
			response = utility.createListOfResponseInfo(listOfProducts);
		}else {
			LOGGER.info("There's no product available in the DB.");
			
		}
			
		return response;
	}

}
