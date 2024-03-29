package com.example.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Configuration
@EnableKafka
public class KafkaListenerConfig {

	@Autowired
	private MessageController messageController;
	
	@KafkaListener(topics="denemeler", groupId = "readers_denemeler")
	public void listen(String message) {
		
		System.out.println("Received Messasge: " + message);
		
		SseEmitter latestEm = messageController.getLatestEmitter();
				
		try {
			latestEm.send(message);
		} catch (IOException e) {
			latestEm.completeWithError(e);
		}
		
	}
	
}
