package com.relax.dicegame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameResult {

	private Game game;
	private long startTime;
	private long endTime;
	private int wins;
	private int loses;
	@JsonProperty("statistics")
	private StatisticsResult statisticsResult;

	@JsonIgnore
	private List<BetResult> betResults = new ArrayList<>();

	public GameResult(Game game) {

		this.game = game;
	}

	public int getWins() {

		return wins;
	}

	public void setWins(int wins) {

		this.wins = wins;
	}

	public int getLoses() {

		return loses;
	}

	public void setLoses(int loses) {

		this.loses = loses;
	}

	public long getDuration() {

		return TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);
	}

	public Game getGame() {

		return game;
	}

	public List<BetResult> getBetResults() {

		return betResults;
	}

	public void setBetResults(List<BetResult> betResults) {

		this.betResults = betResults;
	}

	public void setStatisticsResult(StatisticsResult statisticsResult) {

		this.statisticsResult = statisticsResult;
	}

	public long getStartTime() {

		return startTime;
	}

	public void setStartTime(long startTime) {

		this.startTime = startTime;
	}

	public long getEndTime() {

		return endTime;
	}

	public void setEndTime(long endTime) {

		this.endTime = endTime;
	}

	@Override
	public String toString() {

		return "Result for game: " +  game.getName() +
				",\n duration(seconds)= " + getDuration() +
				",\n wins= " + wins +
				",\n loses= " + loses +
				",\n Statistics: " + statisticsResult +
				"\n";

	}
}
