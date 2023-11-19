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

    @PostMapping("/buyPlayer")
    @ResponseBody
    public ResponseEntity<String> buyPlayer(@RequestParam Long playerId, Principal principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().body("로그인 하세요!");
        }

        String userEmail = principal.getName();
        User user = getUserByEmail(userEmail);

        if (user == null) {
            return ResponseEntity.badRequest().body("사용자 정보를 찾을 수 없습니다.");
        }

        String playerSql = "SELECT * FROM player WHERE id = ?";
        int playerPrice = jdbcTemplate.queryForObject(playerSql, new Object[]{playerId}, Integer.class);

        if (user.getCoin() >= playerPrice) {
            int newCoinBalance = user.getCoin() - playerPrice;

            String updateCoinSql = "UPDATE user SET coin = ? WHERE id = ?";
            jdbcTemplate.update(updateCoinSql, newCoinBalance, user.getId());

            // 데이터베이스에 해당 선수 정보를 저장하는 로직 작성
            String insertSql = "INSERT INTO my_player (user_id, player_id) VALUES (?, ?)";
            jdbcTemplate.update(insertSql, user.getId(), playerId);

            return ResponseEntity.ok("Player purchased successfully");
        } else {
            return ResponseEntity.badRequest().body("코인이 부족합니다.");
        }
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
