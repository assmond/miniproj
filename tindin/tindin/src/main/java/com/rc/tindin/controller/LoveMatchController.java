package com.rc.tindin.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

import com.rc.tindin.model.*;
import com.rc.tindin.service.LoveCalculatorService;
import com.rc.tindin.service.UserService;

@RestController
@RequestMapping(path = {"/loveMatch"})
public class LoveMatchController {

    @Autowired
    private LoveCalculatorService lcs;

    @Autowired
    private UserService userService;

    @GetMapping(produces = {"text/html"})
    public String matchWithRandomUser(@RequestParam(required = true) String username, Model model){
        List<String> usernameList = userService.getAllUsers();

        List<String> matchedList = lcs.retrieveMatchedUsers(username);
        
        String toMatch = "";

        for (String user: usernameList){
            if (!matchedList.contains(user)){
                toMatch = user;
                break;
            }
        }
        boolean matches = false;

        if (toMatch.equals("")){
            model.addAttribute("matches", matches);
            return "matchresult";
        }
        

        matches = true;
        model.addAttribute("matches", matches);
        LoveMatch lm = lcs.matchUsers(username, toMatch);
        model.addAttribute("lm", lm);
        return "matchresult";
    }

    

}
