package service;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

		for (int playIndex = 0; playIndex < game.getBets(); playIndex++) {
			boolean win = false;
			List<Integer> rolledNumbers = new ArrayList<>();

			for (int roll = 0; roll < game.getAttempts(); roll++) {
				final List<Integer> rollDieResult = game.getDice().stream().map(Die::play).collect(Collectors.toList());
				rolledNumbers.addAll(rollDieResult);

				if (rollDieResult.stream().allMatch(number -> number.equals(game.getLockNumber()))) {
					result.setWins(result.getWins() + 1);
					win = true;
				}
			}
			if (!win) {
				result.setLoses(result.getLoses() + 1);
			} else {
				result.getWinNumbers().addAll(rolledNumbers);
			}
		}
		result.setElapsedTime(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime));
		return result;
	}
}


