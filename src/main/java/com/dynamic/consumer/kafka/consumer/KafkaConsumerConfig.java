package com.dynamic.consumer.kafka.consumer;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerConfig {

	private String bootstrapServer;

	private String elasticSearchGroup;

	private String earliestOffset;

	private String topic;

	public KafkaConsumer<String, String> createConsumer() {
		// create Properties
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, elasticSearchGroup);
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, earliestOffset);
		properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "200");
		// create consumer
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

		// subscribe consume
		consumer.subscribe(Collections.singleton(topic));

		return consumer;
	}
}
