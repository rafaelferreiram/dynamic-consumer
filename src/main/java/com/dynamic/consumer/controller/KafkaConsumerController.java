package com.dynamic.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dynamic.consumer.kafka.service.KafkaConsumerService;

@RestController
@RequestMapping("/consumer")
@CrossOrigin(origins = "*")
public class KafkaConsumerController {
	
	@Autowired
	private KafkaConsumerService service;

	@GetMapping(value = "/activate")
	public ResponseEntity<Object> activateConsumer(){
		try {
			service.activateConsumer();
			return ResponseEntity.ok().body("Consumer activated successfully");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error actvating consumer");
		}
	}
}
