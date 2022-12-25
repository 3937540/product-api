package com.santosh.product.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ProductExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException ex){
		
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
			
		});
		return errorMap;
	}
	
	@ExceptionHandler(value = {ProductCustomException.class})
	public final ResponseEntity<Object> handleProductCustomException(ProductCustomException ex){
		log.error("Exception caught by ExceptionHandler is {} and stacktrace is {}", ex, ex.getStackTrace());
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		
		ErrorDetails error = new ErrorDetails(new Date(), "Server Error", details);
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
