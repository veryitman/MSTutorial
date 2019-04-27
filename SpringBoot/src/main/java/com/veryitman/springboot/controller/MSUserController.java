package com.veryitman.springboot.controller;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class MSUserController {

    @RequestMapping(value = "/user")
    public String user() {
        return "Greate user, welcome!";
    }

    @RequestMapping(value = "/user2")
    public String user2() {
        return "Greate user!";
    }

    @RequestMapping(value = "/users/{page}/{pageSize}", method = RequestMethod.GET)
    public String getUsers(@PathVariable String page, @PathVariable String pageSize) {
        return "Get user list. " + "page: " + page + " - pagesize: " + pageSize;
    }

    @RequestMapping(value = "/users2/{page}/{pageSize}", method = RequestMethod.GET)
    public String getUsers2(@PathVariable(value = "pageSize") String page, @PathVariable(value = "page") String pageSize) {
        return "Get user list2. " +  "page: " + page + " - pageSize: " + pageSize;
    }

    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public String getFrieds(@PathParam(value = "page") String page, @PathParam(value = "pageSize") String pageSize) {
        return "Get friend list. " + "page: " + page + " - pageSize: " + pageSize;
    }
}
