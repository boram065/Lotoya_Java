package com.example.Lotoya_Java.service;

import com.example.Lotoya_Java.dto.UserDto;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
<<<<<<< HEAD
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User saveUser(User user){
        validateDuplicateUser(user);
        System.out.println(user);
=======
>>>>>>> 9123af325493a6574989b41649c52aeee8043310

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;

    }
<<<<<<< HEAD

    public static User createUser(UserDto userDto, PasswordEncoder passwordEncoder){
        User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        return user;
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
=======
}
>>>>>>> 9123af325493a6574989b41649c52aeee8043310
