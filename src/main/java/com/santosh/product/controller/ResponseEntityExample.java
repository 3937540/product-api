package com.santosh.product.controller;


import com.santosh.product.dto.RequestDTO;
import com.santosh.product.dto.ResponseDTO;
import com.santosh.product.dto.ResponseInfo;
import com.santosh.product.entity.ProductEntity;
import com.santosh.product.service.AdditionalProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.santosh.product.util.ProductConstants.*;
import static com.santosh.product.util.ProductConstants.INVALID_REQ;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/responseentity")
@Slf4j
public class ResponseEntityExample {

    @Autowired
    private AdditionalProductService service;

    @GetMapping(path = "/requestparam", produces = { APPLICATION_JSON_VALUE })   // Second endpoint.
    public ResponseEntity<ResponseDTO> getProductWithRequestParam(
            @RequestParam(value = "productId") final Long productId,
            @RequestParam(value = "productNm") final String productNm) {

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

    @GetMapping(path = "/pathvariable/{productId}", produces = { APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseDTO> getProductWithPathVariable(@PathVariable(value = "productId") final Long productId, @RequestHeader(value = "uuid", required = true) String uuid) {

        log.info("Product ID: {} and UUID: {}", productId, uuid);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("uuid", uuid);
        ResponseInfo responseInfo = null;
        String result = "Successfully fetched the product";
        String status = SUCCESS;
        int statusCode = HttpStatus.OK.value();

        if (productId < 100 || productId > 500) {  //Product ID should be between 100..500
            // Create the response entity object with bad request.
            result = "Invalid request";
            status = ERROR;
            statusCode = HttpStatus.BAD_REQUEST.value();
            return new ResponseEntity<ResponseDTO>(populateProductResponse(result, status, statusCode, responseInfo), responseHeader,
                    HttpStatus.BAD_REQUEST);
        } else {
            responseInfo = service.getProductById(productId);
            if (responseInfo == null) {
                result = "Product not found";
                status = ERROR;
                statusCode = HttpStatus.NOT_FOUND.value();
                return new ResponseEntity<ResponseDTO>(
                        populateProductResponse(result, status, statusCode, responseInfo), responseHeader, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<ResponseDTO>(
                        populateProductResponse(result, status, statusCode, responseInfo), responseHeader, HttpStatus.OK);
            }

        }

    }

    @GetMapping(path = "/product/{productId}", produces = { APPLICATION_JSON_VALUE })  // First endpoint.
    public ResponseDTO getProductWithoutResponseEntity(@PathVariable(value = "productId") final Long productId) {

        log.info("Product ID: {}", productId);
        ResponseInfo responseInfo = null;
        String result = "Successfully fetched the product";
        String status = SUCCESS;
        int statusCode = HttpStatus.OK.value();

        if (productId < 100 || productId > 500) {  //Product ID should be between 100..500
            // Create the response entity object with bad request.
            result = "Invalid request";
            status = ERROR;
            statusCode = HttpStatus.BAD_REQUEST.value();
            return populateProductResponse(result, status, statusCode, responseInfo);
        } else {
            responseInfo = service.getProductById(productId);
            if (responseInfo == null) {
                result = "Product not found";
                status = ERROR;
                statusCode = HttpStatus.NOT_FOUND.value();
                return populateProductResponse(result, status, statusCode, responseInfo);
            } else {
                return populateProductResponse(result, status, statusCode, responseInfo);
            }

        }

    }

    @PostMapping(path = "/addproduct", produces = {APPLICATION_JSON_VALUE})  // Third endpoint
    public ResponseEntity<ResponseDTO> addProducts(@RequestHeader Map<String, String> requestHeader, @RequestBody RequestDTO requestDTO) {
   // public ResponseEntity<ResponseDTO> addProducts(@RequestHeader(value = "uuid", required = true) String uuid, @RequestBody RequestDTO requestDTO) {
        log.info("Adding product for the UUID: {}", requestHeader.get("uuid"));
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("uuid", requestHeader.get("uuid"));
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
                return new ResponseEntity<>(populateProductResponse(result, status, statusCode, responseInfo), responseHeader,HttpStatus.BAD_REQUEST);
            }

            responseInfo = service.saveProduct(requestDTO);
            log.info(result);

        } catch (Exception ex) {

            result = "Ploblem in saving the product into DB";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            status = ERROR;
            log.error(result, ex);
            return new ResponseEntity<>(populateProductResponse(result, status, statusCode, responseInfo),responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(populateProductResponse(result, status, statusCode, responseInfo),responseHeader, HttpStatus.OK);

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
