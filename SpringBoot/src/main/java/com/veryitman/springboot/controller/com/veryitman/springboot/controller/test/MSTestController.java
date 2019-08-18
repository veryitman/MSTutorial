package com.veryitman.springboot.controller.com.veryitman.springboot.controller.test;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import javax.ws.rs.QueryParam;

@RestController
@RequestMapping(value = "spbtest")
public class MSTestController {

    // PathVariable
    @RequestMapping(value = "/users/{page}/{pageSize}", method = RequestMethod.GET)
    public String getUsers(@PathVariable String page, @PathVariable String pageSize) {
        return "Get user list. " + "page: " + page + " - pagesize: " + pageSize;
    }

    @RequestMapping(value = "/users2/{page}/{pageSize}", method = RequestMethod.GET)
    public String getUsers2(@PathVariable(value = "pageSize") String page, @PathVariable(value = "page") String pageSize) {
        return "Get user list2. " +  "page: " + page + " - pageSize: " + pageSize;
    }

    // PathParam
    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public String getFrieds(@PathParam(value = "page") String page, @PathParam(value = "pageSize") String pageSize) {
        return "Get friend list. " + "page: " + page + " - pageSize: " + pageSize;
    }

    @RequestMapping(value = "/friends2", method = RequestMethod.GET)
    public String getFrieds2(@PathParam(value = "page") String page, @PathParam(value = "pagesize") String pagesize) {
        return "Get friend list. " + "page: " + page + " - pagesize: " + pagesize;
    }

    // RequestParam
    @RequestMapping(value = "/friends3", method = RequestMethod.GET)
    public String getFrieds3(@RequestParam(value = "page") String page, @RequestParam(value = "yy") String pageSize) {
        return "Get friend list. " + "page: " + page + " - pageSize: " + pageSize;
    }

    // QueryParam
    @RequestMapping(value = "/friends4", method = RequestMethod.GET)
    public String getFrieds4(@QueryParam(value = "page") String page, @QueryParam(value = "pageSize") String pageSize) {
        return "Get friend list. " + "page: " + page + " - pageSize: " + pageSize;
    }
}
