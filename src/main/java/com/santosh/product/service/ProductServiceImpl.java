package com.santosh.product.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.santosh.product.dto.ProductDTO;
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
//		List<ProductEntity> listOfProducts = repository.findAll();
		List<ProductEntity> listOfProducts = repository.findAll(Sort.by(Sort.Direction.ASC, "productQuantity"));
		if(!listOfProducts.isEmpty()) {
			LOGGER.info("Fetched {} products from the DB.", listOfProducts.size());
			response = utility.createListOfResponseInfo(listOfProducts);
		}else {
			LOGGER.info("There's no product available in the DB.");
			
		}
			
		return response;
	}

	@Override
	public ResponseInfo getProduct(Long productId) {
		LOGGER.info("Fetching product from DB for productId: {}", productId);
		ResponseInfo responseInfo = null;
		Optional<ProductEntity> entity = repository.findById(productId);
		if(entity.isPresent()) {
			responseInfo = utility.createResponseInfo(entity.get());
			LOGGER.info("Successfully fetched the product from DB.");
		}
		return responseInfo;
	}

	@Override
	public ResponseInfo updateProduct(ProductDTO productDTO) {
		LOGGER.info("Updating the product in DB for productId: {}", productDTO.getProductId());
		ProductEntity productEntity = repository.findById(productDTO.getProductId()).orElse(null);
		if(null != productEntity) {
			productEntity.setProductName(productDTO.getProductName());
			productEntity.setProductPrice(productDTO.getProductPrice());
			productEntity.setProductQuantity(productDTO.getProductQuantity());
			productEntity = repository.save(productEntity);
			LOGGER.info("Successfully updated the product in DB for productId: {}", productDTO.getProductId());
		}else {
			LOGGER.info("The product with productId: {} is not available in DB.", productDTO.getProductId());
		}
		return utility.createResponseInfo(productEntity);
	}

	@Override
	public void deleteProduct(Long id) {
		LOGGER.info("Deleting the product from DB with productId: {}", id);
		repository.deleteById(id);
		LOGGER.info("Successfully deleted the product from DB with productId: {}", id);
	}

}
