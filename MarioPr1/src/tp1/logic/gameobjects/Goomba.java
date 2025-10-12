//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.logic.gameobjects;

import java.util.List;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba {
	private Position pos;
	private boolean izquierda = true;
	private boolean vivo = true;
	
	public String getIcon() {
		return Messages.GOOMBA;
	}
	
	public boolean estaVivo() {
		return this.vivo;
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
	
	public void update(List <Land> listLand) { //actualizaciones para Goombas
		Position pos;
		if(this.izquierda) pos = new Position(-1, 0);
		else pos = new Position(1, 0);
		if(this.GoombaColisiona(listLand, new Position(0,1))) {
			if(this.GoombaColisiona(listLand, pos) || this.pos.EsBorde(!this.izquierda)) {
				this.izquierda = !this.izquierda;
				if(this.izquierda) pos = new Position(-1, 0);
				else pos = new Position(1, 0);
			}
			this.cambiarPos(this.pos.sumar(pos));
		}
		else {
			if(this.pos.estaAbajo()) {
				this.vivo = false;
			}
			else this.cambiarPos(this.pos.sumar(new Position(0, 1)));
		}
	}
	
	public boolean GoombaColisiona(List <Land> listLand, Position pos) {
		for(Land land: listLand) {
			if(land.estaEnPos(this.pos.sumar(pos))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean recieveInteraction(Mario mario) {
		boolean hayContacto = false;
		if(mario.estaEnPos(this.pos)) {
			this.vivo = false;
			hayContacto = true;
		}
		return hayContacto;
	}
	
	@Override
	public String toString() {
		return "Goomba " + this.pos.toString() + " vivo:" + this.vivo + " izquierda:" + this.izquierda;
	}
}
