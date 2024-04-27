package com.userservice.userService.service.imp;
import com.userservice.userService.dtos.AuthDTO;
import com.userservice.userService.dtos.UserDTO;
import com.userservice.userService.entity.Users;
import com.userservice.userService.exception.UserAlreadyFoundException;
import com.userservice.userService.repository.UserRepo;
import com.userservice.userService.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    Logger logger= LogManager.getLogger(UserServiceImp.class);
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Users> getAllUsers() {
        List<Users> allUser = userRepo.findAll();
        if (allUser.isEmpty()){
            throw  new RuntimeException("No User Present In DB Please First Create User. Thanks !!");
        }
        return allUser;
    }

    @Override
    public Users findById(String id) {
        Optional<Users> users = userRepo.findById(id);
        if (users.isEmpty()){
            logger.error("user not  found " , id);
            throw new NoSuchElementException("No user Present with id  " + id);
        }
        return users.get();
    }

    @Override
    public String saveUser(Users users) {
        Optional<Users> user = userRepo.findById(users.getId());
        if (user.isPresent()){
            logger.error("user allReady found " , users.getId());
            throw new UserAlreadyFoundException(" user allReady present " +users.getId());

        }else {
                users.setPassword(passwordEncoder.encode(users.getPassword()));
                userRepo.save(users);
                logger.info("user saved in DB");
            return "User Save successfully !!";
        }

    }

    @Override
    public String delete(String id) {
        Optional<Users> users = userRepo.findById(id);
        if (users.isEmpty()){
            logger.error("user not  found " , id);
            throw new NoSuchElementException("No user Present with Id : " + id );
        }else {
            userRepo.deleteById(id);
            return  "User Deleted successfully !!";
        }

    }

    @Override
    public Users update(UserDTO userDTO, String id) {
        return null;
    }

    @Override
    public String genrateToken(AuthDTO authDTO) {
        return jwtService.generateToken(authDTO.getName());
    }

    @Override
    public void validateToken(String token){
         jwtService.validateToken(token);
    }
}
