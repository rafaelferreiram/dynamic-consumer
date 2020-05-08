package com.dynamic.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dynamic.consumer.dto.response.ResponseDTO;
import com.dynamic.consumer.kafka.consumer.async.KafkaConsumerServiceAsync;

@RestController
@RequestMapping("/consumer")
@CrossOrigin(origins = "*")
public class KafkaConsumerController {
	
	@Autowired
	private KafkaConsumerServiceAsync serviceAsync;

	@GetMapping(value = "/activate")
	public ResponseEntity<Object> activateConsumer(){
		try {
			serviceAsync.activateConsumer();
			return ResponseEntity.ok().body(new ResponseDTO("OK","Consumer activated successfully"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseDTO("ERROR","Error actvating consumer"));
		}
	}
}
