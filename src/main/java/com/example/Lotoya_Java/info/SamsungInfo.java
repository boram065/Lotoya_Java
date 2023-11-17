package com.example.Lotoya_Java.info;

import com.example.Lotoya_Java.entity.Player;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SamsungInfo {
    public static List<Player> players = new ArrayList<>();
    public static ArrayList<String> clubList = new ArrayList<>();
    public static ArrayList<String> imgList = new ArrayList<>();
    public static ArrayList<Integer> numList = new ArrayList<>();
    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> birthList = new ArrayList<>();
    public static ArrayList<String> positionList = new ArrayList<>();
    public static ArrayList<Integer> heightList = new ArrayList<>();
    public static ArrayList<Integer> weightList = new ArrayList<>();
    public static ArrayList<Integer> priceList = new ArrayList<>();

    public static List<Player> convertToPlayers() {
        String pitcher1 = "https://www.samsunglions.com/roster/roster_2.asp?pcode=";
        // 투수 다 함
        String pitcher2[] = { "A0432", "A0334", "77446", "A0195", "A0281", "A0433", "A0418", "A0419", "A0332", "A0513",
                            "A0084", "A0516", "A0360", "A0315", "A0191", "A0357", "A0236", "A0313", "A0314",
                            "A0368", "A0477", "A0405", "A0363", "A0369", "A0362", "A0399", "A0426", "A0422", "A0421",
                            "A0420", "A0459", "A0458", "A0454", "A0476", "A0469", "A0470", "A0504", "A0502", "A0505",
                            "A0501", "A0500", "A0503" };

        String batters1 = "https://www.samsunglions.com/roster/roster_2.asp?pcode=";
        String batters2[] = { "A0448", "A0338", "A0245", "A0248", "A0515", "A0365", "A0133",
                "A0449", "A0404", "A0466", "A0373", "A0452", "A0428", "A0468", "A0473", "A0507",
                "21108", "A0447", "A0398", "A0033", "A0098", "A0512", "A0326", "A0366", "A0451",
                "A0472", "A0510", "A0354", "A0174", "A0464", "A0427", "A0401", "A0511" };

        String imgLink1 = "https://www.samsunglions.com/roster/roster_2_list.asp";
        String imgLink2 = "https://www.samsunglions.com/roster/roster_3_list.asp";

        try {
            for(int i = 0; i < pitcher2.length; i++) {
                Document doc = Jsoup.connect(pitcher1 + pitcher2[i]).get();
                Element div = doc.select("div.keyvisualBg").first();
                Element strong = div.select("strong.t").first();
                Element divTxt = doc.select("div.txt").first();

                String playerImg = "";
                String playerName = "";
                String playerNumber = "";
                String playerBirth = "";
                String playerPosition = "";
                String playerHeight = "";
                String playerWeight = "";

                // 등번호
                playerNumber = strong.select("span").text();
                numList.add(Integer.parseInt(playerNumber));
                
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

                String body = content.substring(content.indexOf("키/몸무게 : ") + 8, content.indexOf("경력")).trim();
                int heightIndex = body.indexOf("c");
                playerHeight = body.substring(0, heightIndex);
                heightList.add(Integer.parseInt(playerHeight));

                int weightIndex = body.indexOf("k");
                playerWeight = body.substring(heightIndex+3, weightIndex).trim();
                weightList.add(Integer.parseInt(playerWeight));

                // 이미지 링크
                Document imgDoc1 = Jsoup.connect(imgLink1).get();
                String targetAlt = playerPosition + playerNumber + playerName;
                Element imgDiv = imgDoc1.select(".mList3 li .img img[alt=" + targetAlt + "]").first();
                String img = imgDiv.attr("src");
                playerImg = "https://www.samsunglions.com/" + img;
                imgList.add(playerImg);
            }

            for (int i = 0; i < batters2.length; i++) {
                Document doc = Jsoup.connect(batters1 + batters2[i]).get();
                Element div = doc.select("div.keyvisualBg").first();
                Element strong = div.select("strong.t").first();
                Element divTxt = doc.select("div.txt").first();

                String playerImg = "";
                String playerName = "";
                String playerNumber = "";
                String playerBirth = "";
                String playerPosition = "";
                String playerHeight = "";
                String playerWeight = "";

                // 등번호
                playerNumber = strong.select("span").text();
                numList.add(Integer.parseInt(playerNumber));

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

                String body = content.substring(content.indexOf("키/몸무게 : ") + 8, content.indexOf("경력")).trim();
                int heightIndex = body.indexOf("c");
                playerHeight = body.substring(0, heightIndex);
                heightList.add(Integer.parseInt(playerHeight));

                int weightIndex = body.indexOf("k");
                playerWeight = body.substring(heightIndex+3, weightIndex).trim();
                weightList.add(Integer.parseInt(playerWeight));

                // 이미지 링크
                Document imgDoc1 = Jsoup.connect(imgLink2).get();
                String targetAlt = playerPosition + playerNumber + playerName;
                Element imgDiv = imgDoc1.select(".mList3 li .img img[alt=" + targetAlt + "]").first();
                String img = imgDiv.attr("src");
                playerImg = "https://www.samsunglions.com/" + img;
                imgList.add(playerImg);
            }

            for (int i = 0; i < numList.size(); i++) {
                clubList.add("삼성");
                priceList.add(numList.get(i) * 10);

                Player playerEntity = new Player();
                playerEntity.setBackNum(numList.get(i));
                playerEntity.setHeight(heightList.get(i));
                playerEntity.setPrice(priceList.get(i));
                playerEntity.setWeight(weightList.get(i));
                playerEntity.setBirth(birthList.get(i));
                playerEntity.setClub(clubList.get(i));
                playerEntity.setImgLink(imgList.get(i));
                playerEntity.setName(nameList.get(i));
                playerEntity.setPosition(positionList.get(i));

                players.add(playerEntity);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }
}
