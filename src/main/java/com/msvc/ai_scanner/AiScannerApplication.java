package com.msvc.ai_scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AiScannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiScannerApplication.class, args);
	}

}

