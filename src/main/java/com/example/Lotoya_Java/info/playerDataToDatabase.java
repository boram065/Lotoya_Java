package com.example.Lotoya_Java.info;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

@Component
public class playerDataToDatabase {
    public static void main(String[] args) {
        ArrayList<Integer> idList = DoosanInfo.idList;
        ArrayList<String> clubList = DoosanInfo.clubList;
        ArrayList<String> imgLink = DoosanInfo.imgList;
        ArrayList<Integer> numList = DoosanInfo.numList;
        ArrayList<String> nameList = DoosanInfo.nameList;
        ArrayList<String> birthList = DoosanInfo.birthList;
        ArrayList<String> positionList = DoosanInfo.positionList;
        ArrayList<Integer> heightList = DoosanInfo.heightList;
        ArrayList<Integer> weightList = DoosanInfo.weightList;
        ArrayList<Integer> priceList = DoosanInfo.priceList;

        // JDBC 연결 설정
        String url = "jdbc:mysql://localhost:3306/lotoya";
        String user = "root";
        String password = "0605";

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            conn.setAutoCommit(false);
            if (conn != null) {
                System.out.println("데이터베이스에 연결되었습니다.");

                // 'player' 테이블에 데이터를 삽입하는 쿼리
                String insertQuery = "INSERT INTO player (id, club, position, back_num, name, birth, height, weight, price, imgLink) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                // PreparedStatement를 사용하여 쿼리를 실행
                PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

                // ArrayList 데이터를 순회하면서 데이터베이스에 삽입
                for (int i = 0; i < nameList.size(); i++) {
                    preparedStatement.setInt(1, idList.get(i));
                    preparedStatement.setString(2, clubList.get(i));
                    preparedStatement.setString(3, positionList.get(i));
                    preparedStatement.setInt(4, numList.get(i));
                    preparedStatement.setString(5, nameList.get(i));
                    preparedStatement.setString(6, birthList.get(i));
                    preparedStatement.setInt(7, heightList.get(i));
                    preparedStatement.setInt(8, weightList.get(i));
                    preparedStatement.setInt(9, priceList.get(i));
                    preparedStatement.setString(10, imgLink.get(i));
                    preparedStatement.executeUpdate();
                    System.out.println(positionList.get(i));
                }
                System.out.println("데이터 삽입이 완료되었습니다.");
                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // main
}
