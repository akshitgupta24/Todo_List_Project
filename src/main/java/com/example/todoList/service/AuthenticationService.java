package com.example.todoList.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String username , String password){
        if(username.equals("akshit") && password.equals("s")){
            return true;
        }
        return false;
    }
}
