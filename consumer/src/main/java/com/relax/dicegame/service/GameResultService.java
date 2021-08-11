package com.relax.dicegame.service;

import org.springframework.stereotype.Service;

import com.relax.dicegame.repository.GameResultRepository;
import com.relax.domain.model.GameResult;

@Service
public class GameResultService {

	private GameResultRepository resultRepository;

	public GameResultService(GameResultRepository resultRepository) {

		this.resultRepository = resultRepository;
	}

	public void save(GameResult gameResult) {

		resultRepository.save(gameResult);
	}
}
