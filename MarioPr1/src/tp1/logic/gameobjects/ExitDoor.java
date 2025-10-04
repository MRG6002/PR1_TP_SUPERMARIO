package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor {
	private Position pos;
	
	public String getIcon() {
		return Messages.EXIT_DOOR;
	}
	
	public ExitDoor (int x, int y) {
		this.pos = new Position(x, y);
		
	}
	public ExitDoor (Position pos) {
		this.pos = new Position(pos);
	}
	
	public boolean estaEnPos(Position pos) {
		return this.pos.equals(pos);
	}
}
