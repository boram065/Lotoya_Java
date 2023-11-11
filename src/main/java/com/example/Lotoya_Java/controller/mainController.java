package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.BoramPlayer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class mainController {
    private final BoramPlayer boramPlayer;

    @RequestMapping("/main")
    public String Main(Model model) {

        model.addAttribute("imagePath1", "/static/images/logo.jpg");
        model.addAttribute("imagePath2", "/static/images/vs.jpg");
        model.addAttribute("imagePath3", "/static/images/club.jpg");
        model.addAttribute("imagePath4", "/static/images/FA.jpg");
        model.addAttribute("imagePath8", "/static/images/coin.jpg");
        model.addAttribute("imagePath9", "/static/images/basket.jpg");
        return "main";
    }
}
