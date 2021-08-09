package service;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.BetResult;
import model.Die;
import model.Game;

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

	public BetResult execute(Game game) {

		BetResult result = new BetResult(game);
		final long startTime = System.currentTimeMillis();

		List<Integer> winsNumbers = Collections.synchronizedList(new ArrayList<>());
		AtomicInteger winsCount = new AtomicInteger();
		AtomicInteger loseCount = new AtomicInteger();

		IntStream.rangeClosed(1, game.getBets()).parallel().forEach(betNumber -> {

			AtomicBoolean hasWinner = new AtomicBoolean(false);
			List<Integer> temporaryRolledNumbers = new ArrayList<>();

			IntStream.rangeClosed(1, game.getAttempts()).forEach(attemptNumber -> {
				final List<Integer> rollDiceResult = game.getDice().stream().map(Die::play).collect(Collectors.toList());
				temporaryRolledNumbers.addAll(rollDiceResult);

				if (rollDiceResult.stream().allMatch(number -> number.equals(game.getLockNumber()))) {
					winsCount.getAndIncrement();
					hasWinner.getAndSet(true);
				}
			});

			if (!hasWinner.get()) {
				loseCount.getAndIncrement();
			} else {
				winsNumbers.addAll(temporaryRolledNumbers);
			}
		});

		result.setWins(winsCount.get());
		result.setLoses(loseCount.get());
		result.setWinNumbers(winsNumbers);

		result.setElapsedTime(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime));
		return result;
	}
}


