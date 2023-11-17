package com.example.Lotoya_Java.controller;

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

import java.sql.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PlayerController {
    @Autowired
    private final PlayerService playerService;

    public String showStoreForm(Model model) {
        model.addAttribute("player", new Player());
        return "store";
    }

    @GetMapping("/")
    public ResponseEntity<List<PlayerViewResponse>> getAllPlayers() {
        List<PlayerViewResponse> players = playerService.getAllPlayers()
                .stream()
                .map(PlayerViewResponse::new)
                .toList();
        return ResponseEntity.ok().body(players);
    }

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/lotoya";
    private static final String USER = "root";
    private static final String PASSWORD = "0605";

    @Transactional
    public static void saveAllPlayers() {
        try {
            List<Player> kiwoomPlayers = KiwoomInfo.convertToPlayers();
            List<Player> doosanPlayers = DoosanInfo.convertToPlayers();
            List<Player> SSGPlayers = SSGInfo.convertToPlayers();
            List<Player> SamsungPlayers = SamsungInfo.convertToPlayers();
            List<Player> NCPlayers = NCInfo.convertToPlayers();

            System.out.println("Kiwoom Players: " + kiwoomPlayers.size());
            System.out.println("Doosan Players: " + doosanPlayers.size());
            System.out.println("SSG Players: " + SSGPlayers.size());
            System.out.println("Samsung Players: " + SamsungPlayers.size());
            System.out.println("NC Players: " + NCPlayers.size());

            savePlayers(kiwoomPlayers);
            savePlayers(doosanPlayers);
            savePlayers(SSGPlayers);
            savePlayers(SamsungPlayers);
            savePlayers(NCPlayers);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        saveAllPlayers();
    }

    @Transactional
    private static void savePlayers(List<Player> players) {
        String sql = "INSERT INTO player (back_num, height, price, weight, birth, club, img_link, name, position) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (Player player : players) {
                if (!isPlayerExists(connection, player)) {
                    preparedStatement.setInt(1, player.getBackNum());
                    preparedStatement.setInt(2, player.getHeight());
                    preparedStatement.setInt(3, player.getPrice());
                    preparedStatement.setInt(4, player.getWeight());
                    preparedStatement.setString(5, player.getBirth());
                    preparedStatement.setString(6, player.getClub());
                    preparedStatement.setString(7, player.getImgLink());
                    preparedStatement.setString(8, player.getName());
                    preparedStatement.setString(9, player.getPosition());

                    preparedStatement.executeUpdate();

                } else {
                    System.out.println(player.getClub() + " " + player.getName());
                }
            }
            System.out.println("Players saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isPlayerExists(Connection connection, Player player) throws SQLException {
        String checkDuplicateSql = "SELECT COUNT(*) FROM player WHERE back_num = ? AND name = ?";
        try (PreparedStatement checkDuplicateStatement = connection.prepareStatement(checkDuplicateSql)) {
            checkDuplicateStatement.setInt(1, player.getBackNum());
            checkDuplicateStatement.setString(2, player.getName());

            try (ResultSet resultSet = checkDuplicateStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }
}
