package com.wibmo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dell
 *
 */
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	private static final String TOPIC= "TestTopic";
	
	@GetMapping("/publish/{message}")
	public String sendDataMessage(@PathVariable("message") final String message) {
		
		kafkaTemplate.send(TOPIC,message);
		
		return "Message SuccessFully Published";
	}

}
