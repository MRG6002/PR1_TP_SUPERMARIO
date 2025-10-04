//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.control;

import java.util.Scanner;
import tp1.logic.Game;
import tp1.view.GameView;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private GameView view;

	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 * 
	 */
	public void run() {
		view.showWelcome();
		Scanner scanner = new Scanner(System.in);
		char c = scanner.next().char
		//TODO fill your code: The main loop that displays the game, asks the user for input, and executes the action.
		while() {
			view.showGame();
		}
		view.showEndMessage();
	}

}
