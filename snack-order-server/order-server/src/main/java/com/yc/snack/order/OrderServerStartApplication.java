package com.yc.snack.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@MapperScan("com.yc.snack.orders.mapper") // 指定mapper文件对应的接口包路径
//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker // 开启熔断和降级
@SpringCloudApplication // 它包含以上三个注解
public class OrderServerStartApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderServerStartApplication.class, args);
	}
}
