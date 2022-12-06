package com.santosh.product.util;

import org.springframework.stereotype.Component;

import com.santosh.product.dto.Product;
import com.santosh.product.entity.ProductEntity;

@Component
public class ProductUtil {

	public ProductEntity createProductEntity(Product product){
		
		ProductEntity productEntity = null;
		if(null != product) {
			
			productEntity = new ProductEntity();
			productEntity.setProductName(product.getProductName());
			productEntity.setProductPrice(product.getProductPrice());
			productEntity.setProductQuantity(product.getProductQuantity());
		}
		
		return productEntity;
	}
}
