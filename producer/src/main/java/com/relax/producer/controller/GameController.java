package com.relax.producer.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.relax.domain.model.Game;
import com.relax.producer.rest.GameRequest;

@RestController
public class GameController {

	private final KafkaTemplate<String, Game> gameKafkaTemplate;
	private static final String TOPIC_NAME = "game-topic";

	public GameController(KafkaTemplate<String, Game> gameKafkaTemplate) {

		this.gameKafkaTemplate = gameKafkaTemplate;
	}

	@PostMapping("/play")
	public ResponseEntity<String> publish(@RequestBody @Valid GameRequest gameRequest) {

		final String requestTraceId = UUID.randomUUID().toString();
		gameRequest.getGames().forEach(game -> {
			gameKafkaTemplate.send(TOPIC_NAME, requestTraceId, game);
		});
		return new ResponseEntity<>(requestTraceId, HttpStatus.ACCEPTED);
	}
}
