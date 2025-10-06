//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.control;

import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.ViewInterface;
import tp1.view.Messages;

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
	
	private boolean commands(String [] prompt) {
		boolean exit = false;
		if(prompt[0].equalsIgnoreCase("exit") || prompt[0].equalsIgnoreCase("e")) exit = true;	
		else if(prompt[0].equalsIgnoreCase("help") || prompt[0].equalsIgnoreCase("h")) {
			for(int i = 0; i < 6; i++) {
				view.showMessage(Messages.HELP_LINES[i]);
			}
		}
		else if(prompt[0].equalsIgnoreCase("reset") || prompt[0].equalsIgnoreCase("r")) {
			if(prompt.length == 1) {
				game.resetGame();
			}
			else game.resetGame(Integer.parseInt(prompt[1]));
			view.showGame();
		}
		else if(prompt[0].equalsIgnoreCase("action") || prompt[0].equalsIgnoreCase("a")) {
			view.showGame();
		}
		else if(prompt[0].equalsIgnoreCase("update") || prompt[0].equalsIgnoreCase("u") || prompt[0].equals("")) {
			view.showGame();
		}
		else  view.showMessage(Messages.ERROR + Messages.UNKNOWN_COMMAND + "comandoTecleadoPorElUsuario");
		return exit;
	}
	
	public void run() {
		String [] prompt;
		view.showWelcome();
		view.showGame();
		boolean exit = false;
		//TODO fill your code: The main loop that displays the game, asks the user for input, and executes the action.
		while(!game.isFinished() && !exit) {
			prompt = view.getPrompt();
			exit = commands(prompt);
		}
		view.showEndMessage();
	}

}
