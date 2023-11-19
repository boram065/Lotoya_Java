package com.example.Lotoya_Java.service;

//import com.example.Lotoya_Java.dto.UserContextHolder;
import com.example.Lotoya_Java.controller.UserController;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(Long email) {
        return userRepository.findByEmail(String.valueOf(email));
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    public void updateCoinValue(Integer newCoinValue, HttpSession session) {
        User user = UserController.getLoggedInUser(session);

        if (user != null) {
            user.setCoin(newCoinValue);
            userRepository.save(user);
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }
    }
}