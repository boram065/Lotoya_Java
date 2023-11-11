package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.dto.UserDto;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController{

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String login(Model model){
        model.addAttribute("userDto", new UserDto());
        return "join";
    }
    @PostMapping(value = "new")
    public String login(@Valid UserDto userDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "join";
        }try{
            User user = userService.createUser(userDto, passwordEncoder);
            userService.saveUser(user);
            return "login";
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "join";
        }
    }

    @GetMapping(value = "/login")
    public String loginUser(){
        return "login";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "login";
    }
}
