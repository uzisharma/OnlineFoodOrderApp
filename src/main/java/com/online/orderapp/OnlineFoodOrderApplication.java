package com.online.orderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class OnlineFoodOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineFoodOrderApplication.class, args);
	}

}
