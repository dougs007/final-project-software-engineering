package com.br.sigaf.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SigafController {

    @GetMapping
    public String home() {
        return "API SIGAF. \n\n Click <a href=\"/swagger-ui.html\">here</a> to see the documentation.";
    }
}
