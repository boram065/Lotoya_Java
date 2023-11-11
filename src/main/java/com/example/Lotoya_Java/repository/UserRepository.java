package com.example.Lotoya_Java.repository;

import com.example.Lotoya_Java.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // email 로 사용자 정보 가져옴
}
