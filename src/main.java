import java.util.Arrays;
import java.util.List;

import model.BetResult;
import model.Die;
import model.Game;
import model.StatisticsResult;
import service.GameService;
import service.StatisticsService;

public class main {

	public static void main(String[] args) {

		Die dieOne = new Die("Die One");
		Die dieTwo = new Die("Die Two");

		Game gameOne = new Game("When got 6 in 4 attempts with one die", 6, 4, 1000000, Arrays.asList(dieOne));
		Game gameTwo = new Game("When got 4 in 24 attempts with two dice", 4, 24, 1000000, Arrays.asList(dieOne, dieTwo));

		GameService gameService = GameService.getInstance();
		StatisticsService statisticsService = StatisticsService.getInstance();

		List<Game> games = Arrays.asList(gameOne, gameTwo);

		games.parallelStream().forEach(game -> {
			final BetResult betResult = gameService.execute(game);

			final StatisticsResult gameStatistics = statisticsService.execute(betResult);
			System.out.println(betResult +" "+ gameStatistics);

		});
	}
}
