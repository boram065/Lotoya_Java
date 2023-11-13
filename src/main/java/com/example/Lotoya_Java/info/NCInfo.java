package com.example.Lotoya_Java.info;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class NCInfo {
    public static ArrayList<String> imgList = new ArrayList<>();
    public static ArrayList<String> numList = new ArrayList<>();
    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> birthList = new ArrayList<>();
    public static ArrayList<String> positionList = new ArrayList<>();
    public static ArrayList<String> heightList = new ArrayList<>();
    public static ArrayList<String> weightList = new ArrayList<>();

    public static void main(String[] args) {
        String pitcher1 = "https://www.ncdinos.com/player/pitcher/view.do?playerId=";
        String pitcher2[] = {"591"};
        String imgStr = "https://www.ncdinos.com/player/pitcher/list.do";

        try {
            for (int i = 0 ; i < pitcher2.length; i++) {
                Document doc = Jsoup.connect(pitcher1 + pitcher2[i]).get();
                Element top = doc.select(".player-view").first();

                Element div = top.select(".txt-wrap").first();
                Element table = top.select(".table-wrap").first();
                Element tr = table.select("tr").first();

                String playerImg = "";
                String playerName = "";
                String playerNumber = "";
                String playerBirth = "";
                String playerPosition = "";
                String playerHeight = "";
                String playerWeight = "";

                // 등번호
                String num = div.select("span").text();
                int index = num.indexOf(".");
                playerNumber = num.substring(index+1, index+2);
                numList.add(playerNumber);

                // 이름
                playerName = div.select(".txt-primary").text();
                nameList.add(playerName);

                // 포지션
                playerPosition = tr.select("th:contains(보직) + td").text();
                positionList.add(playerPosition);

                // 생년월일
                playerBirth = tr.select("th:contains(생년월일) + td").text();
                birthList.add(playerBirth);

                // 키, 몸무게
                String body = tr.select("th:contains(신장/체중/혈액형) + td").text();
                heightList.add(body);

                // 이미지
                Document imgDoc = Jsoup.connect(imgStr).get();
                Element ul = imgDoc.select("ul.player-list").first();
                Element li = ul.select("li").first();
                Element a = li.select("a").first();

                playerImg = a.attr("src");
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
