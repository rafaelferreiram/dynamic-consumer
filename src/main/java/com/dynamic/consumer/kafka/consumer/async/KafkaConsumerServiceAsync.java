package com.dynamic.consumer.kafka.consumer.async;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.dynamic.consumer.kafka.service.KafkaConsumerService;

@Component
public class KafkaConsumerServiceAsync {

	@Autowired
	private KafkaConsumerService service;

	@Async
	public void activateConsumer() {
		try {
			service.activateConsumer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
