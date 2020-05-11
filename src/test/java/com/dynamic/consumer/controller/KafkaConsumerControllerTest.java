package com.dynamic.consumer.controller;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.dynamic.consumer.kafka.service.KafkaConsumerServiceAsync;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
public class KafkaConsumerControllerTest {

	@Mock
	private KafkaConsumerServiceAsync kafkaConsumerServiceAsync;

	private MockMvc mockMvc;

	private static final int BAD_REQUEST = 400;
	private static final int STATUS_OK = 200;
	Gson gson;
}
