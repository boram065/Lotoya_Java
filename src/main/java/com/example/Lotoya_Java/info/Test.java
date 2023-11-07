package com.example.Lotoya_Java.info;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        String url = "https://www.koreabaseball.com/Futures/Player/HitterDetail.aspx?playerId=53100";

        try {
            Document doc = Jsoup.connect(url).get();

            Element table = doc.select("div.player_basic ul").first();
            Elements rows = table.select("li");

            for (int i = 0; i < 5; i++) {
                Element row = rows.get(i);
                Element spanElement = row.select("span").first();
                String playerName = spanElement.text();

                System.out.println(playerName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
