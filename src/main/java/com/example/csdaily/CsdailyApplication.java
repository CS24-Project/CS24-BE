package com.example.csdaily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CsdailyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsdailyApplication.class, args);
	}

}
