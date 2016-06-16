package org.angryautomata;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.angryautomata.game.*;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		Position origin1 = new Position(0, 62);
		int[][] transitions1 = {
				{0},
				{0},
				{0},
				{0}
		};
		int[][] actions1 = {
				{0}, // desert
				{2}, // lac
				{4}, // prairie
				{6}  // foret
		};

		Automaton automaton1 = new Automaton(transitions1, actions1, origin1);
		Player player1 = new Player(automaton1, "MonAutomateCool", 0xFFBF0D0D);

		Position origin2 = new Position(3, 6);
		int[][] transitions2 = {
				{0},
				{0},
				{0},
				{0}
		};
		int[][] actions2 = {
				{0}, // desert
				{2}, // lac
				{4}, // prairie
				{6}  // foret
		};

		Automaton automaton2 = new Automaton(transitions2, actions2, origin2);
		Player player2 = new Player(automaton2, "TonAutomateNase", 0xFF916012);

		Position origin3 = new Position(7, 12);
		int[][] transitions3 = {
				{0},
				{0},
				{0},
				{0}
		};
		int[][] actions3 = {
				{0}, // desert
				{2}, // lac
				{4}, // prairie
				{6}  // foret
		};

		Automaton automaton3 = new Automaton(transitions3, actions3, origin3);
		Player player3 = new Player(automaton3, "SonAutomateMeh", 0xFFFF00FF);

		Board board = new Board(128, 128);

		Controller root = new Controller();
		Game game = new Game(root, board, player1, player2, player3);
		root.setGame(game);

		primaryStage.setTitle("Struggle for Life");
		primaryStage.setResizable(true);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		Thread gameThread = new Thread(game, "Main game loop thread");
		gameThread.setDaemon(true);
		gameThread.start();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
