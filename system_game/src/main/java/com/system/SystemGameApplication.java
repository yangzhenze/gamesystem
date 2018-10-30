package com.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
@RestController
public class SystemGameApplication {
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(SystemGameApplication.class, args);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(){
		MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
		postParameters.add("account", "yzz");
		postParameters.add("password", "02CF6485D51F95F1109A0C937EC7BB80");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, headers);
		return restTemplate().postForObject("http://SYSTEMADMIN/admin/login",r,String.class);
	}
}
