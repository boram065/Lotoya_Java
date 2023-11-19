package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class BuyPlayerController {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    @Autowired
    public BuyPlayerController(JdbcTemplate jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
//        // 데이터베이스에서 사용자 정보를 조회하는 쿼리 작성
//        String selectSql = "SELECT * FROM user WHERE email = ?";
//        try {
//            return jdbcTemplate.queryForObject(selectSql, new Object[]{email}, (resultSet, rowNum) -> {
//                User user = new User();
//                user.setId(resultSet.getLong("id"));
//                user.setEmail(resultSet.getString("email"));
//                return user;
//            });
//        } catch (Exception e) {
//            return null;
//        }
    }
}
