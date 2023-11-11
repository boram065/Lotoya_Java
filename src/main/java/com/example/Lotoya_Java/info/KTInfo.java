package com.example.Lotoya_Java.info;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class KTInfo {
    public static ArrayList<String> imgList = new ArrayList<>();
    public static ArrayList<String> numList = new ArrayList<>();
    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> birthList = new ArrayList<>();
    public static ArrayList<String> positionList = new ArrayList<>();
    public static ArrayList<String> heightList = new ArrayList<>();

    public static void main(String[] args) {
        String pitcher1 = "https://www.ktwiz.co.kr/player/pitcher/detail?pcode=";
        String pitcher2[] = {"53006"};
        String imgLink = "https://www.ktwiz.co.kr/player/pitcher";

        try {
            for (int i = 0; i < pitcher2.length; i++) {
                Document doc = Jsoup.connect(pitcher1 + pitcher2[i]).get();
                Element article = doc.select("article.player_info_content").first();
                Element dl = article.select("div dl.player_info").first();

                String playerImg = "";
                String playerName = "";
                String playerNumber = "";
                String playerBirth = "";
                String playerPosition = "";
                String playerHeight = "";

                Element dt = dl.select("dt").first();
                String text = dt.text();
                playerNumber = text.replaceAll("\\D", "");
                numList.add(playerNumber);

                playerName = dt.ownText();
                nameList.add(playerName);

                Element span = dl.select("span.position").first();
                String number = span.text();
                int index = number.indexOf("(");
                playerPosition = number.substring(0, index);
                positionList.add(playerPosition);

                // 생년월일, 신장/체중
                Element dd = dl.select("dd.info_list_wrap").first();

                Element birth = dd.select("dl dt:contains(생년월일) + dd").first();
                playerBirth = birth.text();
                birthList.add(playerBirth);

                Element body = dd.select("dl dt:contains(체격) + dd").first();
                playerHeight = body.text();
                heightList.add(playerHeight);

                // 이미지
                Document imgDoc = Jsoup.connect(imgLink).get();
                Element box = imgDoc.select(".article").first();
                Element imgDiv = box.select(".tit_img").first();
                playerImg = imgDiv.attr("src");
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
