package io.github.mrocha01.spring_react_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringReactApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactApiApplication.class, args);
	}

}
