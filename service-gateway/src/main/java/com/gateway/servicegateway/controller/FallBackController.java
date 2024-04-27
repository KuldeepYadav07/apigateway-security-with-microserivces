package com.gateway.servicegateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FallBackController {

    @PostMapping("/identityServiceFallBack")
    public String identityServiceFallBack() {
        log.info("fall back executed for identityService ");
        return "Identity Service is down! please contact to help desk!!!";
    }

    @GetMapping("/employeeServiceFallBack")
    public String employeeServiceFallBack() {
        log.info("fall back executed for employeeService ");
        return " Employee Service is down! please contact to help desk!!!";
    }

    @GetMapping("/departmentsServiceFallBack")
    public String departmentsServiceFallBack() {
        log.info("fall back executed for departmentsService ");
        return "Departments Service is down! please contact to help desk!!!";
    }
}
