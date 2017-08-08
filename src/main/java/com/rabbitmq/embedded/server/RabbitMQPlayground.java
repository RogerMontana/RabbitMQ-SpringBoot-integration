package com.rabbitmq.embedded.server;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMq;
import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMqConfig;

/**
 * Created by Artem Karpov
 */

public class RabbitMQPlayground {
	//TODO make static in order to manual run of rabbit-mq server (need to have erlang on your machine)
	public void main(String[] args) throws IOException, TimeoutException {
		EmbeddedRabbitMqConfig config =
				new EmbeddedRabbitMqConfig.Builder().defaultRabbitMqCtlTimeoutInMillis(100000).erlangCheckTimeoutInMillis(10000)
						.rabbitMqServerInitializationTimeoutInMillis(100000).build();
		EmbeddedRabbitMq rabbitMq = new EmbeddedRabbitMq(config);
		rabbitMq.start();
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setVirtualHost("/");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");

		Connection connection = connectionFactory.newConnection();

		assertThat("Can't create connection", connection.isOpen(), equalTo(true));
		Channel channel = connection.createChannel();
		assertThat("Can't create channel", channel.isOpen(), equalTo(true));
		System.out.println("everything is fine");
		channel.close();
		connection.close();
	}
}

