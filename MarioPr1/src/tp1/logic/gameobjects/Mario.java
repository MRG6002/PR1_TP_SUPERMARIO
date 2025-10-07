package tp1.logic.gameobjects;

import java.util.List;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mario {

	//TODO fill your code
	private Position pos;
	private Position posGrande;
	private boolean derecha;
	private boolean parado;
	private boolean big;
	private Game game;
	
	/**
	 *  Implements the automatic update	
	 */
	public boolean update(List <Land> listLand) { //actualizaciones para Mario
		//automático
		boolean marioMuere = false;
		Position pos;
		if(this.derecha) pos = new Position(1, 0);
		else pos = new Position(-1, 0);
		if(this.MarioColisiona(listLand, new Position(0,1))) {
			if(this.MarioColisiona(listLand, pos) || this.pos.EsBorde()) {
				this.derecha = !this.derecha;
				if(this.derecha) pos = new Position(1, 0);
				else pos = new Position(-1, 0);
			}
			this.cambiarPos(this.pos.sumar(pos));
		}
		else {
			if(this.pos.estaAbajo()) {
				marioMuere = true;
			}
			else this.cambiarPos(this.pos.sumar(new Position(0, 1)));
		}
		return marioMuere;
	}
	
	public boolean MarioColisiona(List <Land> listLand, Position pos) {
		for(Land land: listLand) {
			if(land.estaEnPos(this.pos.sumar(pos))) {
				return true;
			}
		}
		return false;
	}
	
	public String getIcon() {
		String aux;
		if(this.parado) {
			aux = Messages.MARIO_STOP;
		}
		else {
			if(this.derecha) {
				aux = Messages.MARIO_RIGHT;
			}
			else aux = Messages.MARIO_LEFT;
		}
		return aux;
	}
	
	public Mario (int x, int y) {
		if(Position.esValida(x,y) && Position.esValida(x, y - 1)) {
			this.pos = new Position(x, y);
			this.posGrande = new Position(x, y -1);
		}
		this.derecha = true;
		this.big = true;
		this.parado = true;
	}
	public Mario(Position pos) {
		if(pos.esValida() && pos.sumar(new Position (0, -1)).esValida()) {
			this.pos = new Position(pos);
			this.posGrande = new Position(pos.sumar(new Position (0, -1)));
		}
		this.derecha = true;
		this.big = true;
		this.parado = true;
	}
	public String toString(Mario mario) {
		return mario.pos.toString() + " " + mario.getIcon() + " " + mario.big; 
	}
	public void cambiarPos(Position pos) {
		if(pos.esValida() && pos.sumar(new Position (0, -1)).esValida()) {
			this.pos = new Position(pos);
			this.posGrande = new Position(pos.sumar(new Position (0, -1)));
		}
	}
	public void hacerGrande(boolean grande) {
		this.big = grande;
	}
	public void hacerDerecha (boolean derecha) {
		this.derecha = derecha;
	}
	public boolean esGrande() {
		return this.big;
	}
	public boolean esDerecha() {
		return this.derecha;
	}
	public boolean esParado() {
		return this.parado;
	}
	public boolean estaEnPos(Position pos) {
		return this.pos.equals(pos) || (this.posGrande != null && this.posGrande.equals(pos));
	}
}
