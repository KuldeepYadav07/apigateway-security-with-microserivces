package com.employeeservice.response;

import com.employeeservice.entity.Department;
import com.employeeservice.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ResponseTemplateDTO {

    private Employee employee;
    private Department department;
}
