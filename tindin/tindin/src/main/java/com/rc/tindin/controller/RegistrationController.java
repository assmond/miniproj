package com.rc.tindin.controller;

import org.springframework.web.bind.annotation.*;

import com.rc.tindin.service.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping(path = {"/registration"})
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoveCalculatorService lcs;

    @GetMapping(produces = {"text/html"})
    public String registration(@RequestParam(required = true) String username, @RequestParam(required = true)String password, @RequestParam(required = true)String gender, @RequestParam(required = true)String company, @RequestParam(required = true)String job, @RequestParam(required = true)String url, Model model){
        String checkUsername = userService.handleRegistration(username, password, gender, company, job, url);
        model.addAttribute("checkUsername", checkUsername);
        List<String> matchList = lcs.retrieveMatchedUsers(username);
        model.addAttribute("size", matchList.size());
        model.addAttribute("matchList", lcs.retrieveMatchedUsers(username));
        return "dashboard";
    }
}
