package com.assignment.bitbuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.assignment.bitbuy.entity"})
public class BitbuyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitbuyApplication.class, args);
	}

}
