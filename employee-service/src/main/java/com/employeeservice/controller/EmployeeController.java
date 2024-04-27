package com.employeeservice.controller;


import com.employeeservice.entity.Employee;
import com.employeeservice.response.ResponseTemplateDTO;
import com.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/hello")
    public String hello() {
        log.info("Inside saveEmployee of UserController");
        return " welcome";
    }

    @PostMapping("/")
    public Employee saveUser(@RequestBody Employee employee) {
        log.info("Inside saveEmployee of UserController");
        return employeeService.saveUser(employee);
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "EMP-DEPARTMENT-BREAKER", fallbackMethod = "departmentFallBack")
    public ResponseTemplateDTO getUserWithDepartment(@PathVariable("id") Long employeeId) {
        log.info("Inside getEmployeeWithDepartment of EmployeeController");
        return employeeService.getEmployeeWithDepartment(employeeId);
    }

    public ResponseTemplateDTO departmentFallBack (Long employeeId , Exception ex){
        log.info("fall back executed because service is down" +ex.getMessage());
       return ResponseTemplateDTO.builder().build();
    }
}
