package com.santosh.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.santosh.product.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

	ProductEntity findProductByProductIdAndProductName(@Param("productId") final Long productId, @Param("productNm") final String productNm);

	ProductEntity findProductByProductId(@Param("productId") final Long productId);

	//@Query("select productPrice from ProductEntity where productName =:productNm and actFlg = 'Y'")
	//@Query(value = "select * from PRODUCT_DTL where PRDCT_NM =:productNm", nativeQuery = true)
	@Query("select pe from ProductEntity pe where pe.productName =:productNm")
	ProductEntity getProductByName(@Param("productNm") final String productNm);
	
}
