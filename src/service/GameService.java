package service;

import static java.util.Collections.synchronizedList;
import static java.util.Objects.nonNull;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import model.Bet;
import model.BetResult;
import model.Game;
import model.GameResult;
import model.StatisticsResult;

public class GameService {

	private static GameService instance;

	private GameService() {

	}

	public static GameService getInstance() {

		if (nonNull(instance)) {
			return instance;
		}
		instance = new GameService();
		return instance;
	}

	public GameResult execute(Game game) throws ExecutionException, InterruptedException {

		final long startTime = System.currentTimeMillis();

		CompletableFuture<List<BetResult>> betResultsCompletableFuture = supplyAsync(() -> getBetResult(game));
		CompletableFuture<StatisticsResult> statisticsResultCompletableFuture = betResultsCompletableFuture.thenComposeAsync(
				betResults -> StatisticsService.getInstance()
						.execute(betResults));

		final StatisticsResult statisticsResult = statisticsResultCompletableFuture.get();
		final List<BetResult> betResults = betResultsCompletableFuture.get();

		final long endTime = System.currentTimeMillis();
		return buildGameResult(game, startTime, endTime, betResults, statisticsResult);
	}

	private List<BetResult> getBetResult(Game game) {

		List<BetResult> betResults = synchronizedList(new ArrayList<>());

		IntStream.rangeClosed(1, game.getBets()).parallel().forEach(betNumber -> {
			betResults.add(
					BetService.getInstance().execute(new Bet(betNumber, game.getAttempts(), game.getLockNumber(), game.getDice())));

		});
		return betResults;

	}

	private GameResult buildGameResult(Game game, long startTime, long endTime, List<BetResult> betResults,
			StatisticsResult statisticsResult) {

		GameResult gameResult = new GameResult(game);
		gameResult.setStartTime(startTime);
		gameResult.setEndTime(endTime);
		gameResult.setWins((int) betResults.parallelStream().filter(BetResult::isWin).count());
		gameResult.setLoses(betResults.size() - gameResult.getWins());
		gameResult.setStatisticsResult(statisticsResult);
		return gameResult;
	}

}


