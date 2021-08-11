package com.relax.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class DiceGamesProducer {

	public static void main(String[] args) {

		SpringApplication.run(DiceGamesProducer.class, args);
	}

}
