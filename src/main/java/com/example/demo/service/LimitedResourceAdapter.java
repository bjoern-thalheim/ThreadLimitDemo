package com.example.demo.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LimitedResourceAdapter {
    private static final Logger log = LoggerFactory.getLogger(LimitedResourceAdapter.class);
    
    ExecutorService executor = Executors.newFixedThreadPool(3);

	public Future<String> getWithLimitedThreads(String arg) {
		Callable<String> callable = new Callable<String>() {

			@Override
			public String call() throws Exception {
				return getDirectly(arg);
			}
		};
		return executor.submit(callable);
	}
	
	public String getDirectly(String arg) {				
		Thread currentThread = Thread.currentThread();
		String result = "%s - %s".formatted("Gotten from limited resource", arg);
		log.info("[Thread Pool control needed] Received call with argument %s in Thread %s - returning \"%s\"".formatted(arg, currentThread, result));
		return result;
	}
}
