package com.relax.dice.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.relax.domain.model.GameResult;

public interface GameResultRepository extends MongoRepository<GameResult, String> {

	List<GameResult> findByRequestTraceId(String requestTraceId);
}
