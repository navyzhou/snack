package com.yc.snack.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@MapperScan("com.yc.snack.product.mapper") // 指定mapper文件对应的接口包路径
@SpringCloudApplication
public class ProductServerStartApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductServerStartApplication.class, args);
	}
}
