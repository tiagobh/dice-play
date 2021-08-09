package com.relax.dicegame.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.relax.dicegame.model.Game;

public class GameRequest {

	@NotEmpty(message = "A least one game is needed")
	private List<@Valid Game> games;

	public List<Game> getGames() {

		return games;
	}

	public void setGames(List<Game> games) {

		this.games = games;
	}
}
