package com.sparta.spartakanbanboard.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/board.html")
    public String showBoardPage(@RequestParam("boardId") String boardId) {
        return "board";
    }

    @GetMapping("/createBoard.html")
    public String getCreateBoard() {
        return "createBoard";
    }

    @GetMapping("/mainPageAfterLoginForUser.html")
    public String getMainPageAfterLoginForUser() {
        return "mainPageAfterLoginForUser";
    }

    @GetMapping("/checkCurrentUser.html")
    public String getCheckCurrentUser() {
        return "checkCurrentUser";
    }
}
