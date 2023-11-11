package com.example.Lotoya_Java.service;

import com.example.Lotoya_Java.dto.UserRequest;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(UserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

//    public List<User> getAllUser(){
//        return userRepository.findAll();
//    }
//
//    public User getUserById(Long userEmail){
//        return userRepository.findById(userEmail).orElse(null);
//    }
//
//    public User createUser(User user){
//        return userRepository.save(user);
//    }
//
////    public User updateUser(Long userNo, User updateUser){
////        if(userRepository.existsById(userNo)){
////            updateUser.setUserNo(userNo);
////            return userRepository.save(updateUser);
////        }
////    }
//
//    public User SaveUser(User user){
//        return userRepository.save(user);
//    }
//
}
