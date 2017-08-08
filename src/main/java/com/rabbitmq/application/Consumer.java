package com.rabbitmq.application;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *
 * Created by Artem Karpov
 */
@EnableRabbit
@Component
public class Consumer {
	private static Logger logger = Logger.getLogger(SampleController.class);
	private static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

	@RabbitListener(queues = "queue1")
	public void consumer1(String message) {
		logger.info("Consumer received from queue 1: " + message);
		queue.add(message);
	}

	public static ConcurrentLinkedQueue<String> getQueue() {
		return queue;
	}
}
