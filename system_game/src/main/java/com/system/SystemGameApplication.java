package com.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableHystrixDashboard
@EnableSwagger2
public class SystemGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemGameApplication.class, args);
	}
}
