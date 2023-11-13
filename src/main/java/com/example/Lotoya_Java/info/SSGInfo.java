package com.example.Lotoya_Java.info;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class SSGInfo {
    public static ArrayList<String> imgList = new ArrayList<>();
    public static ArrayList<String> numList = new ArrayList<>();
    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> birthList = new ArrayList<>();
    public static ArrayList<String> positionList = new ArrayList<>();
    public static ArrayList<String> heightList = new ArrayList<>();
    public static ArrayList<String> weightList = new ArrayList<>();

    public static void main(String[] args) {
        String player1 = "https://www.ssglanders.com/players/";
        String player2[] = {"607", "616", "617", "599", "659", "251", "176", "698", "309", "626",
                            "657", "625", "639", "100", "538", "600", "588", "572", "653", "76",
                            "419", "589", "640", "527", "601", "591", "611", "60", "557", "630",
                            "258", "699", "638", "590", "645", "542", "683", "571", "683", "571",
                            "465", "259"};

        try {
            for (int i = 0; i < player2.length; i++) {
                Document doc = Jsoup.connect(player1 + player2[i]).get();
                Element top = doc.select(".wrapper-body").first();
                Elements divs = top.select("div");
                Elements div = divs.select(".list-col");
                Element table = divs.select("#tb-player-info").first();
                Elements tr = table.select("tr");

                String playerImg = "";
                String playerName = "";
                String playerNumber = "";
                String playerBirth = "";
                String playerPosition = "";
                String playerHeight = "";
                String playerWeight = "";

                playerPosition = div.select(".txt-subPosition").text();
                positionList.add(playerPosition);

                String number = div.select(".box-backNo").text();
                int index = number.indexOf(".");
                playerNumber = number.substring(index+1);
                numList.add(playerNumber);

                playerName = div.select(".txt-box").text();
                nameList.add(playerName);

                Elements img = div.select(".box-img-file img");
                playerImg = img.attr("src");
                imgList.add(playerImg);

                playerBirth = tr.select("th:contains(생년월일) + td").text();
                birthList.add(playerBirth);

                playerHeight = tr.select("th:contains(신장) + td").text();
                heightList.add(playerHeight);

                playerWeight = tr.select("th:contains(체중) + td").text();
                weightList.add(playerWeight);

                String heightWeight = playerHeight + " / " + playerWeight;
                heightList.add(heightWeight);

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
