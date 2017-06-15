package com.rabbitmq.example1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan
@Import(RabbitConf.class)
public class RabbitBootApp {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(RabbitBootApp.class, args);
	}
}