package com.relax.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameResult {

	@Id
	private String id;
	private Game game;
	private long startTime;
	private long endTime;
	private int wins;
	private int loses;
	@JsonProperty("statistics")
	private StatisticsResult statisticsResult;

	@JsonIgnore
	private List<BetResult> betResults = new ArrayList<>();

	@JsonProperty
	private String requestTraceId;

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

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getRequestTraceId() {

		return requestTraceId;
	}

	public void setRequestTraceId(String requestTraceId) {

		this.requestTraceId = requestTraceId;
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
