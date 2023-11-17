package com.example.Lotoya_Java.info;

import com.example.Lotoya_Java.entity.Player;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KTInfo {
    public static List<Player> players = new ArrayList<>();
    public static ArrayList<String> imgList = new ArrayList<>();
    public static ArrayList<Integer> numList = new ArrayList<>();
    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> birthList = new ArrayList<>();
    public static ArrayList<String> positionList = new ArrayList<>();
    public static ArrayList<Integer> heightList = new ArrayList<>();
    public static ArrayList<Integer> weightList = new ArrayList<>();
    public static ArrayList<Integer> priceList = new ArrayList<>();

    public static void main(String[] args) {
        String pitcher1 = "https://www.ktwiz.co.kr/player/pitcher/detail?pcode=";
        String pitcher2[] = { "53006" };
        String imgLink = "https://www.ktwiz.co.kr/player/pitcher";

        try {
            for (int i = 0; i < pitcher2.length; i++) {
                Document doc = Jsoup.connect(pitcher1 + pitcher2[i]).get();
                if (doc != null) {
                    System.out.println("doc 있음!");
                } else {
                    System.out.println("doc 없음!");
                }

                String playerImg = "";
                String playerName = "";
                String playerNumber = "";
                String playerBirth = "";
                String playerPosition = "";
                String playerHeight = "";
                String playerWeight = "";

                Elements element = doc.select("dt");
                Element number = element.select(".back_num").first();
                if (number != null) {
                    playerNumber = number.text().replace("No. ", "").trim();
                    System.out.println("playerNumber 있음");
                    System.out.println("등번호 : " + playerNumber);
                } else {
                    System.out.println("playerNumber 없음");
                }
//                numList.add(Integer.parseInt(playerNumber));

                playerName = element.select("#text").text();
                if (!playerName.isEmpty()) {
                    System.out.println("playerName 있음");
                    System.out.println("이름:" + playerName);
                } else {
                    System.out.println("playerName 없음");
                }
                nameList.add(playerName);

                // 포지션
//                Element position = doc.select(".page_location").first();
//                Element span = position.select("span.here").first();
//                String p = span.text();
//                int positionIndex = p.indexOf("(");
//                playerPosition = p.substring(0, positionIndex);
//                positionList.add(playerPosition);
//                Element positionElement = doc.select(".container.player_pitcher_detail .position_maker .page_location .here").first();
//                if (positionElement != null) {
//                    playerPosition = positionElement.text();
//                    System.out.println("포지션 있음");
//                } else {
//                    System.out.println("포지션을 찾을 수 없습니다.");
//                }

                playerPosition = doc.select(".dep3.active").text();
                if (playerPosition != null) {
                    System.out.println("포지션 있음");
                } else {
                    System.out.println("포지션 없음");
                }
                positionList.add(playerPosition);

                // 생년월일
                Element birth = doc.select(".birthday dd").first();
                playerBirth = birth.text().trim();
                birthList.add(playerBirth);
                if (birth != null) {
                    System.out.println("birth 있음");
                } else {
                    System.out.println("birth 없음");
                }

                Element body = doc.select(".build dd").first();
                String bodyStr = body.text().trim();

                // 신장
                int heightIndex = bodyStr.indexOf("c");
                playerHeight = bodyStr.substring(0, heightIndex);
                heightList.add(Integer.parseInt(playerHeight));

                // 체중
                int weightIndex = bodyStr.indexOf("k");
                playerWeight = bodyStr.substring(heightIndex+1, weightIndex).trim();
                weightList.add(Integer.parseInt(playerWeight));

                // 이미지
                Document imgDoc = Jsoup.connect(imgLink).get();
                Element box = imgDoc.select(".article").first();
                Element imgDiv = box.select(".tit_img[alt=NO." + playerNumber + " " + playerName + "]").first();
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
