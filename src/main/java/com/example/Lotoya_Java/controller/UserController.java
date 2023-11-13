package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.dto.UserDto;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.UserRepository;
import com.example.Lotoya_Java.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/join")
@Controller
public class UserController{

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String login(Model model){
        model.addAttribute("userDto", new UserDto());
        return "user/join";
    }
    @PostMapping(value = "new")
    public String login(@Valid UserDto userDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "user/join";
        }try{
            User user = userService.createUser(userDto, passwordEncoder);
            userService.saveUser(user);
            return "user/login";
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "user/join";
        }
    }

    @PostMapping(value = "login")
    public String loginUser(@Valid UserDto userDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "/login";
        }

        User user = userRepository.findByEmail(userDto.getEmail());

        if (user == null || !passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            model.addAttribute("errorMessage", "이메일 또는 비밀번호가 잘못되었습니다.");
            return "/login";
        }
        return "redirect:/main";
    }
}
