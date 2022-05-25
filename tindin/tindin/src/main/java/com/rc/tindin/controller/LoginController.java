package com.rc.tindin.controller;

import org.springframework.web.bind.annotation.*;

import com.rc.tindin.service.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping(path = {"/login"})
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoveCalculatorService lcs;

    @GetMapping(produces = {"text/html"})
    public String login(@RequestParam(required = true) String username, @RequestParam(required = true)String password, Model model){
        String checkUsername = userService.handleLogin(username, password);
        model.addAttribute("checkUsername", checkUsername);
        List<String> matchList = lcs.retrieveMatchedUsers(username);
        model.addAttribute("size", matchList.size());
        model.addAttribute("matchList", lcs.retrieveMatchedUsers(username));
        return "dashboard";
    }
}
