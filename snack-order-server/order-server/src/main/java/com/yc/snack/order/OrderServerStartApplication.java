package com.yc.snack.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@MapperScan("com.yc.snack.order.mapper") // 指定mapper文件对应的接口包路径
//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker // 开启熔断和降级
@EnableFeignClients(basePackages = {"com.yc.snack.product.client", "com.yc.snack.user.client"})
@ComponentScan(basePackages = "com.yc.snack")
@SpringCloudApplication // 它包含以上三个注解
@EnableHystrixDashboard // 启用仪表盘
@EnableRedisHttpSession
public class OrderServerStartApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderServerStartApplication.class, args);
	}
}
