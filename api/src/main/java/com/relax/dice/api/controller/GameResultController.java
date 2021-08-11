package com.relax.dice.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.relax.dice.api.service.GameResultService;
import com.relax.domain.model.GameResult;

@RestController
public class GameResultController {

	private final GameResultService gameResultService;

	public GameResultController(GameResultService gameResultService) {

		this.gameResultService = gameResultService;
	}

	@GetMapping("/result/{requestTraceId}")
	public ResponseEntity<List<GameResult>> get(@PathVariable() String requestTraceId) {

		final List<GameResult> gameResults = gameResultService.get(requestTraceId);
		if (CollectionUtils.isEmpty(gameResults)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(gameResults, HttpStatus.OK);
	}
}
