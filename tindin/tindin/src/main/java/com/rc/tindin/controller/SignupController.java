package com.rc.tindin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = {"/signup"})
public class SignupController {
    
    @GetMapping(produces = {"text/html"})
    public String signup(){
        return "signup";
    }
}
