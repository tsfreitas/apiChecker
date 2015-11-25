package com.tsfreitas.apiChecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CheckerBoot {

	public static void main(String[] args) {
		SpringApplication.run(CheckerBoot.class, args);
	}
}
