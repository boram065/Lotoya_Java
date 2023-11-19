package com.example.Lotoya_Java.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Component
@Repository
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name="club")
    private String club;

    @Column(name="position")
    private String position;

    @Column(name="back_num")
    private Integer backNum;

    @Column(name="name")
    private String name;

    @Column(name="birth")
    private String birth;

    @Column(name="height")
    private Integer height;

    @Column(name="weight")
    private Integer weight;

    @Column(name="price")
    private Integer price;

    @Column(name="imgLink")
    private String imgLink;

    @OneToMany(mappedBy = "player")
    private List<MyPlayer> myPlayerList;

    @OneToMany(mappedBy = "player")
    private List<Wishlist> wishlistList;


    @Builder
    public Player(Long id, String club, String position, Integer backNum, String name, String birth, Integer height, Integer weight, Integer price, String imgLink) {
        this.id = id;
        this.club = club;
        this.position = position;
        this.backNum = backNum;
        this.name = name;
        this.birth = birth;
        this.height = height;
        this.weight = weight;
        this.price = price;
        this.imgLink = imgLink;
    }
}
