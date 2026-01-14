package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @RequestMapping(path = "v1/{name}", method = RequestMethod.GET)
    public String test(@PathVariable("name") String name) {
         return "hello " + name;
    }
}
