package com.yc.snack.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@MapperScan("com.yc.snack.product.mapper") // 指定mapper文件对应的接口包路径
@SpringCloudApplication
@EnableRedisHttpSession
@EnableHystrixDashboard // 仪表盘的
@EnableFeignClients("com.yc.snack.user.client")
@ComponentScan(basePackages = "com.yc.snack")
public class ProductServerStartApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductServerStartApplication.class, args);
	}
}
