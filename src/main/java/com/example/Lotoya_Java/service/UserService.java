package com.example.Lotoya_Java.service;

import com.example.Lotoya_Java.dto.UserDto;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User user){
        validateDuplicateUser(user);

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        return (UserDetails) User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
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
