package model;

import java.util.List;

public class Bet {

	private long id;
	private int attempts;
	private int lockNumber;
	private List<Die> dice;

	public Bet(long id, int attempts, int lockNumber, List<Die> dice) {

		this.id = id;
		this.attempts = attempts;
		this.lockNumber = lockNumber;
		this.dice = dice;
	}

	public long getId() {

		return id;
	}

	public int getAttempts() {

		return attempts;
	}

	public List<Die> getDice() {

		return dice;
	}

	public int getLockNumber() {

		return lockNumber;
	}
}
