package com.example.Lotoya_Java.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "coin", nullable = false)
    private Integer coin = 1000;

    @OneToMany(mappedBy = "user")
    private List<MyPlayer> myPlayerList;

    @OneToMany(mappedBy = "user")
    private List<Wishlist> wishlistList;

    @Builder
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
}