package com.rabbitmq.example1;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

@EnableRabbit
public class RabbitConf {
	private static  Logger logger = Logger.getLogger(RabbitConf.class);

	@Bean
	public ConnectionFactory connectionFactory() {
		return new CachingConnectionFactory("localhost");
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public AmqpTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
		rabbitTemplate.setExchange("exchange-example");
		return rabbitTemplate;
	}

	@Bean
	public Queue myQueue1() {
		return new Queue("queue1");
	}

	@Bean
	public Queue myQueue2() {
		return new Queue("queue2");
	}

	@Bean
	public FanoutExchange fanoutExchange(){
		return new FanoutExchange("exchange-example");
	}

	@Bean
	public Binding binding1(){
		return BindingBuilder.bind(myQueue1()).to(fanoutExchange());
	}

	@Bean
	public Binding binding2(){
		return BindingBuilder.bind(myQueue2()).to(fanoutExchange());
	}

}
