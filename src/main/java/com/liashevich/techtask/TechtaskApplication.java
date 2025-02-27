package com.liashevich.techtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.liashevich.entity")
@ComponentScan({"com/liashevich/controller","com.liashevich.service", "com/liashevich/exception"})
@EnableJpaRepositories("com/liashevich/repository")
public class TechtaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechtaskApplication.class, args);
	}

}
