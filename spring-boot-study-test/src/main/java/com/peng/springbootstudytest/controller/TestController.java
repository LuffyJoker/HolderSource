package com.peng.springbootstudytest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/index")
    public String hello(){
        return "hello";
    }
}
