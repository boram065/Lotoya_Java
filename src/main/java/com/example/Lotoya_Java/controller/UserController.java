package com.example.Lotoya_Java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    // 회원가입 페이지 출력 요청
    @GetMapping("/join")
    public String joinForm() {
        return "join";
    }

    @PostMapping("/join")    // name값을 requestparam에 담아온다
    public String join(@RequestParam("email_input") String email,
                       @RequestParam("pw_input") String password,
                       @RequestParam("id_input") String id) {
        System.out.println("UserController.join");
        System.out.println("email = " + email + ", password = " + password + ", id = " + id);
        return "login";
    }
}
