package org.mybank.springboot.msvc.account.movement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MsvcAccountMovementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcAccountMovementApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
