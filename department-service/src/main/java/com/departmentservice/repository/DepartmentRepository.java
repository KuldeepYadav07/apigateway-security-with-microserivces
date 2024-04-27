package com.departmentservice.repository;

import com.departmentservice.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, Long> {
    Department findByDepartmentId(Long departmentId);
}
