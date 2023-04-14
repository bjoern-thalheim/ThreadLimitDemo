package com.example.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import com.example.demo.events.DemoEvent;

@SpringBootTest
class DemoApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);
    
	@Autowired
    private ApplicationEventPublisher eventPublisher;

	@Test
	void contextLoads() {
		Thread currentThread = Thread.currentThread();
		for (int i = 0; i < 13; i++) {
			DemoEvent event = new DemoEvent();
			log.info("Sending event %s in Thread %s".formatted(event, currentThread));
			eventPublisher.publishEvent(event);
		}			
	}
}
