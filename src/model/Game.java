package model;

import java.util.List;

public class Game {

	private String name;
	private int lockNumber;
	private int attempts;
	private int bets;
	private List<Die> dice;

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
