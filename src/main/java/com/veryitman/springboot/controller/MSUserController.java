package com.veryitman.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MSUserController {

    @RequestMapping(value = "/user")
    public String user() {
        return "Greate user";
    }
}
