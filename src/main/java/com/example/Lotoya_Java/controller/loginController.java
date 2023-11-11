package com.example.Lotoya_Java.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class loginController {

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("imagePath1", "/static/images/logo.jpg");
        model.addAttribute("imagePath2", "/static/images/vs.jpg");
        model.addAttribute("imagePath3", "/static/images/club.jpg");
        model.addAttribute("imagePath4", "/static/images/FA.jpg");

        return "login";
    }
}
