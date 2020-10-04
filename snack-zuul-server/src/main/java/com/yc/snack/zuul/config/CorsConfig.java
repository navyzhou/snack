package com.yc.snack.zuul.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 配置跨域访问 Cors  Cross Origin Resource Sharing
 * company 源辰信息
 * @author navy
 * @date 2020年10月4日
 * Email haijunzhou@hnit.edu.cn
 */
@Configuration
public class CorsConfig {
	@Bean
	public CorsFilter crosFilter() {
		UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); // 允许cookie跨域
		config.setAllowedOrigins(Arrays.asList("*"));  // 域
		config.setAllowedHeaders(Arrays.asList("*"));
		// config.setAllowedMethods(Arrays.asList("GET", "POST"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setMaxAge(300L); // 300秒
		configSource.registerCorsConfiguration("/**", config);
		return new CorsFilter(configSource);
	}
}
