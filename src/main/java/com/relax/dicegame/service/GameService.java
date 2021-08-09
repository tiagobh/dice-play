package com.relax.dicegame.service;

import static java.util.Collections.synchronizedList;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.relax.dicegame.model.Bet;
import com.relax.dicegame.model.BetResult;
import com.relax.dicegame.model.Game;
import com.relax.dicegame.model.GameResult;
import com.relax.dicegame.model.StatisticsResult;

@Component
public class GameService {

	private final BetService betService;
	private final StatisticsService statisticsService;

	public GameService(BetService betService, StatisticsService statisticsService) {

		this.betService = betService;
		this.statisticsService = statisticsService;
	}

	public GameResult execute(Game game) throws ExecutionException, InterruptedException {

		final long startTime = System.currentTimeMillis();

		CompletableFuture<List<BetResult>> betResultsCompletableFuture = supplyAsync(() -> getBetResult(game));
		CompletableFuture<StatisticsResult> statisticsResultCompletableFuture = betResultsCompletableFuture.thenComposeAsync(
				betResults -> statisticsService.execute(betResults));

		final StatisticsResult statisticsResult = statisticsResultCompletableFuture.get();
		final List<BetResult> betResults = betResultsCompletableFuture.get();

		return buildGameResult(game, startTime, betResults, statisticsResult);
	}

	private List<BetResult> getBetResult(Game game) {

		List<BetResult> betResults = synchronizedList(new ArrayList<>());

		IntStream.rangeClosed(1, game.getBets()).parallel().forEach(betNumber -> {
			betResults.add(
					betService.execute(new Bet(betNumber, game.getAttempts(), game.getLockNumber(), game.getDice())));

		});
		return betResults;
	}

	private GameResult buildGameResult(Game game, long startTime, List<BetResult> betResults,
			StatisticsResult statisticsResult) {

		GameResult gameResult = new GameResult(game);
		gameResult.setWins((int) betResults.parallelStream().filter(BetResult::isWin).count());
		gameResult.setLoses(betResults.size() - gameResult.getWins());
		gameResult.setStatisticsResult(statisticsResult);
		gameResult.setStartTime(startTime);
		gameResult.setEndTime(System.currentTimeMillis());
		return gameResult;
	}

}


