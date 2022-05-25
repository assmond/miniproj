package com.rc.tindin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TindinApplication {

	public static void main(String[] args) {
		SpringApplication.run(TindinApplication.class, args);
	}

}
