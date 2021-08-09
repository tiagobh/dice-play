package com.relax.dicegame.model;

import java.util.Random;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Die {

	@JsonProperty("name")
	private String name;

	public Die() {

	}

	public Die(String name) {

		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public int play() {

		Random random = new Random();
		int rollNumber = random.nextInt(6) + 1;

		return rollNumber;
	}


}
