package com.example.Lotoya_Java.info;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DoosanInfo {
    public static ArrayList<Integer> idList = new ArrayList<>();
    public static ArrayList<String> clubList = new ArrayList<>();
    public static ArrayList<String> imgList = new ArrayList<>();
    public static ArrayList<Integer> numList = new ArrayList<>();
    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> birthList = new ArrayList<>();
    public static ArrayList<String> positionList = new ArrayList<>();
    public static ArrayList<Integer> heightList = new ArrayList<>();
    public static ArrayList<Integer> weightList = new ArrayList<>();
    public static ArrayList<Integer> priceList = new ArrayList<>();

    public static void main(String[] args) {
        String pitcher1 = "https://www.doosanbears.com/players/pitchers/";
        String pitcher2[] = {"98", "296", "251", "117", "49", "68", "311", "348", "359", "257",
                            "313", "336", "99", "113", "387", "385", "97", "258", "112", "342"};
        String batters1 = "https://www.doosanbears.com/players/batters/";
        String batters2[] = {"34", "343", "302", "107", "345", "261", "104", "370", "260", "64",
                "259", "30", "286", "355", "27", "377", "307", "82", "246", "308"};

        try {
            for (int i = 0; i < pitcher2.length; i++) {
                Document doc = Jsoup.connect(pitcher1 + pitcher2[i]).get();
                Element table = doc.select("table.tableBWrite1").first();
                Elements tr = table.select("tr");

                String playerImg = "";
                String playerName = "";
                int playerNumber;
                String playerBirth = "";
                String playerPosition = "";
                int playerHeight;
                int playerWeight;

                // 이미지 링크 불러오기
                Element imgElement = doc.select("div.photo_r > img").first();
                playerImg = imgElement.attr("src");
                imgList.add(playerImg);

                playerName = tr.select("th:contains(선수명) + td").text();
                nameList.add(playerName);

                playerNumber = Integer.parseInt(tr.select("th:contains(등번호) + td").text());
                numList.add(playerNumber);

                playerBirth = tr.select("th:contains(생년월일) + td").text();
                birthList.add(playerBirth);

                playerPosition = tr.select("th:contains(포지션) + td").text();
                positionList.add(playerPosition);

                String body = tr.select("th:contains(신장/체중) + td").text();
                int index = body.indexOf("/");
                playerHeight = Integer.parseInt(body.substring(0, index-3).replaceAll("[^0-9]",""));
                heightList.add(playerHeight);
                playerWeight = Integer.parseInt(body.substring(index+2).replaceAll("[^0-9]",""));
                weightList.add(playerWeight);
            }

            for (int i = 0; i < batters2.length; i++) {
                Document doc = Jsoup.connect(batters1 + batters2[i]).get();
                Element table = doc.select("table.tableBWrite1").first();
                Elements tr = table.select("tr");

                String playerImg = "";
                String playerName = "";
                int playerNumber;
                String playerBirth = "";
                String playerPosition = "";
                int playerHeight;
                int playerWeight;

                // 이미지 링크 불러오기
                Element imgElement = doc.select("div.photo_r > img").first();
                playerImg = imgElement.attr("src");
                imgList.add(playerImg);

                playerName = tr.select("th:contains(선수명) + td").text();
                nameList.add(playerName);

                playerNumber = Integer.parseInt(tr.select("th:contains(등번호) + td").text());
                numList.add(playerNumber);

                playerBirth = tr.select("th:contains(생년월일) + td").text();
                birthList.add(playerBirth);

                playerPosition = tr.select("th:contains(포지션) + td").text();
                positionList.add(playerPosition);

                String body = tr.select("th:contains(신장/체중) + td").text();
                int index = body.indexOf("/");
                playerHeight = Integer.parseInt(body.substring(0, index-3).replaceAll("[^0-9]",""));
                heightList.add(playerHeight);
                playerWeight = Integer.parseInt(body.substring(index+2).replaceAll("[^0-9]",""));
                weightList.add(playerWeight);

            }

            int length = pitcher2.length + batters2.length;
            for (int i = 0; i < length; i++) {
                idList.add(i+1);
                clubList.add("두산");
                priceList.add(numList.get(i) * 100);

//                System.out.println("id: " + idList.get(i));
//                System.out.println("팀 소속: " + clubList.get(i));
//                System.out.println("이미지 링크: " + imgList.get(i));
//                System.out.println("선수명: " + nameList.get(i));
//                System.out.println("등번호: " + numList.get(i));
//                System.out.println("생년월일: " + birthList.get(i));
//                System.out.println("포지션: " + positionList.get(i));
//                System.out.println("신장: " + heightList.get(i));
//                System.out.println("체중: " + weightList.get(i));
//                System.out.println("가격: " + priceList.get(i));
//                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}