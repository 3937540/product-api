package com.santosh.product.repository;

import com.santosh.product.entity.EmployeeEntity;
import com.santosh.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    EmployeeEntity findByEmpId(@Param("empid") final int empid);

}
