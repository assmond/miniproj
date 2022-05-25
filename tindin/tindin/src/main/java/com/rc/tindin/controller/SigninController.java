package com.rc.tindin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = {"/signin"})
public class SigninController {
    
    @GetMapping(produces = {"text/html"})
    public String signin(){
        return "signin";
    }
}
