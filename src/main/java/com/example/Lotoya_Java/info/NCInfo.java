package com.example.Lotoya_Java.info;

import com.example.Lotoya_Java.entity.Player;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NCInfo {
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
        String pitcher1 = "https://www.ncdinos.com/player/pitcher/view.do?playerId=";
        String pitcher2[] = {"591", "348", "403", "625", "255", "347", "619", "227", "346",
                "578", "18", "108", "594", "194", "402", "198", "539",
                "19", "360", "286", "450", "193", "605"};

        String catcher1 = "https://www.ncdinos.com/player/catcher/view.do?playerId=";
        String catcher2[] = {"627", "170", "629", "611", "597", "352", "628"};

        String infielder1 = "https://www.ncdinos.com/player/infielder/view.do?playerId=";
        String infielder2[] = {"42", "261", "410", "630", "536", "236", "217", "411", "355",
                "609", "356", "543", "537", "456", "632", "649", "472", "633",
                "631"};

        String outfielder1 = "https://www.ncdinos.com/player/outfielder/view.do?playerId=";
        String outfielder2[] = {"579", "267", "226", "52", "592", "593", "541", "634", "635", "412", "636"};

        try {
            for (int i = 0; i < pitcher2.length; i++) {
                Document doc = Jsoup.connect(pitcher1 + pitcher2[i]).get();
                Element top = doc.select(".player-view").first();

                Element div = top.select(".txt-wrap").first();
                Element table = top.select(".table-wrap").first();
                Elements tr = table.select("tr");

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
                playerNumber = num.substring(index + 1).replaceAll("[^\\d]", "");
                numList.add(Integer.parseInt(playerNumber));

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

                // 키
                int heightIndex = body.indexOf("c");
                playerHeight = body.substring(0, heightIndex);
                heightList.add(Integer.parseInt(playerHeight));

                // 체중
                int weightIndex = body.indexOf("k");
                playerWeight = body.substring(heightIndex + 4, weightIndex).trim();
                weightList.add(Integer.parseInt(playerWeight));

                // 이미지
                playerImg = "https://ncdinos-homepage.s3.ap-northeast-2.amazonaws.com/v1/player/2023/" + playerNumber + "_1.png";
                imgList.add(playerImg);
            }

            for (int i = 0; i < catcher2.length; i++) {
                Document doc = Jsoup.connect(catcher1 + catcher2[i]).get();
                Element top = doc.select(".player-view").first();

                Element div = top.select(".txt-wrap").first();
                Element table = top.select(".table-wrap").first();
                Elements tr = table.select("tr");

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
                playerNumber = num.substring(index + 1).replaceAll("[^\\d]", "");
                numList.add(Integer.parseInt(playerNumber));

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

                // 키
                int heightIndex = body.indexOf("c");
                playerHeight = body.substring(0, heightIndex);
                heightList.add(Integer.parseInt(playerHeight));

                // 체중
                int weightIndex = body.indexOf("k");
                playerWeight = body.substring(heightIndex + 4, weightIndex).trim();
                weightList.add(Integer.parseInt(playerWeight));

                // 이미지
                playerImg = "https://ncdinos-homepage.s3.ap-northeast-2.amazonaws.com/v1/player/2023/" + playerNumber + "_1.png";
                imgList.add(playerImg);
            }

            for (int i = 0; i < infielder2.length; i++) {
                Document doc = Jsoup.connect(infielder1 + infielder2[i]).get();
                Element top = doc.select(".player-view").first();

                Element div = top.select(".txt-wrap").first();
                Element table = top.select(".table-wrap").first();
                Elements tr = table.select("tr");

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
                playerNumber = num.substring(index + 1).replaceAll("[^\\d]", "");
                numList.add(Integer.parseInt(playerNumber));

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

                // 키
                int heightIndex = body.indexOf("c");
                playerHeight = body.substring(0, heightIndex);
                heightList.add(Integer.parseInt(playerHeight));

                // 체중
                int weightIndex = body.indexOf("k");
                playerWeight = body.substring(heightIndex + 4, weightIndex).trim();
                weightList.add(Integer.parseInt(playerWeight));

                // 이미지
                playerImg = "https://ncdinos-homepage.s3.ap-northeast-2.amazonaws.com/v1/player/2023/" + playerNumber + "_1.png";
                imgList.add(playerImg);
            }

            for (int i = 0; i < outfielder2.length; i++) {
                Document doc = Jsoup.connect(outfielder1 + outfielder2[i]).get();
                Element top = doc.select(".player-view").first();

                Element div = top.select(".txt-wrap").first();
                Element table = top.select(".table-wrap").first();
                Elements tr = table.select("tr");

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
                playerNumber = num.substring(index + 1).replaceAll("[^\\d]", "");
                numList.add(Integer.parseInt(playerNumber));

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

                // 키
                int heightIndex = body.indexOf("c");
                playerHeight = body.substring(0, heightIndex);
                heightList.add(Integer.parseInt(playerHeight));

                // 체중
                int weightIndex = body.indexOf("k");
                playerWeight = body.substring(heightIndex + 4, weightIndex).trim();
                weightList.add(Integer.parseInt(playerWeight));

                // 이미지
                playerImg = "https://ncdinos-homepage.s3.ap-northeast-2.amazonaws.com/v1/player/2023/" + playerNumber + "_1.png";
                imgList.add(playerImg);
            }

            for (int i = 0; i < numList.size(); i++) {
                clubList.add("NC");
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