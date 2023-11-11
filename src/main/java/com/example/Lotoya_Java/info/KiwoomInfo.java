package com.example.Lotoya_Java.info;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class KiwoomInfo {
    public static ArrayList<String> imgList = new ArrayList<>();
    public static ArrayList<String> numList = new ArrayList<>();
    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> birthList = new ArrayList<>();
    public static ArrayList<String> positionList = new ArrayList<>();
    public static ArrayList<String> heightList = new ArrayList<>();

    public static void main(String[] args) {
        String pitcher1 = "https://www.heroesbaseball.co.kr/players/pitcher/view.do?num=";
        String pitcher2[] = {"448", "473", "393", "332", "430", "341", "453", "406", "428"};
        String catcher1 = "https://www.heroesbaseball.co.kr/players/catcher/view.do?num=";
        String catcher2[] = {"455", "411", "153", "431", "361", "461", "462"};
        String infielder1 = "https://www.heroesbaseball.co.kr/players/infielder/view.do?num=";
        String infielder2[] ={"337", "284", "375", "443", "315", "407", "470", "239", "395", "456",
                              "457", "465", "401", "464"};
        String outfielder1 = "https://www.heroesbaseball.co.kr/players/outfielder/view.do?num=";
        String outfielder2[] = {"471", "424", "390", "314", "472", "459", "205", "207", "436", "371", "410"};

        try {
            for (int i = 0; i < pitcher2.length; i++) {
                Document doc = Jsoup.connect(pitcher1 + pitcher2[i]).get();
                Element div = doc.select(".playerInfo").first();
                Element n = div.select(".summary").first();

                String playerImg = "";
                String playerName = "";
                String playerNumber = "";
                String playerBirth = "";
                String playerPosition = "";
                String playerHeight = "";

                // 등번호
                String num = n.select("em").text();
                int index = num.indexOf(".");
                playerNumber = num.substring(index+1, index+3);
                numList.add(playerNumber);

                // 이름
                playerName = n.selectFirst("span").text();
                nameList.add(playerName);

                Element table = n.select("table.profile").first();
                Elements tr = table.select("tr");

                // 포지션
                playerPosition = tr.select("th:contains(포지션) + td").text();
                positionList.add(playerPosition);

                // 생년월일
                playerBirth = tr.select("th:contains(생년월일) + td").text();
                birthList.add(playerBirth);

                // 키 몸무게
                playerHeight = tr.select("th:contains(키 / 몸무게) + td").text();
                heightList.add(playerHeight);

                // 이미지
                String imgLink = "https://www.heroesbaseball.co.kr/players/pitcher/list.do";
                Document imgDoc = Jsoup.connect(imgLink).get();
                Elements playerImageElements = imgDoc.select(".playerList li img");

                for (Element playerImage : playerImageElements) {
                    playerImg = playerImage.attr("src");
                    imgList.add("https://www.heroesbaseball.co.kr" + playerImg);
                }
            }

            for (int i = 0; i < catcher2.length; i++) {
                Document doc = Jsoup.connect(catcher1 + catcher2[i]).get();
                Element div = doc.select(".playerInfo").first();
                Element n = div.select(".summary").first();

                String playerImg = "";
                String playerName = "";
                String playerNumber = "";
                String playerBirth = "";
                String playerPosition = "";
                String playerHeight = "";

                // 등번호
                String num = n.select("em").text();
                int index = num.indexOf(".");
                playerNumber = num.substring(index+1, index+3);
                numList.add(playerNumber);

                // 이름
                playerName = n.selectFirst("span").text();
                nameList.add(playerName);

                Element table = n.select("table.profile").first();
                Elements tr = table.select("tr");

                // 포지션
                playerPosition = tr.select("th:contains(포지션) + td").text();
                positionList.add(playerPosition);

                // 생년월일
                playerBirth = tr.select("th:contains(생년월일) + td").text();
                birthList.add(playerBirth);

                // 키 몸무게
                playerHeight = tr.select("th:contains(키 / 몸무게) + td").text();
                heightList.add(playerHeight);

                // 이미지
                String imgLink = "https://www.heroesbaseball.co.kr/players/catcher/list.do";
                Document imgDoc = Jsoup.connect(imgLink).get();
                Elements playerImageElements = imgDoc.select(".playerList li img");

                for (Element playerImage : playerImageElements) {
                    playerImg = playerImage.attr("src");
                    imgList.add("https://www.heroesbaseball.co.kr" + playerImg);
                }
            }

            for (int i = 0; i < infielder2.length; i++) {
                Document doc = Jsoup.connect(infielder1 + infielder2[i]).get();
                Element div = doc.select(".playerInfo").first();
                Element n = div.select(".summary").first();

                String playerImg = "";
                String playerName = "";
                String playerNumber = "";
                String playerBirth = "";
                String playerPosition = "";
                String playerHeight = "";

                // 등번호
                String num = n.select("em").text();
                int index = num.indexOf(".");
                playerNumber = num.substring(index+1, index+3);
                numList.add(playerNumber);

                // 이름
                playerName = n.selectFirst("span").text();
                nameList.add(playerName);

                Element table = n.select("table.profile").first();
                Elements tr = table.select("tr");

                // 포지션
                playerPosition = tr.select("th:contains(포지션) + td").text();
                positionList.add(playerPosition);

                // 생년월일
                playerBirth = tr.select("th:contains(생년월일) + td").text();
                birthList.add(playerBirth);

                // 키 몸무게
                playerHeight = tr.select("th:contains(키 / 몸무게) + td").text();
                heightList.add(playerHeight);

                // 이미지
                String imgLink = "https://www.heroesbaseball.co.kr/players/infielder/list.do";
                Document imgDoc = Jsoup.connect(imgLink).get();
                Elements playerImageElements = imgDoc.select(".playerList li img");

                for (Element playerImage : playerImageElements) {
                    playerImg = playerImage.attr("src");
                    imgList.add("https://www.heroesbaseball.co.kr" + playerImg);
                }
            }

            for (int i = 0; i < outfielder2.length; i++) {
                Document doc = Jsoup.connect(outfielder1 + outfielder2[i]).get();
                Element div = doc.select(".playerInfo").first();
                Element n = div.select(".summary").first();

                String playerImg = "";
                String playerName = "";
                String playerNumber = "";
                String playerBirth = "";
                String playerPosition = "";
                String playerHeight = "";

                // 등번호
                String num = n.select("em").text();
                int index = num.indexOf(".");
                playerNumber = num.substring(index+1);
                numList.add(playerNumber);

                // 이름
                playerName = n.selectFirst("span").text();
                nameList.add(playerName);

                Element table = n.select("table.profile").first();
                Elements tr = table.select("tr");

                // 포지션
                playerPosition = tr.select("th:contains(포지션) + td").text();
                positionList.add(playerPosition);

                // 생년월일
                playerBirth = tr.select("th:contains(생년월일) + td").text();
                birthList.add(playerBirth);

                // 키 몸무게
                playerHeight = tr.select("th:contains(키 / 몸무게) + td").text();
                heightList.add(playerHeight);

                // 이미지
                String imgLink = "https://www.heroesbaseball.co.kr/players/pitcher/list.do";
                Document imgDoc = Jsoup.connect(imgLink).get();
                Elements playerImageElements = imgDoc.select(".playerList li img");

                for (Element playerImage : playerImageElements) {
                    playerImg = playerImage.attr("src");
                    imgList.add("https://www.heroesbaseball.co.kr" + playerImg);
                }
            }

            int length = pitcher2.length + catcher2.length + infielder2.length + outfielder2.length;
            for (int i = 0; i < length; i++) {
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
