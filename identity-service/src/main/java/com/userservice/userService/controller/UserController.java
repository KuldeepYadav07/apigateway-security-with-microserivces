package com.userservice.userService.controller;

import com.userservice.userService.dtos.AuthDTO;
import com.userservice.userService.dtos.UserDTO;
import com.userservice.userService.entity.Users;
import com.userservice.userService.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/identity")
public class UserController {

    Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.GET, value = {"/allUser"}, consumes = ("application/json"))
    public ResponseEntity<List<Users>> getAllUser() {
        logger.info("Inside getAllUser method form UserController ");
        List<Users> allUsers = userService.getAllUsers();
        logger.info("User fetched successfully");
        return new ResponseEntity<>(allUsers, HttpStatus.ACCEPTED);

    }

    @RequestMapping(method = RequestMethod.GET, value = {"/getUserBy/{id}"}, consumes = ("application/json"))
    public ResponseEntity<Users> getUserById(@RequestParam("Id") String id) {
        logger.info("Inside getUserById method form UserController ");
        Users user = userService.findById(id);
        logger.info(" fetching user by id " + id);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/register"})
    public ResponseEntity<String> createUser(@RequestBody Users users) {
        logger.info("Inside createUser method form UserController ");
        String created = userService.saveUser(users);
        logger.info(" new user created ");
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = {"/update/{id}"}, consumes = ("application/json"))
    public ResponseEntity<Users> updateUserById(@RequestParam("Id") String id, @RequestBody UserDTO userDTO) {
        logger.info("Inside updateUserById method form UserController ");
        Users update = userService.update(userDTO, id);
        logger.info("user Updated " + id);
        return new ResponseEntity<>(update, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = {"/delete/{id}"})
    public ResponseEntity<String> deleteUserById(@RequestParam("Id") String id) {
        logger.info("Inside deleteUserById method form UserController ");
        String delete = userService.delete(id);
        logger.info(" user deleted " + id);
        return new ResponseEntity<>(delete, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/token"})
    public ResponseEntity<String> createToken(@RequestBody AuthDTO authDTO) {
        logger.info("Inside createToken method form UserController ");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getName(), authDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = userService.genrateToken(authDTO);
            logger.info(" token created for  " + authDTO.getName());
            return new ResponseEntity<>(token, HttpStatus.ACCEPTED);

        } else {
            logger.error("User Details invalid  " + authDTO.getName());
            throw new UsernameNotFoundException(" Invalid User");
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = {"/validate"})
    public ResponseEntity<String> validateUserToken(String token) {
        logger.info("Inside validateUserToken method form UserController ");
        userService.validateToken(token);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

}

