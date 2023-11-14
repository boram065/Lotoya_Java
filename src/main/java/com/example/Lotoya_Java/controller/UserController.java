package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
