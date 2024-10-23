package com.netmind.APICurso.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Hola Mundo!";
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "curso spring") String name) {
        return String.format("Hola %s!", name);
    }
}
