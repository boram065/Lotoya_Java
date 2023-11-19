package com.example.Lotoya_Java.info;

import com.example.Lotoya_Java.entity.Player;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KIAInfo {
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
        String pitcher1 = "https://tigers.co.kr/players/pitcher/";
        String pitcher2[] = {"67604"}; // , "53609"
        String pitcherImg = "https://tigers.co.kr/players/pitcher";

        try {
            for(int i = 0; i < pitcher2.length; i++) {
                Document doc = Jsoup.connect(pitcher1 + pitcher2[i]).get();
                System.out.println(doc);

//                String playerImg = "";
//                String playerName = "";
//                String playerNumber = "";
//                String playerBirth = "";
//                String playerPosition = "";
//                String playerHeight = "";
//                String playerWeight = "";
//
//                Element position = doc.select("div.plyr_view01In > p").first();
//                String positionStr = position.text();
//                int index = positionStr.indexOf("/");
//                playerPosition = positionStr.substring(0, index).trim();
//                positionList.add(playerPosition);
//
//                Element name = doc.select("div.plyr_view01In > h5").first();
//                playerName = name.ownText();
//                nameList.add(playerName);
//
//                Element number = doc.select("div.plyr_view01In > strong").first();
//                playerNumber = number.text();
//                numList.add(playerNumber);
//
//                playerBirth = doc.select("div.plyr_view01In dl dt:contains(생년월일) + dd").text();
//                birthList.add(playerBirth);
//
//                String body = doc.select("div.plyr_view01In dl dt:contains(신장/체중) + dd").text();
//                int bodyIndex = body.indexOf("/");
//
//                playerHeight = body.substring(0, bodyIndex-3).replaceAll("[^0-9]","");
//                heightList.add(Integer.parseInt(playerHeight));
//
//                playerWeight = body.substring(bodyIndex+2).replaceAll("[^0-9]","");
//                weightList.add(Integer.parseInt(playerWeight));
//
//                Document imgDoc = Jsoup.connect(pitcherImg).get();
//                Element img = imgDoc.select(".plyrImg").first();
//                Elements divs = img.select("li");
//                for (Element row : divs) {
//                    Element a = row.select("a").first();
//                    playerImg = a.attr("src");
//                    imgList.add(playerImg);
//                }
//
            }
//
//            for(int i = 0; i < pitcher2.length; i++) {
//                if (i < imgList.size()) {
//                    System.out.println("이미지 링크: " + imgList.get(i));
//                    System.out.println("선수명: " + nameList.get(i));
//                    System.out.println("등번호: " + numList.get(i));
//                    System.out.println("생년월일: " + birthList.get(i));
//                    System.out.println("포지션: " + positionList.get(i));
//                    System.out.println("신장/체중: " + heightList.get(i));
//                    System.out.println();
//                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
