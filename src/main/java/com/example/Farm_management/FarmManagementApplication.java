package com.example.Farm_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:3000")
@ComponentScan(basePackages = "com.example.Farm_management")
@EnableAutoConfiguration
public class FarmManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmManagementApplication.class, args);
	}

}
