package com.example.Lotoya_Java.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name="club", nullable = false)
    private String club;

    @Column(name="position", nullable = false)
    private String position;

    @Column(name="back_num", nullable = false)
    private Integer backNum;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="birth", nullable = false)
    private String birth;

    @Column(name="height", nullable = false)
    private Integer height;

    @Column(name="weight", nullable = false)
    private Integer weight;

    @Column(name="price", nullable = false)
    private Integer price;

    @Column(name="imgLink", nullable = false)
    private String imgLink;

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
