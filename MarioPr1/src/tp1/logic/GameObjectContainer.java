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
	//TODO fill your code
	
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
	
	private ExitDoor door;
	public void add(ExitDoor door) {
		this.door = door;
	}
	
	private List<Goomba> listGoomba;
	public void listaGoomba() {
		this.listGoomba = new ArrayList<>();
	}
	public void add(Goomba goomba) {
		this.listGoomba.add(goomba);
	}
	private boolean hayGoombaAqui(Position pos) {
		boolean loHay = false;
		int cont = 0; 
		while(cont < this.listGoomba.size() && !loHay) {
			if(this.listGoomba.get(cont).estaEnPos(pos)) {
				loHay = true;
			}
			cont++;
		}
		return loHay;
	}
	
	private Mario mario;
	public void add(Mario mario) {
		this.mario = mario;
	}
	
	
	public GameObjectContainer() {
		this.listaLand();
		this.listaGoomba();
		this.mario = new Mario(new Position(0, 0));
		this.door = new ExitDoor (new Position (0, 1));
	}
	
	public boolean marioEnDoor() { //esto hay que cambiarlo 
		int x = 0, y = 0;
		boolean encontrado = false;
		while (x < Game.DIM_X && !encontrado) {
			while(y < Game.DIM_Y && !encontrado) {
				if(mario.estaEnPos(new Position(x, y))) {
					if(door.estaEnPos(new Position(x,y))){
						encontrado = true;
					}
				}
				y++;
			}
			x++;
		}
		return encontrado; //este no es necesario, pero lo ponemos por si acaso.
	}
	
	public String ContainerEnPos(Position pos) {
		String aux = "";
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
	
}
