package com.userservice.userService.exception;

public class UserAlreadyFoundException extends RuntimeException{

    private String message;

    public UserAlreadyFoundException(){

    }

    public UserAlreadyFoundException(String msg){
        super(msg);
        this.message= msg;
    }

}
