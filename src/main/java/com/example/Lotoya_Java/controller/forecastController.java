package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class forecastController {
    private final Player player;

    public forecastController(Player player) {
        this.player = player;
    }

    @RequestMapping("/forecast")
    public String forecast(Model model) {
        model.addAttribute("imagePath1", "/static/images/logo.jpg");
        model.addAttribute("imagePath2", "/static/images/vs.jpg");
        model.addAttribute("imagePath3", "/static/images/club.jpg");
        model.addAttribute("imagePath4", "/static/images/FA.jpg");
        model.addAttribute("imagePath5", "/static/images/mypage.jpg");
        model.addAttribute("imagePath6", "/static/images/SSG.png");
        model.addAttribute("imagePath7", "/static/images/두산.png");
        model.addAttribute("imagePath8", "/static/images/KT.png");
        model.addAttribute("imagePath9", "/static/images/LG.png");
        model.addAttribute("imagePath10", "/static/images/NC.png");
        model.addAttribute("imagePath11", "/static/images/기아.png");
        model.addAttribute("imagePath12", "/static/images/롯데.png");
        model.addAttribute("imagePath13", "/static/images/삼성.png");
        model.addAttribute("imagePath14", "/static/images/키움.png");
        model.addAttribute("imagePath15", "/static/images/한화.png");
        
        return "forecast";
    }
}
