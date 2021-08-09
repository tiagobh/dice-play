import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import model.Die;
import model.Game;
import model.GameResult;
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
			final GameResult gameResult;
			try {
				System.out.println("Begin the game: " + game.getName());
				gameResult = gameService.execute(game);
				System.out.println(gameResult);
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
}
