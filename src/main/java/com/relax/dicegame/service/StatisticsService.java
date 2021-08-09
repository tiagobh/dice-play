package com.relax.dicegame.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.relax.dicegame.model.BetResult;
import com.relax.dicegame.model.StatisticsResult;

@Component
public class StatisticsService {

	public CompletableFuture<StatisticsResult> execute(List<BetResult> betResults) {

		return CompletableFuture.supplyAsync(() -> {
			final List<Integer> winNumbers = betResults.parallelStream().filter(BetResult::isWin).map(BetResult::getRollNumbers)
					.flatMap(Collection::stream).collect(
							Collectors.toList());

			StatisticsResult statisticsResult = new StatisticsResult();
			statisticsResult.setMean(getMean(winNumbers));
			statisticsResult.setVariance(getVariance(statisticsResult.getMean(), winNumbers));
			statisticsResult.setStandardDeviation(Math.sqrt(statisticsResult.getVariance()));
			return statisticsResult;
		});
	}

	private Double getVariance(double mean, List<Integer> winNumbers) {

		List<Double> squareVariance = Collections.synchronizedList(new ArrayList<>());

		winNumbers.parallelStream().forEach(number -> {
			final double distance = Math.abs(number - mean);
			final double squareDistance = Math.pow(distance, 2);
			squareVariance.add(squareDistance);
		});

		final Double sumSquareVariance = squareVariance.stream().parallel().reduce(Double::sum).orElse(0.0);

		return Double.valueOf(sumSquareVariance / winNumbers.size());

	}

	private Double getMean(List<Integer> source) {

		final Optional<Integer> sum = source.stream().parallel().reduce(Integer::sum);
		if (!sum.isPresent()) {
			return 0.0;
		}

		return Double.valueOf(sum.get() / source.size());
	}
}
