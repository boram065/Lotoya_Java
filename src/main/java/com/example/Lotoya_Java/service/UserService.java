package com.example.Lotoya_Java.service;

import com.example.Lotoya_Java.dto.UserDto;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User saveUser(User user){
        validateDuplicateUser(user);
        System.out.println(user);

        return userRepository.save(user);
    }

    private void validateDuplicateUser(User user){
        User findUser = userRepository.findByEmail(user.getEmail());
        if(findUser != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

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
