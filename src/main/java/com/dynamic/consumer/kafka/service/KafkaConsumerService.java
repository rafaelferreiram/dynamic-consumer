package com.dynamic.consumer.kafka.service;

import java.io.IOException;

public interface KafkaConsumerService {

	public void activateConsumer() throws IOException ;

	public void setActive(boolean active);
}
