package com.example.Java_Lotoya.info;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DoosanInfo {
    public static ArrayList<String> imgList;
    public static ArrayList<String> numList;
    public static ArrayList<String> nameList;
    public static ArrayList<String> birthList;
    public static ArrayList<String> positionList;
    public static ArrayList<String> heightList;

    public static void main(String[] args) {
        String pitcher1 = "https://www.doosanbears.com/players/pitchers/";
        String pitcher2[] = {"98", "296", "251", "117", "49", "68", "311", "348", "359", "257", "313", "336"}; // 알칸타라까지 함

        ArrayList<String> imgList = new ArrayList<>();
        ArrayList<String> numList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> birthList = new ArrayList<>();
        ArrayList<String> positionList = new ArrayList<>();
        ArrayList<String> heightList = new ArrayList<>();

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

                System.out.println("이미지 링크 : " + imgList.get(i));
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