package com.example.Lotoya_Java.info;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DoosanInfo {
    public static ArrayList<String> imgList = new ArrayList<>();
    public static ArrayList<String> numList = new ArrayList<>();
    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> birthList = new ArrayList<>();
    public static ArrayList<String> positionList = new ArrayList<>();
    public static ArrayList<String> heightList = new ArrayList<>();

    public static void main(String[] args) {
        String pitcher1 = "https://www.doosanbears.com/players/pitchers/";
        String pitcher2[] = {"98", "296", "251", "117", "49", "68", "311", "348", "359", "257",
                            "313", "336", "99", "113", "387", "385", "97", "258", "112", "342"};

        try {
            for (int i = 0; i < pitcher2.length; i++) {
                Document doc = Jsoup.connect(pitcher1 + pitcher2[i]).get();
                Element table = doc.select("table.tableBWrite1").first();
                Elements tr = table.select("tr");

                // 이미지 링크 불러오기
                Element imgElement = doc.select("div.photo_r > img").first();
                String playerImg = imgElement.attr("src");
                imgList.add(playerImg);
                
                String playerName = "";
                String playerNumber = "";
                String playerBirth = "";
                String playerPosition = "";
                String playerHeight = "";

                for (Element row : tr) {
                    Element content = row.select("th").first();
                    Element dataCell = row.select("td").first();

                    if (content.text().equals("선수명")) {
                        playerName = dataCell.text();
                        nameList.add(playerName);
                    }

                    if (content.text().equals("등번호")) {
                        playerNumber = dataCell.text();
                        numList.add(playerNumber);
                    }

                    if (content.text().equals("생년월일")) {
                        playerBirth = dataCell.text();
                        birthList.add(playerBirth);
                    }

                    if (content.text().equals("포지션")) {
                        playerPosition = dataCell.text();
                        positionList.add(playerPosition);
                    }

                    if (content.text().equals("신장/체중")) {
                        playerHeight = dataCell.text();
                        heightList.add(playerHeight);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



        String batters1 = "https://www.doosanbears.com/players/batters/";
        String batters2[] = {"34", "343", "302", "107", "345", "261", "104", "370", "260", "64",
                            "259", "30", "286", "355", "27", "377", "307", "82", "246", "308"};
        try {
            for (int i = 0; i < batters2.length; i++) {
                Document doc = Jsoup.connect(batters1 + batters2[i]).get();
                Element table = doc.select("table.tableBWrite1").first();
                Elements tr = table.select("tr");

                // 이미지 링크 불러오기
                Element imgElement = doc.select("div.photo_r > img").first();
                String playerImg = imgElement.attr("src");
                imgList.add(playerImg);

                String playerName = "";
                String playerNumber = "";
                String playerBirth = "";
                String playerPosition = "";
                String playerHeight = "";

                for (Element row : tr) {
                    Element content = row.select("th").first();
                    Element dataCell = row.select("td").first();

                    if (content.text().equals("선수명")) {
                        playerName = dataCell.text();
                        nameList.add(playerName);
                    }

                    if (content.text().equals("등번호")) {
                        playerNumber = dataCell.text();
                        numList.add(playerNumber);
                    }

                    if (content.text().equals("생년월일")) {
                        playerBirth = dataCell.text();
                        birthList.add(playerBirth);
                    }

                    if (content.text().equals("포지션")) {
                        playerPosition = dataCell.text();
                        positionList.add(playerPosition);
                    }

                    if (content.text().equals("신장/체중")) {
                        playerHeight = dataCell.text();
                        heightList.add(playerHeight);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        int length = pitcher2.length + batters2.length;
        for (int i = 0; i < length; i++) {
            System.out.println("이미지 링크 : " + imgList.get(i));
            System.out.println("선수명: " + nameList.get(i));
            System.out.println("등번호: " + numList.get(i));
            System.out.println("생년월일: " + birthList.get(i));
            System.out.println("포지션: " + positionList.get(i));
            System.out.println("신장/체중: " + heightList.get(i));
            System.out.println();
        }
    }
}