package com.example.Lotoya_Java.controller;

import com.example.Lotoya_Java.dto.PlayerFilterRequest;
import com.example.Lotoya_Java.dto.PlayerViewResponse;
import com.example.Lotoya_Java.entity.Player;
import com.example.Lotoya_Java.info.*;
import com.example.Lotoya_Java.repository.PlayerRepository;
import com.example.Lotoya_Java.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PlayerController {
    @Autowired
    private final PlayerService playerService;

    @PostMapping("/store")
    public ResponseEntity<List<PlayerViewResponse>> getFilteredPlayers(@RequestBody PlayerFilterRequest filterRequest) {
        List<PlayerViewResponse> filteredPlayers = playerService.getFilteredPlayers(filterRequest);
        return ResponseEntity.ok().body(filteredPlayers);
    }

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/lotoya";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String RESET_ID_SQL = "ALTER TABLE player AUTO_INCREMENT = 1";

    @Transactional
    public static void saveAllPlayers() {
        try {
            List<Player> kiwoomPlayers = KiwoomInfo.convertToPlayers();
            List<Player> doosanPlayers = DoosanInfo.convertToPlayers();
            List<Player> SSGPlayers = SSGInfo.convertToPlayers();
            List<Player> SamsungPlayers = SamsungInfo.convertToPlayers();
            List<Player> NCPlayers = NCInfo.convertToPlayers();

            savePlayers(kiwoomPlayers);
            savePlayers(doosanPlayers);
            savePlayers(SSGPlayers);
            savePlayers(SamsungPlayers);
            savePlayers(NCPlayers);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    private static void savePlayers(List<Player> players) {
        String selectSql = "SELECT * FROM player WHERE back_num = ? AND name = ?";
        String updateSql = "UPDATE player SET height = ?, price = ?, weight = ?, birth = ?, club = ?, img_link = ?, position = ? WHERE back_num = ? AND name = ?";
        String insertSql = "INSERT INTO player (back_num, height, price, weight, birth, club, img_link, name, position) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement selectStatement = connection.prepareStatement(selectSql);
             PreparedStatement updateStatement = connection.prepareStatement(updateSql);
             PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {

            for (Player newPlayer : players) {
                selectStatement.setInt(1, newPlayer.getBackNum());
                selectStatement.setString(2, newPlayer.getName());

                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // 선수가 이미 존재하는 경우, 정보 업데이트
                        updateStatement.setInt(1, newPlayer.getHeight());
                        updateStatement.setInt(2, newPlayer.getPrice());
                        updateStatement.setInt(3, newPlayer.getWeight());
                        updateStatement.setString(4, newPlayer.getBirth());
                        updateStatement.setString(5, newPlayer.getClub());
                        updateStatement.setString(6, newPlayer.getImgLink());
                        updateStatement.setString(7, newPlayer.getPosition());
                        updateStatement.setInt(8, newPlayer.getBackNum());
                        updateStatement.setString(9, newPlayer.getName());

                        updateStatement.executeUpdate();
                    } else {
                        // 선수가 존재하지 않는 경우, 신규 추가
                        insertStatement.setInt(1, newPlayer.getBackNum());
                        insertStatement.setInt(2, newPlayer.getHeight());
                        insertStatement.setInt(3, newPlayer.getPrice());
                        insertStatement.setInt(4, newPlayer.getWeight());
                        insertStatement.setString(5, newPlayer.getBirth());
                        insertStatement.setString(6, newPlayer.getClub());
                        insertStatement.setString(7, newPlayer.getImgLink());
                        insertStatement.setString(8, newPlayer.getName());
                        insertStatement.setString(9, newPlayer.getPosition());

                        insertStatement.executeUpdate(RESET_ID_SQL);
                        insertStatement.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
