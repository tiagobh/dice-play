package com.relax.dicegame.rest;

import java.util.List;

import com.relax.dicegame.model.GameResult;

public class GameResponse {

	List<GameResult> gameResults;

	public GameResponse(List<GameResult> gameResults) {

		this.gameResults = gameResults;
	}

	public List<GameResult> getGameResults() {

		return gameResults;
	}

}
