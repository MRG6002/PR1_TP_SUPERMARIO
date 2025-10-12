//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class Land {
	private Position pos;
	
	public String getIcon() {
		return Messages.LAND;
	}
	
	public Land (Position pos) {
		if(pos.esValida()) {
			this.pos = new Position(pos);
		}
	}
	
	public boolean estaEnPos(Position pos) {
		return this.pos.equals(pos);
	}
	
	@Override
	public String toString() {
		return "Land " + this.pos.toString();
	}
}
