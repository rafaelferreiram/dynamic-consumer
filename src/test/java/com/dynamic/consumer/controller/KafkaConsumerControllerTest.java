package com.dynamic.consumer.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dynamic.consumer.dto.response.ResponseDTO;
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

	@Before
	public void onInit() {
		MockitoAnnotations.initMocks(this);
		KafkaConsumerController controller = new KafkaConsumerController(kafkaConsumerServiceAsync);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		gson = new Gson();
	}

	@Test
	public void shouldActivateSuccessfully() throws Exception {
		ResponseDTO expectedResponse = populateSuccessResponse();
		String expectedJsonResponse = gson.toJson(expectedResponse);

		MvcResult response = this.mockMvc.perform(get("/consumer/activate").contentType(APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();

		assertEquals(STATUS_OK, response.getResponse().getStatus());
		assertEquals(expectedJsonResponse, response.getResponse().getContentAsString());
	}

	private ResponseDTO populateSuccessResponse() {
		return new ResponseDTO("OK", "Consumer activated successfully");
	}

}
