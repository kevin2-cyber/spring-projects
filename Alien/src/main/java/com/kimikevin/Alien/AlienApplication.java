package com.kimikevin.Alien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AlienApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AlienApplication.class, args);

		Dev obj = context.getBean(Dev.class);
		obj.build();
	}

}
