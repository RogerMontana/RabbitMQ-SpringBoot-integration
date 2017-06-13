package com.rabbitmq.example1;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
	private static Logger logger = Logger.getLogger(SampleController.class);

	@Autowired
	private AmqpTemplate template;

	@RequestMapping("/emitExchange")
	@ResponseBody
	public String emitMessagesExchange() {
		IntStream.range(1, 100).forEach((i) -> template.convertAndSend("exchange-example", null, "Message to exchange" + i));
		return "Request was started type http://localhost:8080/print to see results";

	}

	@RequestMapping("/emitDirectly")
	@ResponseBody
	public String emitDirectly() {
		IntStream.range(1, 100).forEach((i) -> template.convertAndSend("queue1", "Message to queue1" + i));
		return "Request was started type http://localhost:8080/print to see results";

	}

	@RequestMapping("/print")
	@ResponseBody
	public String printMessages() {
		return Consumer.getQueue().toString();
	}

}