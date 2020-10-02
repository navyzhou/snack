package com.yc.snack.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.yc.snack.product.mapper") // 指定mapper文件对应的接口包路径
@SpringBootApplication
@EnableDiscoveryClient
public class ProductServerStartApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductServerStartApplication.class, args);
	}
}
