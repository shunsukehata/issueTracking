package com.example.its.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    // "/"は省略可能
    @GetMapping("/")
    public String index() {
        return "index"; // src/main/resources/templates/index.htmlを返す。
    }
}
