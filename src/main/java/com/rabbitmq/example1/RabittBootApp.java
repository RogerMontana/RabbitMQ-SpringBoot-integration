package com.rabbitmq.example1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@ComponentScan
@Import(RabbitConf.class)
public class RabittBootApp {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(RabittBootApp.class, args);
	}
}