package com.santosh.product.service;

import com.santosh.product.dto.EmployeeDeptDetails;
import com.santosh.product.dto.EmployeeDetails;
import com.santosh.product.entity.EmployeeEntity;
import com.santosh.product.repository.EmployeeRepository;
import com.santosh.product.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {


    @Autowired
    EmployeeRepository repository;
    @Autowired
    private ProductUtil utility;
    public EmployeeDetails getEmployeeDetail(int empid){
        EmployeeEntity entity = repository.findByEmpId(empid);
        return utility.createEmployeeResponse(entity);
    }

    public EmployeeDeptDetails getEmployeeDeptDetail(int empid) {
        EmployeeEntity entity = repository.findByEmpId(empid);
        return utility.createEmployeeDeptResponse(entity);
    }
}
