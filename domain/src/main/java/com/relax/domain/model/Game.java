package com.relax.domain.model;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class Game {

	@NotBlank
	private String name;

	@NotNull
	@Range(min = 1, max = 6, message = "Must be between 1 and 6")
	private int lockNumber;

	@NotNull
	@Min(value = 1, message = "Must be great than Zero")
	private int attempts;

	@NotNull
	@Min(value = 1, message = "Must be great than Zero")
	private int bets;

	@NotEmpty(message = "A least one die must be present")
	private List<Die> dice;

	public Game() {

	}

	public Game(String name, int lockNumber, int attempts, int bets, List<Die> dice) {

		this.name = name;
		this.lockNumber = lockNumber;
		this.attempts = attempts;
		this.bets = bets;
		this.dice = dice;
	}

	public String getName() {

		return name;
	}

	public int getLockNumber() {

		return lockNumber;
	}

	public int getAttempts() {

		return attempts;
	}

	public int getBets() {

		return bets;
	}

	public List<Die> getDice() {

		return dice;
	}
}
