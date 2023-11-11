package com.example.Lotoya_Java.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
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

}
