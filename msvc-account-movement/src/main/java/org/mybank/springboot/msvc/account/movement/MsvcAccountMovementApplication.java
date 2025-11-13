package org.mybank.springboot.msvc.account.movement;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MsvcAccountMovementApplication {

    private static final Logger log = LoggerFactory.getLogger(MsvcAccountMovementApplication.class);

    public static void main(String[] args) {
		SpringApplication.run(MsvcAccountMovementApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

    @PostConstruct
    public void init() {
        System.out.println("🟢 System.out desde clase principal");
        log.info("🟢 Log SLF4J desde clase principal");
    }

}
