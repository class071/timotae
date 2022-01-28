package com.daily.timotae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TimotaeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimotaeApplication.class, args);
	}

}
