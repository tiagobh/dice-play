package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.Bet;
import model.BetResult;
import model.Die;

public class BetService {

	private static BetService instance;

	private BetService() {

	}

	public static BetService getInstance() {

		if (Objects.nonNull(instance)) {
			return instance;
		}
		instance = new BetService();
		return instance;
	}

	public BetResult execute(Bet bet) {

		List<Integer> temporaryRolledNumbers = Collections.synchronizedList(new ArrayList<>());
		AtomicBoolean hasWin = new AtomicBoolean(false);

		IntStream.rangeClosed(1, bet.getAttempts()).parallel().forEach(attemptNumber -> {
			final List<Integer> rollDiceResult = bet.getDice().stream().map(Die::play).collect(Collectors.toList());

			if (rollDiceResult.stream().allMatch(number -> number.equals(bet.getLockNumber()))) {
				hasWin.getAndSet(true);
			}
			temporaryRolledNumbers.addAll(rollDiceResult);
		});

		return new BetResult(hasWin.get(), temporaryRolledNumbers);
	}
}
