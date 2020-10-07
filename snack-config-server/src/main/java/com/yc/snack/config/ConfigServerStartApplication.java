package com.yc.snack.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
@ServletComponentScan(basePackages = "com.yc.snack.config.filter")
public class ConfigServerStartApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerStartApplication.class, args);
	}
}
