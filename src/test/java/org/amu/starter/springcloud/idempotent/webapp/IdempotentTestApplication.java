package org.amu.starter.springcloud.idempotent.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages={"org.amu.starter.springcloud.idempotent"})
public class IdempotentTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(IdempotentTestApplication.class, args);
	}
}
