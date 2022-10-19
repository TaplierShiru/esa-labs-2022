package gribanov.lab2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello-world")
public class HelloWorld {

    @GetMapping
    public String getHelloWorld() {
        return "Hello world";
    }
}
