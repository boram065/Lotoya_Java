package com.example.Java_Lotoya.info;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LGInfo {
    public static void main(String[] args) {
        String url = "https://www.lgtwins.com/service/html.ncd"; // 경로 에러

        try {
            Document doc = Jsoup.connect(url).get();
            Element div = doc.select("div.txt_wrap").first();
            Elements rows = div.select("p");

            for (Element row : rows) {
                String PlayerTitle = rows.select("p.role").text();
                System.out.println(PlayerTitle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
