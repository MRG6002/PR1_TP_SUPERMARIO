package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba {
	private Position pos;
	
	public String getIcon() {
		return Messages.GOOMBA;
	}
	
	public Goomba (int x, int y) {
		if(Position.esValida(x,y)) {
			this.pos = new Position(x, y);
		}
	}
	public Goomba (Position pos) {
		if(pos.esValida()) {
			this.pos = new Position(pos);
		}
	}
	
	public void cambiarPos (Position pos) {
		if(pos.esValida()) {
			this.pos = pos;
		}
	}
	
	public boolean estaEnPos(Position pos) {
		return this.pos.equals(pos);
	}
	
	public void update() { //actualizaciones para Goombas
		if ()
	}
}
