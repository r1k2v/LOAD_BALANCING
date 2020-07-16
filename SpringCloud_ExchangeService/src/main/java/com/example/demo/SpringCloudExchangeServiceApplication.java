package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="com.*")
@EntityScan(basePackages="com.*")
@EnableJpaRepositories(basePackages="com.*")
@EnableDiscoveryClient
public class SpringCloudExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudExchangeServiceApplication.class, args);
	}

}
