package com.relax.dicegame.model;

import java.util.ArrayList;
import java.util.List;

public class BetResult {

	private boolean isWin;
	private List<Integer> rollNumbers = new ArrayList<>();

	public BetResult(boolean isWin, List<Integer> rollNumbers) {

		this.isWin = isWin;
		this.rollNumbers = rollNumbers;
	}

	public boolean isWin() {

		return isWin;
	}

	public List<Integer> getRollNumbers() {

		return rollNumbers;
	}
}
