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
	
	public static String maxPollSize = "200";

	public static String autoCommitConfig = "false";
}
