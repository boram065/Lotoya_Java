package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController{
    private final UserService userService;

    @GetMapping("/join")
    public String showJoinForm(Model model){
        model.addAttribute("user", new User());
        return "join";
    }

    @PostMapping("/join")
    public String processJoin(@ModelAttribute User user){
        userService.createUser(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model){
        User user = userService.loginUser(email, password);

        if(user != null){
            model.addAttribute("loggedInUser", user);
            return "redirect:/main";
        }else{
            model.addAttribute("error", "존재하지 않는 유저입니다.");
            return "/login";
        }
    }
}
