package com.hackaton.ms_calls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MsCallsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCallsApplication.class, args);
	}

}
