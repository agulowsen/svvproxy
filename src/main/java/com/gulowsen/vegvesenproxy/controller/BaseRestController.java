package com.gulowsen.vegvesenproxy.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Tag(name = "Actuators")
public class BaseRestController {

    @GetMapping("/actuator/health")
    public String health() {
        return "OK";
    }

}
