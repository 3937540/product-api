package com.santosh.product.controller;

import static com.santosh.product.util.ProductConstants.ERROR;
import static com.santosh.product.util.ProductConstants.SUCCESS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santosh.product.dto.ResponseDTO;
import com.santosh.product.dto.ResponseInfo;
import com.santosh.product.service.AdditionalProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v2/Products")
@Slf4j
public class AdditionalProductController {

	@Autowired
	private AdditionalProductService service;

	@GetMapping(path = "${fetch.product.with.request.param}", produces = { APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseDTO> getProductWithRequestParam(
			@RequestParam(value = "productId", required = true) final Long productId,
			@RequestParam(value = "productNm", required = true) final String productNm) {

		log.info("Product ID: {}, Product NM: {}", productId, productNm);
		ResponseInfo responseInfo = null;
		String result = "Successfully fetched the product";
		String status = SUCCESS;
		int statusCode = HttpStatus.OK.value();

		if (productId == null || productNm.isEmpty()) {
			// Create the response entity object with bad request.
			result = "Invalid request";
			status = ERROR;
			statusCode = HttpStatus.BAD_REQUEST.value();
			return new ResponseEntity<ResponseDTO>(populateProductResponse(result, status, statusCode, responseInfo),
					HttpStatus.BAD_REQUEST);
		} else {
			responseInfo = service.getProduct(productId, productNm);
			if (responseInfo == null) {
				result = "Product not found";
				status = ERROR;
				statusCode = HttpStatus.NOT_FOUND.value();
				return new ResponseEntity<ResponseDTO>(
						populateProductResponse(result, status, statusCode, responseInfo), HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<ResponseDTO>(
						populateProductResponse(result, status, statusCode, responseInfo), HttpStatus.OK);
			}

		}

	}
	
	@GetMapping(path = "${fetch.product.with.request.header}", produces = { APPLICATION_JSON_VALUE })
	public ResponseEntity<ResponseDTO> getProductWithRequestHeader(@RequestHeader Map<String, String> headers) {

		Long productId = Long.parseLong(headers.get("p-id"));
		String productNm = headers.get("p-name");
		log.info("Product ID: {}, Product NM: {}", productId, productNm);
		ResponseInfo responseInfo = null;
		String result = "Successfully fetched the product";
		String status = SUCCESS;
		int statusCode = HttpStatus.OK.value();

		if (productId == null || productNm.isEmpty()) {
			// Create the response entity object with bad request.
			result = "Invalid request";
			status = ERROR;
			statusCode = HttpStatus.BAD_REQUEST.value();
			return new ResponseEntity<ResponseDTO>(populateProductResponse(result, status, statusCode, responseInfo),
					HttpStatus.BAD_REQUEST);
		} else {
			responseInfo = service.getProduct(productId, productNm);
			if (responseInfo == null) {
				result = "Product not found";
				status = ERROR;
				statusCode = HttpStatus.NOT_FOUND.value();
				return new ResponseEntity<ResponseDTO>(
						populateProductResponse(result, status, statusCode, responseInfo), HttpStatus.NOT_FOUND);
			} else {
				HttpHeaders responseHeader = new HttpHeaders();
				responseHeader.set("X-Key", "Test Value");
				return new ResponseEntity<ResponseDTO>(
						populateProductResponse(result, status, statusCode, responseInfo), responseHeader, HttpStatus.OK);
			}

		}

	}

	private ResponseDTO populateProductResponse(String result, String status, int statusCode,
			ResponseInfo responseInfo) {

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setStatus(status);
		responseDTO.setReturnCode(statusCode);
		responseDTO.setReturnMessage(result);
		responseDTO.setResultDTO(responseInfo);

		return responseDTO;
	}
}
