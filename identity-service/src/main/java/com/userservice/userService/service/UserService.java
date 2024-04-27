package com.userservice.userService.service;

import com.userservice.userService.dtos.AuthDTO;
import com.userservice.userService.dtos.UserDTO;
import com.userservice.userService.entity.Users;

import java.util.List;

public interface UserService {
    List<Users> getAllUsers();

    Users findById(String id);

    String  saveUser(Users users);

    String delete(String id);

    Users update(UserDTO userDTO, String id);

    String genrateToken(AuthDTO authDTO);

    void validateToken(String token);
}
