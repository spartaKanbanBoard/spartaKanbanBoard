package com.sparta.spartakanbanboard.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "login"; // Thymeleaf 템플릿의 경로와 이름 (예: templates/login.html)
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup"; // Thymeleaf 템플릿의 경로와 이름 (예: templates/signup.html)
    }

    @GetMapping("/mainPageAfterLogin.html")
    public String getMainPageAfterLogin() {
        return "mainPageAfterLogin"; // Thymeleaf 템플릿의 경로와 이름 (예: templates/mainPageAfterLogin.html)
    }
}
