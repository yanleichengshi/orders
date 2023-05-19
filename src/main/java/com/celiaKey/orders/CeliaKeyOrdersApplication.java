package com.celiaKey.orders;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class CeliaKeyOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeliaKeyOrdersApplication.class, args);
		log.info("------------- orders 启动成功 ----------------");
	}

}
