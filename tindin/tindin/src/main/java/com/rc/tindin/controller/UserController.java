package com.rc.tindin.controller;

import org.springframework.web.bind.annotation.*;

import com.rc.tindin.service.*;
import com.rc.tindin.model.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.ui.Model;
import java.util.List;

@RestController
@RequestMapping(path = {"/user"})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path="/registration")
    public String registration(@RequestBody(required = true) String username, String password, String gender, String company, String jobTitle, String linkedinUrl, Model model){
        String checkUsername = userService.handleRegistration(username, password, gender, company, jobTitle, linkedinUrl);
        model.addAttribute("checkUsername", checkUsername);
        return "dashboard";
    }

    @GetMapping(path="/getUser")
    public ResponseEntity<User> getSelectedUser(@RequestBody(required = true) String username){
        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }
}
