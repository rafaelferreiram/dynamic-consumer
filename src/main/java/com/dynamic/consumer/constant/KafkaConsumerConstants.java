package com.dynamic.consumer.constant;

import org.springframework.beans.factory.annotation.Value;

public class KafkaConsumerConstants {


	@Value("${kafka.bootstrapServer}")
	public static String bootstrapServer;

	@Value("${kafka.offset}")
	public static String earliestOffset;

	@Value("${kafka.topic}")
	public static String topic;
	
	@Value("${kafka.groupid}")
	public static String elasticSearchGroup;
	
	@Value("${kafka.max.poll.size}")
	public static String maxPollSize;
	
	@Value("{kafka.auto.commit.config}")
	public static String autoCommitConfig;
}
