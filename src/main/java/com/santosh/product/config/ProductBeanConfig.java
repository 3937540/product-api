package com.santosh.product.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ProductBeanConfig {

	@Bean
	public RestTemplate getRestTemplate() {

		return new RestTemplate();
	}
	
	@Bean
	public Docket apiDocket() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.santosh.product.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiInfo() {
		
		return new ApiInfoBuilder()
				.title("Product API")
				.description("Santosh API")
				.version("v1")
				.build();
	}
	
	/*
	 * private Predicate<String> paths(){
	 * 
	 * return Predicates.not(PathSelectors.regex("/error.*")); }
	 */
}
