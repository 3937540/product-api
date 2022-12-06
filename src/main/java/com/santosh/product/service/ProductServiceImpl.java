package com.santosh.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santosh.product.dto.Product;
import com.santosh.product.entity.ProductEntity;
import com.santosh.product.repository.ProductRepository;
import com.santosh.product.util.ProductUtil;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ProductUtil utility;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public ProductEntity saveProduct(Product product) {
		
			LOGGER.info("TEST Message");		
			ProductEntity ps = utility.createProductEntity(product);
			return repository.save(ps);
	}

}
