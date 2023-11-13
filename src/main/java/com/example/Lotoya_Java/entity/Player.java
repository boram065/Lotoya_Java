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

    @Column(name="imgLink")
    private String imgLink;

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

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getClub() {
        return club;
    }
    public void setClub(String club) {
        this.club = club;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getBackNum() {
        return backNum;
    }
    public void setBackNum(Integer backNum) {
        this.backNum = backNum;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }

    public Integer getHeight() {
        return height;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImgLink() {
        return imgLink;
    }
    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
