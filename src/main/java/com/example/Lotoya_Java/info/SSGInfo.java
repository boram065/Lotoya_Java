package com.example.Lotoya_Java.info;

import com.example.Lotoya_Java.entity.Player;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SSGInfo {
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
        String player1 = "https://www.ssglanders.com/players/";
        String player2[] = {"616", "617", "599", "659", "251", "698", "309", "626",
                            "657", "625", "639", "100", "538", "600", "588", "572", "653", "76",
                            "419", "589", "527", "601", "611", "60", "557", "630",
                            "258", "699", "638", "645", "542", "571", "683", "465", "259"};

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
                numList.add(Integer.parseInt(playerNumber));

                playerName = div.select(".txt-box").text();
                nameList.add(playerName);

                Elements img = div.select(".box-img-file img");
                playerImg = img.attr("src");
                imgList.add(playerImg);

                playerBirth = tr.select("th:contains(생년월일) + td").text();
                birthList.add(playerBirth);

                String height = tr.select("th:contains(신장) + td").text();
                int heightIndex = height.indexOf("c");
                playerHeight = height.substring(0, heightIndex);
                heightList.add(Integer.parseInt(playerHeight));

                String weight = tr.select("th:contains(체중) + td").text();
                int weightIndex = weight.indexOf("k");
                playerWeight = weight.substring(0, weightIndex);
                weightList.add(Integer.parseInt(playerWeight));

            }

            for (int i = 0; i < numList.size(); i++) {
                clubList.add("SSG");
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
