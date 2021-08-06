package model;

import java.util.ArrayList;
import java.util.List;

public class BetResult {

	private Game game;
	private long elapsedTime;
	private int wins;
	private int loses;
	private List<Integer> winNumbers = new ArrayList<>();

	public BetResult(Game game) {

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

	public long getElapsedTime() {

		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {

		this.elapsedTime = elapsedTime;
	}

	public List<Integer> getWinNumbers() {

		return winNumbers;
	}

	public void setWinNumbers(List<Integer> winNumbers) {

		this.winNumbers = winNumbers;
	}

	public Game getGame() {

		return game;
	}

	@Override
	public String toString() {

		return "BetResult{" +
				"\n game= " + game.getName() +
				",\n elapsedTime(seconds)= " + elapsedTime +
				",\n wins= " + wins +
				",\n loses= " + loses +
				'}';
	}
}
