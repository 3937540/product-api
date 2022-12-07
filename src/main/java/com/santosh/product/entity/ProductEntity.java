package com.santosh.product.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "PRODUCT_DTL")
public class ProductEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue  		//Hibernate generate an unique value.
//	We can use @GeneratedValue with DB sequence to persist ids in DB.
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ID_SQ")
//	@SequenceGenerator(sequenceName = "PRODUCT_ID_SQ" allocationSize = 1, name = "PRODUCT_ID_SQ")
	@Column(name = "PRDCT_ID")
	private Long productId;
	
	@Column(name = "PRDCT_NM")
	private String productName;
	
	@ColumnDefault("1")
	@Column(name = "PRDCT_QNTY")
	private int productQuantity;
	
	@Column(name = "PRDCT_PRICE")
	private BigDecimal productPrice;

}
