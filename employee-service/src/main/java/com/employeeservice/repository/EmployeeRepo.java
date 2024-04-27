package com.employeeservice.repository;

import com.employeeservice.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee ,Long> {

    Employee findByEmployeeId(Long employeeId);
}
