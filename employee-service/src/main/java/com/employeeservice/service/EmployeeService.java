package com.employeeservice.service;

import com.employeeservice.entity.Employee;
import com.employeeservice.external.service.DepartmentService;
import com.employeeservice.repository.EmployeeRepo;
import com.employeeservice.entity.Department;
import com.employeeservice.response.ResponseTemplateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class EmployeeService {


    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DepartmentService departmentService;
    public Employee saveUser(Employee employee) {
        return employeeRepo.save(employee) ;
    }

    public ResponseTemplateDTO getEmployeeWithDepartment(Long employeeId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateDTO object = new ResponseTemplateDTO();
        Employee user = employeeRepo.findByEmployeeId(employeeId);

        //using feign client  get object from other service
        Department department =departmentService.getDepartment(user.getDepartmentId());

                // using rest template get object from other service
               // restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),Department.class);
        object.setEmployee(user);
        object.setDepartment(department);
        return  object;
    }

}
