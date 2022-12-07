package com.santosh.product.controller;

import static com.santosh.product.util.ProductConstants.ERROR;
import static com.santosh.product.util.ProductConstants.INVALID_REQ;
import static com.santosh.product.util.ProductConstants.SUCCESS;
import static com.santosh.product.util.ProductConstants.ZERO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santosh.product.dto.ResponseDTO;
import com.santosh.product.dto.ProductsResponse;
import com.santosh.product.dto.RequestDTO;
import com.santosh.product.dto.ResponseInfo;
import com.santosh.product.entity.ProductEntity;
import com.santosh.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/Products")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService service;

	// Add products into DB.
	@PostMapping
	public ResponseDTO addProducts(@RequestBody RequestDTO requestDTO) {

		String result = "Successfully saved the product";
		String status = SUCCESS;
		int statusCode = HttpStatus.OK.value();
		boolean validRequest = false;
		ResponseInfo responseInfo = null;

		try {

			if (null != requestDTO.getProductName() && !requestDTO.getProductName().isEmpty()
					&& requestDTO.getProductQuantity() > ZERO && null != requestDTO.getProductPrice()) {
				
				validRequest = true;
			}
			if (!validRequest) {
				
				result = INVALID_REQ;
				statusCode=HttpStatus.BAD_REQUEST.value();
				status = ERROR;
				log.error(result, requestDTO);
				return populateProductResponse(result, status, statusCode, responseInfo);
			}
			
			responseInfo = service.saveProduct(requestDTO);
			log.info(result);

		} catch (Exception ex) {
			
			result = "Ploblem in saving the product into DB";
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
			status = ERROR;
			log.error(result, ex);
		}
		
		return populateProductResponse(result, status, statusCode, responseInfo);

	}

	// Get all products from DB.
	@GetMapping
	public ProductsResponse getProducts() {
		
		String result = "Successfully fetched products from DB.";
		String status = SUCCESS;
		int statusCode = HttpStatus.OK.value();
		List<ResponseInfo> responseInfo = null;

		try {
			
			responseInfo = service.getAllProducts();
			
		}catch(Exception ex) {
			result = "Ploblem in fetching products from DB";
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
			status = ERROR;
			log.error(result, ex);
		}
		
		return productResponse(result, status, statusCode, responseInfo);
	}

	
	// Get any particular product from DB.
	@GetMapping(path = "${product.id}")
	public ResponseDTO getProduct() {

		return null;
	}

	// Update any particular product in DB.
	@PutMapping(path = "${product.id}")
	public ResponseDTO updateProduct() {

		return null;
	}

	// Delete a product from the DB.
	@DeleteMapping(path = "${product.id}")
	public ResponseDTO deleteProduct() {

		return null;
	}
	
	private ResponseDTO populateProductResponse(String result, String status, int statusCode,
			ResponseInfo responseInfo) {
		ResponseDTO productResponse = new ResponseDTO();
		productResponse.setStatus(status);
		productResponse.setReturnCode(statusCode);
		productResponse.setReturnMessage(result);
		productResponse.setResultDTO(responseInfo);
		return productResponse;
	}

	private ProductsResponse productResponse(String result, String status, int statusCode,
			List<ResponseInfo> responseInfo) {
		ProductsResponse response = new ProductsResponse();
		response.setStatus(status);
		response.setReturnCode(statusCode);
		response.setReturnMessage(result);
		response.setResultDTO(responseInfo);
		return response;
	}
}
