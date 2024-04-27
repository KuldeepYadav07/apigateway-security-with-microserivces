package com.userservice.userService.service.imp;

import com.userservice.userService.controller.UserController;
import com.userservice.userService.entity.Users;
import com.userservice.userService.repository.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    Logger logger = LogManager.getLogger(CustomUserDetailsService.class);
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Inside loadUserByUsername method");
       Optional<Users> user = userRepo.findByName(username);
        return user.map(CustomUserDetails::new)
                .orElseThrow(()->
                        new UsernameNotFoundException(" user  not found with name " +username));

    }
}
