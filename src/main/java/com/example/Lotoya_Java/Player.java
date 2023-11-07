package com.example.Lotoya_Java;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Player {
    private ArrayList<String> playerImg;
    private ArrayList<String> playerName;
    private ArrayList<String> playerNumber;
    private ArrayList<String> playerBirth;
    private ArrayList<String> playerPosition;
    private ArrayList<String> playerHeight;

    public Player(ArrayList<String> playerImg, ArrayList<String> playerName, ArrayList<String> playerNumber, ArrayList<String> playerBirth, ArrayList<String> playerPosition, ArrayList<String> playerHeight) {
        this.playerImg = playerImg;
        this.playerName = playerName;
        this.playerNumber = playerNumber;
        this.playerBirth = playerBirth;
        this.playerPosition = playerPosition;
        this.playerHeight = playerHeight;
    }

    public ArrayList<String> getPlayerImg() {
        return playerImg;
    }

    public void setPlayerImg(ArrayList<String> playerImg) {
        this.playerImg = playerImg;
    }

    public ArrayList<String> getPlayerName() {
        return playerName;
    }

    public void setPlayerName(ArrayList<String> playerName) {
        this.playerName = playerName;
    }

    public ArrayList<String> getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(ArrayList<String> playerNumber) {
        this.playerNumber = playerNumber;
    }

    public ArrayList<String> getPlayerBirth() {
        return playerBirth;
    }

    public void setPlayerBirth(ArrayList<String> playerBirth) {
        this.playerBirth = playerBirth;
    }

    public ArrayList<String> getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(ArrayList<String> playerPosition) {
        this.playerPosition = playerPosition;
    }

    public ArrayList<String> getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerHeight(ArrayList<String> playerHeight) {
        this.playerHeight = playerHeight;
    }
}