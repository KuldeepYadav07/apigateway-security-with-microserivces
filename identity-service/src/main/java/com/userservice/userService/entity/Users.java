package com.userservice.userService.entity;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private String id;
    private String name;
    private String password;
    private String address;
    private String email;
    private String state;
    private String roles;
    private boolean isActive = true;
}
