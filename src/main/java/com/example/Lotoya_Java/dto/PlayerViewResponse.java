package com.example.Lotoya_Java.dto;

import com.example.Lotoya_Java.entity.Player;
import lombok.Getter;

@Getter
public class PlayerViewResponse {

    private final Long id;
    private final String club;
    private final String position;
    private final Integer backNum;
    private final String name;
    private final String birth;
    private final Integer height;
    private final Integer weight;
    private final Integer price;

    public PlayerViewResponse(Player player) {
        this.id = player.getId();
        this.club = player.getClub();
        this.position = player.getPosition();
        this.backNum = player.getBackNum();
        this.name = player.getName();
        this.birth = player.getBirth();
        this.height = player.getHeight();
        this.weight = player.getWeight();
        this.price = player.getPrice();

    }
}
