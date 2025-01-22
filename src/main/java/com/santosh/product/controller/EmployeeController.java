package com.santosh.product.controller;

import com.santosh.product.dto.EmployeeDetails;
import com.santosh.product.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping(path = "/getemployee", produces = {APPLICATION_JSON_VALUE})
    public EmployeeDetails getEmployee(@RequestParam(value = "empid") final int empid){
        return service.getEmployeeDetail(empid);
    }
}
