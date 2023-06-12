package com.phrc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.phrc")
public class SpringCrudRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudRestApplication.class, args);
		System.out.println("Hello i m here to learn.....");
	}

}
