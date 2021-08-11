package com.relax.dicegame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.relax.domain.model.GameResult;

public interface GameResultRepository extends MongoRepository<GameResult, String> {

}
