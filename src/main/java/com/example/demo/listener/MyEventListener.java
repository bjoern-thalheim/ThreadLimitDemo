package com.example.demo.listener;

import com.example.demo.events.DemoEvent;
import com.example.demo.service.LimitedResourceAdapter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener {
    private static final Logger log = LoggerFactory.getLogger(MyEventListener.class);
    
    @Autowired
    private LimitedResourceAdapter adapter;

	@EventListener
	public void on(DemoEvent event) throws InterruptedException, ExecutionException {
		Thread currentThread = Thread.currentThread();
		log.info("Received event %s in Thread %s".formatted(event, currentThread));	
		String result = fetchData(event);
		log.info("Returning result %s in Thread %s".formatted(result, currentThread));
	}

	private String fetchData(DemoEvent event) throws InterruptedException, ExecutionException {
//		return adapter.getDirectly(event.getId());
		Future<String> fromLimitedResource = adapter.getWithLimitedThreads(event.getId());
		return fromLimitedResource.get();
	}
}
