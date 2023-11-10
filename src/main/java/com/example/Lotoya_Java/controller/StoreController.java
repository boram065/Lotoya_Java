package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.BoramPlayer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class StoreController {
    private final BoramPlayer boramPlayer;

    @RequestMapping("/store")
        public String store(Model model) {
        // 이미 주입받은 com.example.Lotoya_Java.Player 객체를 활용
        ArrayList<String> imgList = boramPlayer.getPlayerImg();
        ArrayList<String> numList = boramPlayer.getPlayerNumber();
        ArrayList<String> nameList = boramPlayer.getPlayerName();

        // 모델에 데이터 추가
        model.addAttribute("imgList", imgList);
        model.addAttribute("numList", numList);
        model.addAttribute("nameList", nameList);
        model.addAttribute("imagePath1", "/static/images/logo.jpg");
        model.addAttribute("imagePath2", "/static/images/vs.jpg");
        model.addAttribute("imagePath3", "/static/images/club.jpg");
        model.addAttribute("imagePath4", "/static/images/FA.jpg");
        model.addAttribute("imagePath5", "/static/images/mypage.jpg");
        model.addAttribute("imagePath6", "/static/images/playerteam.jpg");
        model.addAttribute("imagePath7", "/static/images/search.jpg");
        model.addAttribute("imagePath8", "/static/images/coin.jpg");
        model.addAttribute("imagePath9", "/static/images/basket.jpg");
        model.addAttribute("imagePath10", "/static/images/wishlist.jpg");

        return "store";
    }

    @RequestMapping("/store-data")
    @ResponseBody
    public Map<String, Object> storeData() {
        Map<String, Object> data = new HashMap<>();
        // 이미 주입받은 com.example.Lotoya_Java.Player 객체를 활용
        ArrayList<String> imgList = boramPlayer.getPlayerImg();
        ArrayList<String> numList = boramPlayer.getPlayerNumber();
        ArrayList<String> nameList = boramPlayer.getPlayerName();

        data.put("imgList", imgList);
        data.put("nameList", nameList);
        data.put("numList", numList);

        return data;
    }
}