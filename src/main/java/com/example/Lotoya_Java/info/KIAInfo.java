package com.example.Lotoya_Java.info;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class KIAInfo {
    public static ArrayList<String> imgList = new ArrayList<>();
    public static ArrayList<String> numList = new ArrayList<>();
    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> birthList = new ArrayList<>();
    public static ArrayList<String> positionList = new ArrayList<>();
    public static ArrayList<String> heightList = new ArrayList<>();

    public static void main(String[] args) {
        String pitcher1 = "https://tigers.co.kr/players/pitcher/";
        String pitcher2[] = {"67604", "53609"};
        String pitcherImg = "https://tigers.co.kr/players/pitcher";

        try {
            for(int i = 0; i < pitcher2.length; i++) {
                Document doc = Jsoup.connect(pitcher1 + pitcher2[i]).get();
                Element div = doc.selectFirst(".plyr_view01In");

                String playerImg = "";
                String playerName = "";
                String playerNumber = "";
                String playerBirth = "";
                String playerPosition = "";
                String playerHeight = "";

                playerPosition = div.select("p").text();
                positionList.add(playerPosition);

                Element name = div.select("h5").first();
                playerName = name.ownText();
                nameList.add(playerName);

                Element number = div.select("strong").first();
                playerNumber = number.text();
                numList.add(playerNumber);

                playerBirth = div.select("dt:contains(생년월일) + dd").text();
                birthList.add(playerBirth);

                playerHeight = div.select("dt:contains(신장/체중) + dd").text();
                heightList.add(playerHeight);

                Element img = doc.selectFirst(".plyrImg img");
                playerImg = img.attr("src");
                imgList.add(playerImg);

                System.out.println("이미지 링크: " + imgList.get(i));
                System.out.println("선수명: " + nameList.get(i));
                System.out.println("등번호: " + numList.get(i));
                System.out.println("생년월일: " + birthList.get(i));
                System.out.println("포지션: " + positionList.get(i));
                System.out.println("신장/체중: " + heightList.get(i));
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
