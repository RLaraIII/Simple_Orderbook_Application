package com.sg.orderbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrderbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderbookApplication.class, args);
	}

}
