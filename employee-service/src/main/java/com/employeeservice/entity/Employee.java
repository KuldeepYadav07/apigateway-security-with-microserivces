package com.employeeservice.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Employee {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private Long departmentId;
}
