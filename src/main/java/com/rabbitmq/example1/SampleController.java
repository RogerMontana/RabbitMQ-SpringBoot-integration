package com.rabbitmq.example1;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
	private static Logger logger = Logger.getLogger(SampleController.class);
	private static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

	@Autowired
	AmqpTemplate template;

	@RequestMapping("/emit")
	@ResponseBody
	String emitMessages() {
		IntStream.range(1, 100000).forEach((i) -> template.convertAndSend(QueneName.NAME.toString(), "Message to queue" + i));
		return "Request was started type /print to see results";

	}

	@RequestMapping("/print")
	@ResponseBody
	public String printMessages() {
		return queue.toString();
	}

	@RabbitListener(queues = "NAME")
	public void processQueue(String message) {
		logger.info("Received from queue 1: " + message);
		queue.add(message);
	}

}