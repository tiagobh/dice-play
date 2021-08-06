package model;

import java.util.Random;

public class Die {

	private final String name;

	public Die(String name) {

		this.name = name;

	}

	public int play() {

		Random random = new Random();
		int rollNumber = random.nextInt(6) + 1;

		return rollNumber;
	}

}
