package com.example.demo;

import java.util.Date;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SendMessageController {

	@Autowired
	private KafkaConfig kafkaConfig;
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	@ResponseBody
	public String sendMessage() {
		
		kafkaConfig.producerFactory().createProducer().send(new ProducerRecord<String, String>("denemeler", "deneme "+new Date().toString()));
		return "success";
		
	}
	
}
