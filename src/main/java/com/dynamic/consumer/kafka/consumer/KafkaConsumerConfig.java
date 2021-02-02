package com.dynamic.consumer.kafka.consumer;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dynamic.consumer.constant.KafkaConsumerConstants;

@Component
public class KafkaConsumerConfig {

	Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);
	
	public KafkaConsumer<String, String> createConsumer() {
		try {
			Properties properties = new Properties();
			properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConsumerConstants.bootstrapServer);
			properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
			properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
			properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, KafkaConsumerConstants.earliestOffset);
			properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, KafkaConsumerConstants.elasticSearchGroup);
			properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, KafkaConsumerConstants.autoCommitConfig);
			properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, KafkaConsumerConstants.maxPollSize);
			
			// create consumer
			KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

			// subscribe consume
			consumer.subscribe(Collections.singleton(KafkaConsumerConstants.topic));
			logger.info("Consumer created");

			return consumer;
		} catch (Exception e) {
			logger.error("Error creating kafka consumer.", e.getMessage());
			return null;
		}
	}
}
