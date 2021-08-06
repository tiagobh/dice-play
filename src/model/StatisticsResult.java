package model;

public class StatisticsResult {

	private double mean;
	private double variance;
	private double standardDeviation;

	public StatisticsResult() {

	}

	public double getMean() {

		return mean;
	}

	public void setMean(double mean) {

		this.mean = mean;
	}

	public double getVariance() {

		return variance;
	}

	public void setVariance(double variance) {

		this.variance = variance;
	}

	public void setStandardDeviation(double standardDeviation) {

		this.standardDeviation = standardDeviation;
	}

	@Override
	public String toString() {

		return "\nStatisticsResult{" +
				"mean= " + mean +
				", variance= " + variance +
				", standardDeviation= " + standardDeviation +
				'}';
	}
}
