package com.example.Lotoya_Java.info;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
public class SamsungInfo {
    public static void main(String[] args) {
        String pitcher1 = "https://www.samsunglions.com/roster/roster_2.asp?pcode=";
        // 투수 다 함
        String pitcher2[] = {"A0432", "A0334", "77446", "A0195", "A0281", "A0433", "A0418", "A0419", "A0332", "A0513",
                            "A0084", "A0516", "A0360", "A0315", "A0191", "A0357", "A0236", "A0312", "A0313", "A0314",
                            "A0368", "A0477", "A0405", "A0363", "A0369", "A0362", "A0399", "A0426", "A0422", "A0421",
                            "A0420", "A0459", "A0458", "A0454", "A0476", "A0469", "A0470", "A0504", "A0502", "A0505",
                            "A0501", "A0500", "A0503", "A0448"};

        ArrayList<String> numList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> birthList = new ArrayList<>();
        ArrayList<String> positionList = new ArrayList<>();
        ArrayList<String> heightList = new ArrayList<>();

//        JsonArray playersArray = new JsonArray();

        try {
            for(int i = 0; i < pitcher2.length; i++) {
                Document doc = Jsoup.connect(pitcher1 + pitcher2[i]).get();
                Element div = doc.select("div.keyvisualBg").first();
                Element strong = div.select("strong.t").first();
                Element divTxt = doc.select("div.txt").first();

                String playerName = "";
                String playerNumber = "";
                String playerBirth = "";
                String playerPosition = "";
                String playerHeight = "";

                // 등번호
                playerNumber = strong.select("span").text();
                numList.add(playerNumber);
                
                // 선수명
                playerName = strong.ownText().trim();
                nameList.add(playerName);

                // 포지션
                String position = divTxt.select("em.s").text();
                int index = position.indexOf(" ");
                playerPosition = position.substring(0, index-1);
                positionList.add(playerPosition);

                // 생년월일, 키/몸무게
                String content = divTxt.select("p").text();
                playerBirth = content.substring(content.indexOf("생년월일 : ") + 6, content.indexOf("키/몸무게")).trim();
                birthList.add(playerBirth);

                playerHeight = content.substring(content.indexOf("키/몸무게 : ") + 8, content.indexOf("경력")).trim();
                heightList.add(playerHeight);

//                // 각 선수 정보를 JSON 객체로 생성하여 배열에 추가
//                JsonObject playerObject = new JsonObject();
//                playerObject.addProperty("playerName", nameList.get(i));
//                playerObject.addProperty("playerNumber", numList.get(i));
//                playerObject.addProperty("playerPosition", positionList.get(i));
//                playerObject.addProperty("playerBirth", birthList.get(i));
//                playerObject.addProperty("playerHeight", heightList.get(i));
//                playersArray.add(playerObject);

                System.out.println("선수명 : " + nameList.get(i));
                System.out.println("등번호 : " + numList.get(i));
                System.out.println("포지션 : " + positionList.get(i));
                System.out.println("생년월일 : " + birthList.get(i));
                System.out.println("키/몸무게 : " + heightList.get(i));
                System.out.println();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        // 모든 선수 정보를 JSON 배열로 묶어서 출력
//        JsonObject result = new JsonObject();
//        result.add("players", playersArray);
//        String jsonData = new Gson().toJson(result);
//        System.out.println(jsonData);
    }
}
