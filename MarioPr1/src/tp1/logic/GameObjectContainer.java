//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Mario;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Goomba;
import tp1.view.Messages;


public class GameObjectContainer {
	
	//lands
	private List<Land> listLand;
	public void listaLand() {
		this.listLand = new ArrayList<>();
	}
	public void add(Land land) {
		this.listLand.add(land);
	}
	private boolean hayLandAqui(Position pos) {
		for(Land land: listLand) {
			if(land.estaEnPos(pos)) {
				return true;
			}
		}
		return false;
	}
	
	//exit door
	private ExitDoor door;
	public void add(ExitDoor door) {
		this.door = door;
	}
	
	//goombas
	private List<Goomba> listGoomba;
	public void listaGoomba() {
		this.listGoomba = new ArrayList<>();
	}
	public void add(Goomba goomba) {
		this.listGoomba.add(goomba);
	}
	private boolean hayGoombaAqui(Position pos) {
		for(Goomba goomba: this.listGoomba) {
			if(goomba.estaEnPos(pos)) {
				return true;
			}
		}
		return false;
	}
	
	//mario
	private Mario mario;
	public void add(Mario mario) {
		this.mario = mario;
	}
	
	//constructor general
	public GameObjectContainer() {
		this.listaLand();
		this.listaGoomba();
		this.mario = new Mario(new Position(0, 0));
		this.door = new ExitDoor (new Position (0, 1));
	}
	
	//funciones
	public String ContainerEnPos(Position pos) {
		String aux = Messages.EMPTY;
		if(this.mario.estaEnPos(pos)) {
			aux = mario.getIcon();
		}
		else if(this.door.estaEnPos(pos)) {
			aux = Messages.EXIT_DOOR;
		}
		else if(this.hayGoombaAqui(pos)) {
			aux = Messages.GOOMBA;
		}
		else if(this.hayLandAqui(pos)) {
			aux = Messages.LAND;
		}
		return aux;
	}
	
	public void update() {
		this.mario.update(this.listLand);
		if(this.mario.interactWith(door)) {
			this.mario.marioExited();
		}
		for(Goomba goomba: this.listGoomba) {
			goomba.update(this.listLand);
		}
		this.doInteractionsFrom(this.mario);
		this.listGoomba.removeIf(goomba -> !goomba.estaVivo());
	}
	
	public void addAction(Action action) {
		this.mario.addAction(action);
	}
	
	public void doInteractionsFrom(Mario mario) {
		for(int i = 0; i < this.listGoomba.size(); i++) {
			mario.interactWith(this.listGoomba.get(i));
		}
	}
}
