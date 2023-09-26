package com.musicalist.intermediator.intermediator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.musicalist.intermediator.intermediator.Modelo")
@ComponentScan(basePackages = "com.musicalist.intermediator.intermediator.Controlador")
public class IntermediatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntermediatorApplication.class, args);
	}

}
