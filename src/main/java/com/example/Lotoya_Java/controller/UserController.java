package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.dto.UserRequest;
import com.example.Lotoya_Java.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController{
    private final UserService userService;

    @PostMapping("/user")
    public String signup(UserRequest request){
        return "/login";
    }
}
