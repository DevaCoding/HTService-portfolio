package com.springboot2.htservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HtserviceApplication {

	/*public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.properties,"
			+ "classpath:aws.yml";*/

	public static void main(String[] args) {
		SpringApplication.run(HtserviceApplication.class, args);
		/*new SpringApplicationBuilder(HtserviceApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);*/
	}

}
