package com.rc.tindin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.rc.tindin.repositories.*;
import com.rc.tindin.model.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String handleLogin(String username, String password){
        Boolean loginStatus = userRepository.login(username, password);
        if (loginStatus){
            return username;
        }
        return "";
    }

    public String handleRegistration(String username, String password, String gender, String company, String jobTitle, String linkedinUrl){
        Boolean registrationStatus = userRepository.createUser(username, password, gender, company, jobTitle, linkedinUrl);
        if (registrationStatus){
            return username;
        }
        return "";
    }

    public List<String> getAllUsers(){
        return userRepository.getAllUsernames();
    }

    public User getUser(String username){
        return userRepository.getSelectedUser(username);
    }
}
