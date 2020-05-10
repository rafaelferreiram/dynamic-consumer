package com.dynamic.consumer.kafka.service;

import org.springframework.scheduling.annotation.Async;

public interface KafkaConsumerServiceAsync {

	@Async
	public void activateConsumer();

	@Async
	public void deactivateConsumer();
	
}
