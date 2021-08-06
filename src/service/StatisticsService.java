package service;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import model.BetResult;
import model.StatisticsResult;

public class StatisticsService {

	private static StatisticsService instance;

	private StatisticsService() {

	}

	public static StatisticsService getInstance() {

		if (nonNull(instance)) {
			return instance;
		}
		instance = new StatisticsService();
		return instance;
	}

	public StatisticsResult execute(BetResult betResult) {

		StatisticsResult statisticsResult = new StatisticsResult();
		statisticsResult.setMean(getMean(betResult.getWinNumbers()));
		statisticsResult.setVariance(getVariance(statisticsResult.getMean(), betResult.getWinNumbers()));
		statisticsResult.setStandardDeviation(Math.sqrt(statisticsResult.getVariance()));

		return statisticsResult;
	}

	private double getVariance(double mean, List<Integer> winNumbers) {

		List<Double> squareVariance = Collections.synchronizedList(new ArrayList<>());
		winNumbers.stream().parallel().forEach(number -> {
			final double distance = Math.abs(number - mean);
			final double squareDistance = Math.pow(distance, 2);
			squareVariance.add(squareDistance);
		});

		final Double sumSquareVariance = squareVariance.stream().parallel().reduce(Double::sum).orElse(0.0);

		return sumSquareVariance / winNumbers.size();
	}

	private double getMean(List<Integer> source) {

		final Optional<Integer> sum = source.stream().parallel().reduce(Integer::sum);
		if (!sum.isPresent()) {
			return 0.0;
		}

		return sum.get() / source.size();
	}
}
