package com.relax.dicegame.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.relax.dicegame.model.Bet;
import com.relax.dicegame.model.BetResult;
import com.relax.dicegame.model.Die;

@Component
public class BetService {

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
