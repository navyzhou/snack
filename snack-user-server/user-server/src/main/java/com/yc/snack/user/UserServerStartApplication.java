package com.yc.snack.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@MapperScan("com.yc.snack.user.mapper") // 指定mapper文件对应的接口包路径
@SpringCloudApplication
@EnableDiscoveryClient
@EnableRedisHttpSession
public class UserServerStartApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserServerStartApplication.class, args);
	}
}
