package com.yc.snacknet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@ServletComponentScan // Servlet Filter  Listener
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 取消DataSource的自动加载配置
public class SnacknetStartApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(SnacknetStartApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SnacknetStartApplication.class);
	}
}
