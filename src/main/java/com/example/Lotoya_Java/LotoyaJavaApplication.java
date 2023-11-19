package com.example.Lotoya_Java;

import com.example.Lotoya_Java.controller.PlayerController;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.Lotoya_Java")
public class LotoyaJavaApplication {
	public static PlayerController playerController;
	public static void main(String[] args) {
		playerController.saveAllPlayers();
		SpringApplication.run(LotoyaJavaApplication.class, args);
	}
}