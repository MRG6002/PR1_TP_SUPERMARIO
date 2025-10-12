//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.logic.gameobjects;

import java.util.List;

import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mario {

	private Position pos;
	private Position posGrande;
	private boolean derecha;
	private boolean parado;
	private boolean big;
	private boolean cayendo;
	private ActionList actions = new ActionList();
	private Game game;
	
	//interacciones con game
	public void recibeGame(Game game) {
		this.game = game;
	}
	
	public void marioExited() {
		this.game.marioExited();
	}
	
	//actualizaciones para Mario
	public void update(List <Land> listLand) { 
		//automático
		Position pos;
		if(this.actions.size() == 0) {
			if(this.derecha) pos = new Position(1, 0);
			else pos = new Position(-1, 0);
			if(this.MarioColisiona(listLand, new Position(0,1))) {
				if(this.MarioColisiona(listLand, pos) || this.pos.EsBorde(this.derecha)) {
					this.derecha = !this.derecha;
					if(this.derecha) pos = new Position(1, 0);
					else pos = new Position(-1, 0);
				}
				this.cambiarPos(this.pos.sumar(pos));
			}
			else {
				if(this.pos.estaAbajo()) {
					//mario muere o es herido
				}
				else this.cambiarPos(this.pos.sumar(new Position(0, 1)));
			}
		}
		else {
			this.actions.restricciones();
			for(int i = 0; i < this.actions.size(); i++) {
				pos = new Position(this.actions.getX(i), this.actions.getY(i));
				if(!this.MarioColisiona(listLand, pos)) {
					this.cambiarPos(this.pos.sumar(pos));
				}
				this.game.doInteractionsFrom(this);
			}
		}
		this.actions = new ActionList();
	}
	
	public boolean MarioColisiona(List <Land> listLand, Position pos) {
		for(Land land: listLand) {
			if(this.big) {
				if(land.estaEnPos(this.pos.sumar(pos)) || land.estaEnPos(this.posGrande.sumar(pos))) {
					return true;
				}
			}
			else if(land.estaEnPos(this.pos.sumar(pos))) return true;
		}
		return false;
	}
	
	public boolean interactWith(ExitDoor door) {
		return door.estaEnPos(this.pos);
	}
	
	public boolean interactWith (Goomba goomba) {
		boolean hayContacto = false;
		if(goomba.estaEnPos(this.pos)) {
			if(this.big && !this.cayendo) {
				this.big = false;
				this.posGrande = null;
			}
			else if (!this.cayendo) this.game.perderVida();
			game.sumar100();
			goomba.recieveInteraction(this);
			hayContacto = true;
		}
		return hayContacto;
	}
	
	public void cambiarPos(Position pos) {
		if(pos.esValida() && pos.sumar(new Position (0, -1)).esValida()) {
			this.parado = true;
			this.cayendo = false;
			if(this.pos.enDerechaDe(pos)) {
				this.derecha = false;
				this.parado = false;
			}
			else if (this.pos.enIzquierdaDe(pos)){
				this.derecha = true;
				this.parado = false;
			}
			else if (this.pos.encimaDe(pos)) this.cayendo = true;
			this.pos = new Position(pos);
			if(this.big) this.posGrande = new Position(pos.sumar(new Position (0, -1)));
			
		}
	}
	
	//operaciones de mario
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
	
	public Mario(Position pos) {
		if(pos.esValida() && pos.sumar(new Position (0, -1)).esValida()) {
			this.pos = new Position(pos);
			this.posGrande = new Position(pos.sumar(new Position (0, -1)));
		}
		this.derecha = true;
		this.big = true;
		this.parado = false;
	}
	
	@Override
	public String toString () {
		return "Mario " + this.pos.toString() + " " + this.getIcon() + " Big:" + this.big + " Cayendo:" + this.cayendo; 
	}
	
	public void addAction(Action action) {
		this.actions.add(action);
	}
	
	public boolean estaEnPos(Position pos) {
		return this.pos.equals(pos) || (this.posGrande != null && this.posGrande.equals(pos));
	}
}
