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
	Action direccion;
	private boolean big;
	private boolean cayendo;
	/*private boolean derecha;
	private boolean parado;
	*/
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
			if(this.direccion == Action.RIGHT) pos = new Position(1, 0);
			else pos = new Position(-1, 0);
			if(this.MarioColisiona(listLand, new Position(0,1)) && this.direccion != Action.STOP) {
				if(this.MarioColisiona(listLand, pos) || this.pos.EsBorde(this.direccion == Action.RIGHT)) {
					if(this.direccion == Action.RIGHT) this.direccion = Action.LEFT;
					else if (this.direccion == Action.LEFT) this.direccion = Action.RIGHT;
					if(this.direccion == Action.RIGHT) pos = new Position(1, 0);
					else pos = new Position(-1, 0);
				}
				else this.cambiarPos(this.pos.sumar(pos));
			}
			else {
				if(this.pos.estaAbajo()) {
					this.game.perderVida();
					this.game.resetGame();
				}
				else if (this.direccion != Action.STOP) this.cambiarPos(this.pos.sumar(new Position(0, 1)));
			}
		}
		else {
			this.actions.restricciones();
			for(int i = 0; i < this.actions.size(); i++) {
				pos = new Position(this.actions.getX(i), this.actions.getY(i));
				if(pos.equals(new Position(0, 1))) {
					if (this.MarioColisiona(listLand, new Position(0, 1))) this.direccion = Action.STOP;
					while(!this.MarioColisiona(listLand, new Position(0, 1)) && !this.pos.estaAbajo()) {
						this.cambiarPos(this.pos.sumar(new Position(0, 1)));
					}
					if(this.pos.estaAbajo()) {
						this.game.perderVida();
						if(this.game.numLives()>0) {
							this.game.resetGame();
							this.game.resetGame();
						}
					}
				}
				else if(!this.MarioColisiona(listLand, pos)) {
					this.cambiarPos(this.pos.sumar(pos));
				}
				else if(pos.equals(new Position(1, 0))) this.direccion = Action.LEFT;
				else if(pos.equals(new Position(-1, 0))) this.direccion = Action.RIGHT;
			}
		}
		this.game.doInteractionsFrom(this);
		this.cayendo = false;
		this.actions = new ActionList();
	}
	
	public boolean MarioColisiona(List <Land> listLand, Position pos) {
		for(Land land: listLand) {
			if(this.big == true) {
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
		if(goomba.estaEnPos(this.pos) || (this.big && goomba.estaEnPos(this.posGrande))) {
			if(this.big && !this.cayendo) {
				this.big = false;
				this.posGrande = null;
			}
			else if (!this.cayendo) {
				this.game.perderVida();
				this.game.resetGame();
			}
			goomba.recieveInteraction(this);
			game.sumar100();
			hayContacto = true;
		}
		return hayContacto;
	}
	
	public void cambiarPos(Position pos) {
		if(pos.esValida() && pos.sumar(new Position (0, -1)).esValida()) {
			if(this.pos.enDerechaDe(pos)) {
				this.direccion = Action.LEFT;
			}
			else if (this.pos.enIzquierdaDe(pos)){
				this.direccion = Action.RIGHT;
			}
			else if (this.pos.encimaDe(pos)) this.cayendo = true;
			else if (this.pos.equals(pos)) this.direccion = Action.STOP;
			this.pos = new Position(pos);
			if(this.big) this.posGrande = new Position(pos.sumar(new Position (0, -1)));
		}
	}
	
	public void resetMario(Position pos) {
		if(pos.esValida() && pos.sumar(new Position (0, -1)).esValida()) {
			this.direccion = Action.RIGHT;
			this.big = true;
			this.cayendo = false;
			this.pos = new Position(pos);
			this.posGrande = new Position(pos.sumar(new Position (0, -1)));
		}
	}
	
	//operaciones de mario
	public String getIcon() {
		String aux = "";
		if(this.direccion == Action.STOP) {
			aux = Messages.MARIO_STOP;
		}
		else if(this.direccion == Action.RIGHT) {
			aux = Messages.MARIO_RIGHT;
		}
		else if (this.direccion == Action.LEFT) {
			aux = Messages.MARIO_LEFT;
		}
		return aux;
	}
	
	public Mario(Position pos) {
		if(pos.esValida() && pos.sumar(new Position (0, -1)).esValida()) {
			this.pos = new Position(pos);
			this.posGrande = new Position(pos.sumar(new Position (0, -1)));
		}
		this.direccion = Action.RIGHT;
		this.big = true;
		this.cayendo = false;
	}
	
	@Override
	public String toString () {
		return "Mario " + this.pos.toString() + " " + this.getIcon() + " Big:" + this.big + " Cayendo:" + this.cayendo; 
	}
	
	public void addAction(Action action) {
		this.actions.add(action);
	}
	
	public boolean estaEnPos(Position pos) {
		return this.pos.equals(pos) || (this.big && this.posGrande.equals(pos));
	}

	public int numVidas() {
		return this.game.numLives();
	}
}
