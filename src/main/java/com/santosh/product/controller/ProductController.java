package com.santosh.product.controller;

import static com.santosh.product.util.ProductConstants.ERROR;
import static com.santosh.product.util.ProductConstants.INVALID_REQ;
import static com.santosh.product.util.ProductConstants.SUCCESS;
import static com.santosh.product.util.ProductConstants.ZERO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santosh.product.dto.ProductDTO;
import com.santosh.product.dto.ProductsResponse;
import com.santosh.product.dto.RequestDTO;
import com.santosh.product.dto.ResponseDTO;
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
	@PostMapping(produces = {APPLICATION_JSON_VALUE})
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
	@GetMapping(produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
	public ProductsResponse getAllProducts() {
		
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
	@GetMapping(path = "{id}", produces = {APPLICATION_JSON_VALUE})
	public ResponseDTO getProduct(@PathVariable Long id) {

		log.info("Fetching the product with id: {}", id);
		String result = "Successfully fetched the product";
		String status = SUCCESS;
		int statusCode = HttpStatus.OK.value();
		ResponseInfo responseInfo = null;
		
		try {
			
			responseInfo = service.getProduct(id);
			
			if(responseInfo == null) {
				result = "Product is not available";
				status = ERROR;
				statusCode = HttpStatus.NOT_FOUND.value();
				log.warn(result);
			}
			
		}catch(Exception ex) {
			result = "Problem in fetching this product from DB";
			status = ERROR;
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
			log.error(result, ex);
		}
		
		return populateProductResponse(result, status, statusCode, responseInfo);
	}

	// Update any particular product in DB.
	// Use validation frame work to validate the client request.
	@PutMapping(produces = {APPLICATION_JSON_VALUE})
	public ResponseDTO updateProduct(@Valid @RequestBody ProductDTO productDTO) {

		log.info("Updating the product with product ID: {}", productDTO.getProductId());
		String result = "Product updated successfully.";
		String status = SUCCESS;
		int statusCode = HttpStatus.OK.value();
		ResponseInfo responseInfo = null;
		try {
			
			responseInfo = service.updateProduct(productDTO);
			if(null == responseInfo) {
				result = "Product with productId: "+ productDTO.getProductId() +" is not available in DB.";
				status = ERROR;
				statusCode = HttpStatus.NOT_FOUND.value();
				log.error(result);
			}
			
		}catch(Exception ex) {
			result = "Problem in updating the product in DB.";
			status = ERROR;
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
			log.error(result, ex);
		}
		
		return populateProductResponse(result, status, statusCode, responseInfo);
	}

	// Delete a product from the DB.
	@DeleteMapping(path = "{id}", produces = {APPLICATION_JSON_VALUE})
	public ResponseDTO deleteProduct(@PathVariable Long id) {

		String result = "Product deleted successfully.";
		String status = SUCCESS;
		int statusCode = HttpStatus.OK.value();
		ResponseInfo responseInfo = null;
		try {
			service.deleteProduct(id);
			
		}catch(EmptyResultDataAccessException ex) {
			result = "There's no product with productId: " + id;
			status = ERROR;
			statusCode = HttpStatus.NOT_FOUND.value();
			log.error(result, ex);
		}
		catch(Exception ex) {
			result = "Problem in updating the product in DB.";
			status = ERROR;
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
			log.error(result, ex);
			
		}
		
		return populateProductResponse(result, status, statusCode, responseInfo);
	}
	
	
	@PatchMapping(path = "{id}")
	public ProductEntity updatePatchRequest(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		
		return service.updateProductByField(id, fields);
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
