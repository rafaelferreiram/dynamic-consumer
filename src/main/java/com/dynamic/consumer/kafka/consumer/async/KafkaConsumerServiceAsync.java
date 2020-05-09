package com.dynamic.consumer.kafka.consumer.async;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.dynamic.consumer.kafka.service.KafkaConsumerService;

@Component
public class KafkaConsumerServiceAsync {

	Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceAsync.class);

	@Autowired
	private KafkaConsumerService service;

	@Async
	public void activateConsumer() {
		try {
			service.setActive(true);
			service.activateConsumer();
		} catch (IOException e) {
			logger.info("Error while activating kafka consumer", e.getMessage());
		}
	}

	public void deactivateConsumer() {
		try {
			service.setActive(false);
		} catch (Exception e) {
			logger.info("Error while deactivating kafka consumer", e.getMessage());
		}
	}

}
