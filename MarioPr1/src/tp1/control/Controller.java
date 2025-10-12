//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.control;

import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.logic.Action;


public class Controller {

	private Game game;
	private GameView view;

	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
		this.game.pasarGameAMario(game);
	}
	
	private boolean commands(String [] prompt) {
		boolean exit = false;
		
		if(prompt[0].equalsIgnoreCase("exit") || prompt[0].equalsIgnoreCase("e")) {
			if(prompt.length == 1) exit = true;	
			else view.showMessage(Messages.ERROR.formatted(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
			
		}
		else if((prompt[0].equalsIgnoreCase("help") || prompt[0].equalsIgnoreCase("h"))) {
			if(prompt.length == 1) view.showMessage(Messages.HELP);
			else view.showMessage(Messages.ERROR.formatted(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
		}
		else if(prompt[0].equalsIgnoreCase("reset") || prompt[0].equalsIgnoreCase("r")) {
			if(prompt.length == 1) {
				game.resetGame();
				view.showGame();
			}
			else if(prompt.length == 2) {
				game.resetGame(Integer.parseInt(prompt[1]));
				view.showGame();
			}
			else view.showMessage(Messages.ERROR.formatted(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
			
		}
		else if(prompt[0].equalsIgnoreCase("action") || prompt[0].equalsIgnoreCase("a")) {
			if(prompt.length == 0) view.showMessage(Messages.ERROR.formatted(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
			else {
				for(int i = 1; i < prompt.length; i++) {
					if(prompt[i].equalsIgnoreCase("up") || prompt[i].equalsIgnoreCase("u")) {
						game.addAction(Action.UP);
					}
					else if(prompt[i].equalsIgnoreCase("down") || prompt[i].equalsIgnoreCase("d")) {
						game.addAction(Action.DOWN);
					}
					else if(prompt[i].equalsIgnoreCase("right") || prompt[i].equalsIgnoreCase("r")) {
						game.addAction(Action.RIGHT);
					}
					else if(prompt[i].equalsIgnoreCase("left") || prompt[i].equalsIgnoreCase("l")) {
						game.addAction(Action.LEFT);
					}
					else if(prompt[i].equalsIgnoreCase("stop") || prompt[i].equalsIgnoreCase("s")) {
						game.addAction(Action.STOP);
					}
					else view.showMessage(Messages.ERROR.formatted(Messages.UNKNOWN_ACTION.formatted(prompt[i])));
				}
				game.update();
				view.showGame();
			}
		}
		else if(prompt[0].equalsIgnoreCase("update") || prompt[0].equalsIgnoreCase("u") || prompt[0].equals("")) {
			if(prompt.length == 1) {
				game.update();
				view.showGame();
			}
			else view.showMessage(Messages.ERROR.formatted(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));
		}
		else  view.showMessage(Messages.ERROR.formatted(Messages.UNKNOWN_COMMAND.formatted(prompt[0])));
		return exit;
	}
	
	public void run() {
		String [] prompt;
		view.showWelcome();
		view.showGame();
		boolean exit = false;
		while(!game.isFinished() && !exit) {
			prompt = view.getPrompt();
			exit = commands(prompt);
		}
		view.showEndMessage();
	}

}
