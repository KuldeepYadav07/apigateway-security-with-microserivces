package com.employeeservice.external.service;


import com.employeeservice.entity.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentService {

    @GetMapping("/departments/{id}")
    Department getDepartment(@PathVariable("id") Long departmentId);
}
