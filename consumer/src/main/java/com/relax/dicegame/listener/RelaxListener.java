package com.relax.dicegame.listener;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.relax.dicegame.service.GameResultService;
import com.relax.dicegame.service.GameService;
import com.relax.domain.model.Game;
import com.relax.domain.model.GameResult;

@Service
public class RelaxListener {

	private final GameService gameService;
	private final GameResultService gameResultService;

	public RelaxListener(GameService gameService, GameResultService gameResultService) {

		this.gameService = gameService;
		this.gameResultService = gameResultService;
	}

	@KafkaListener(topics = "game-topic", groupId = "group_id", containerFactory = "gameConcurrentKafkaListenerContainerFactory")
	public void consumeGame(ConsumerRecord<String, Game> gameConsumerRecord) {

		Game game = gameConsumerRecord.value();
		String requestTraceId = gameConsumerRecord.key();
		GameResult gameResult = null;
		try {
			gameResult = gameService.execute(game);
			gameResult.setRequestTraceId(requestTraceId);
			gameResultService.save(gameResult);
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
