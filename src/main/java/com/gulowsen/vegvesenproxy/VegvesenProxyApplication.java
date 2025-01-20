package com.gulowsen.vegvesenproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class VegvesenProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(VegvesenProxyApplication.class, args);
	}

}
