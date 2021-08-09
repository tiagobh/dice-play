package com.relax.dicegame.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.relax.dicegame.model.GameResult;
import com.relax.dicegame.rest.GameRequest;
import com.relax.dicegame.rest.GameResponse;
import com.relax.dicegame.service.GameService;

@RestController
public class GameController {

	private final GameService gameService;

	public GameController(GameService gameService) {

		this.gameService = gameService;
	}

	@PutMapping("/play")
	ResponseEntity<GameResponse> playGame(@RequestBody @Valid GameRequest gameRequest) {

		List<GameResult> gameResults = Collections.synchronizedList(new ArrayList<>());

		gameRequest.getGames().parallelStream().forEach(game -> {
			try {
				gameResults.add(gameService.execute(game));
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		return new ResponseEntity<>(new GameResponse(gameResults), HttpStatus.OK);
	}
}
