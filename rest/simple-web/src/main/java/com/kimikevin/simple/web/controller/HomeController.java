package com.kimikevin.simple.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
    @RequestMapping("/")
    public String greeting() {
        return "Hello World!";
    }
}
