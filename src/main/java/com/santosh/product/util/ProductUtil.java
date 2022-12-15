package com.santosh.product.util;

import org.springframework.stereotype.Component;

import com.santosh.product.dto.RequestDTO;
import com.santosh.product.dto.ResponseInfo;
import com.santosh.product.entity.ProductEntity;
import static com.santosh.product.util.ProductConstants.ZERO;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductUtil {

	public ProductEntity createProductEntity(RequestDTO product, ProductEntity entity){
		
		if(null != product.getProductName())
			entity.setProductName(product.getProductName());
		if(null != product.getProductPrice())
			entity.setProductPrice(product.getProductPrice());
		if(ZERO != product.getProductQuantity())
			entity.setProductQuantity(product.getProductQuantity());
		
		
		return entity;
	}

	public ResponseInfo createResponseInfo(ProductEntity productEntity) {
		ResponseInfo responseInfo = null;
		
		if(null != productEntity) {
			responseInfo = new ResponseInfo();
			responseInfo.setId(productEntity.getProductId());
			responseInfo.setProductName(productEntity.getProductName());
			responseInfo.setProductPrice(productEntity.getProductPrice());
			responseInfo.setProductQuantity(productEntity.getProductQuantity());
		}
		
		return responseInfo;
	}

	public List<ResponseInfo> createListOfResponseInfo(List<ProductEntity> listOfProducts) {
		
		List<ResponseInfo> responseList = new ArrayList();
		for(ProductEntity productEntity : listOfProducts) {
			ResponseInfo responseInfo = new ResponseInfo();
			responseInfo.setId(productEntity.getProductId());
			responseInfo.setProductName(productEntity.getProductName());
			responseInfo.setProductPrice(productEntity.getProductPrice());
			responseInfo.setProductQuantity(productEntity.getProductQuantity());
			responseList.add(responseInfo);
		}
		return responseList;
	}
}
