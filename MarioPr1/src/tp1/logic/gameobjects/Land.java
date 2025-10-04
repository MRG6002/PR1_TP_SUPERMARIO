package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class Land {
	private Position pos;
	
	public String getIcon() {
		return Messages.LAND;
	}
	
	public Land (int x, int y) {
		if(Position.esValida(x,y)) {
			this.pos = new Position(x, y);
		}
	}
	public Land (Position pos) {
		if(pos.esValida()) {
			this.pos = new Position(pos);
		}
	}
	
	public boolean estaEnPos(Position pos) {
		return this.pos.equals(pos);
	}
}
