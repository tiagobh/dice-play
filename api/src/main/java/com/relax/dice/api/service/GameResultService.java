package com.relax.dice.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.relax.dice.api.repository.GameResultRepository;
import com.relax.domain.model.GameResult;

@Service
public class GameResultService {

	private final GameResultRepository resultRepository;

	public GameResultService(GameResultRepository resultRepository) {

		this.resultRepository = resultRepository;
	}

	public List<GameResult> get(final String requestTraceId) {

		return resultRepository.findByRequestTraceId(requestTraceId);
	}
}
