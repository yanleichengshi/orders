package com.celiaKey.orders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
public class CeliaKeyOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeliaKeyOrdersApplication.class, args);
		log.info("------------- orders 启动成功 ----------------");
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}


}
