package com.santosh.product.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "EMP_MASTER")
public class EmployeeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue        //Hibernate generate an unique value.
//	We can use @GeneratedValue with DB sequence to persist ids in DB.
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ID_SQ")
//	@SequenceGenerator(sequenceName = "PRODUCT_ID_SQ" allocationSize = 1, name = "PRODUCT_ID_SQ")
    @Column(name = "EMPID")
    private Integer empId;

    @Column(name = "EMPNAME")
    private String empName;

    @Column(name = "EMPAGE")
    private int empAge;

    @Column(name = "EMPSAL")
    private double empSal;

    @Column(name = "EMPDEPT")
    private String empDept;

}
