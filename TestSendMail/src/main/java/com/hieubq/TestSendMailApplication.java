package com.hieubq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TestSendMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSendMailApplication.class, args);
	}

}
