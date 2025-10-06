//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.logic;

import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Mario;
import tp1.logic.gameobjects.Goomba;

public class Game {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	private int tiempoRestante = 100;
	private int puntos = 0;
	private int numVidas = 3;
	private int nLevel;
	private Mario mario;
	
	private GameObjectContainer GameObjectContainer;

	//TODO fill your code
	
	private void initLevel0() {
		//Lands
		for(int i = 0; i < 15; i++) {
			this.GameObjectContainer.add(new Land(new Position(i, 14)));
			this.GameObjectContainer.add(new Land(new Position(i, 13)));
		}
		for(int i = 17; i < 30; i++) {
			this.GameObjectContainer.add(new Land(new Position(i, 14)));
			this.GameObjectContainer.add(new Land(new Position(i, 13)));
		}
		int cont = 0;
		for(int i = 19 + cont; i < 27; i++) {
			for(int j = 12; j > 11 - cont; j--) {
				this.GameObjectContainer.add(new Land(new Position(i, j)));
			}
			cont++;
		}
		this.GameObjectContainer.add(new Land(new Position(9,12)));
		this.GameObjectContainer.add(new Land(new Position(12, 12)));
		this.GameObjectContainer.add(new Land(new Position(2, 9)));
		this.GameObjectContainer.add(new Land(new Position(5, 9)));
		this.GameObjectContainer.add(new Land(new Position(6, 9)));
		this.GameObjectContainer.add(new Land(new Position(7, 9)));
		this.GameObjectContainer.add(new Land(new Position(6, 5)));
		//Mario y puerta
		this.GameObjectContainer.add(new Mario(new Position(0, 12)));
		this.GameObjectContainer.add(new ExitDoor(new Position(29, 12)));
		//Goombas
		this.GameObjectContainer.add(new Goomba(new Position(19, 0)));
	}
	
	private void initLevel1() {
		//Lands
		for(int i = 0; i < 15; i++) {
			this.GameObjectContainer.add(new Land(new Position(i, 14)));
			this.GameObjectContainer.add(new Land(new Position(i, 13)));
		}
		for(int i = 17; i < 30; i++) {
			this.GameObjectContainer.add(new Land(new Position(i, 14)));
			this.GameObjectContainer.add(new Land(new Position(i, 13)));
		}
		int cont = 0;
		for(int i = 19 + cont; i < 27; i++) {
			for(int j = 12; j > 11 - cont; j--) {
				this.GameObjectContainer.add(new Land(new Position(i, j)));
			}
			cont++;
		}
		this.GameObjectContainer.add(new Land(new Position(9,12)));
		this.GameObjectContainer.add(new Land(new Position(12, 12)));
		this.GameObjectContainer.add(new Land(new Position(2, 9)));
		this.GameObjectContainer.add(new Land(new Position(5, 9)));
		this.GameObjectContainer.add(new Land(new Position(6, 9)));
		this.GameObjectContainer.add(new Land(new Position(7, 9)));
		this.GameObjectContainer.add(new Land(new Position(6, 5)));
		//Mario y puerta
		this.GameObjectContainer.add(new Mario(new Position(0, 12)));
		this.GameObjectContainer.add(new ExitDoor(new Position(29, 12)));
		//Goombas
		this.GameObjectContainer.add(new Goomba(new Position(6, 12)));
		this.GameObjectContainer.add(new Goomba(new Position(8, 12)));
		this.GameObjectContainer.add(new Goomba(new Position(11, 12)));
		this.GameObjectContainer.add(new Goomba(new Position(14, 12)));
		this.GameObjectContainer.add(new Goomba(new Position(10, 10)));
		this.GameObjectContainer.add(new Goomba(new Position(6, 4)));
		this.GameObjectContainer.add(new Goomba(new Position(19, 0)));
	}
	
	public Game(int nLevel) {
		// TODO Auto-generated constructor stub
		//Permitira a la larga preguntar si se quieren añadir mas mapas
		this.GameObjectContainer = new GameObjectContainer();
		if(nLevel == 0) {
			initLevel0();
			this.nLevel = 0;
		}
		if(nLevel == 1) {
			initLevel1();
			this.nLevel = 1;
		}
	}
	
	private void inicializarGameObjectContainer(int level) {
		if(level == 0) {
			initLevel0();
			this.nLevel = 0;
		}
		if(level == 1) {
			initLevel1();
			this.nLevel = 1;
		}
	}
	
	public void resetGame(int nLevel) {
		this.GameObjectContainer = new GameObjectContainer();
		if(nLevel == 0 || nLevel == 1) {
			this.inicializarGameObjectContainer(nLevel);
		}
		else resetGame();
	}
	
	public void resetGame() { 
		this.GameObjectContainer = new GameObjectContainer();
		this.inicializarGameObjectContainer(this.nLevel);
	}

	
	public String positionToString(int col, int row) {
		return GameObjectContainer.ContainerEnPos(new Position(col,row));
	}

	public boolean playerWins() {
		return GameObjectContainer.marioEnDoor();
	}

	public int remainingTime() {
		return this.tiempoRestante;
	}

	public int points() {
		return this.puntos;
	}

	public int numLives() {
		return this.numVidas;
	}

	@Override
	public String toString() {
		// TODO returns a textual representation of the object
		return this.numVidas + "vidas " + this.puntos + "ptos " + this.tiempoRestante + "s";
	}

	public boolean playerLoses() {
		return this.numVidas == 0 || this.tiempoRestante == 0;
	}

	public boolean isFinished() {
		return this.playerLoses() || this.playerWins();
	}
	
	/*
	private void initLevel0() {
		this.nLevel = 0;
		this.remainingTime = 100;
		
		// 1. Mapa
		gameObjects = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
			gameObjects.add(new Land(new Position(13,col)));
			gameObjects.add(new Land(new Position(14,col)));		
		}

		gameObjects.add(new Land(new Position(Game.DIM_Y-3,9)));
		gameObjects.add(new Land(new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			gameObjects.add(new Land(new Position(Game.DIM_Y-2, col)));
			gameObjects.add(new Land(new Position(Game.DIM_Y-1, col)));		
		}

		gameObjects.add(new Land(new Position(9,2)));
		gameObjects.add(new Land(new Position(9,5)));
		gameObjects.add(new Land(new Position(9,6)));
		gameObjects.add(new Land(new Position(9,7)));
		gameObjects.add(new Land(new Position(5,6)));
		
		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				gameObjects.add(new Land(new Position(posIniY- fila, posIniX+ col)));
			}
		}

		gameObjects.add(new ExitDoor(new Position(Game.DIM_Y-3, Game.DIM_X-1)));

		// 3. Personajes
		this.mario = new Mario(this, new Position(Game.DIM_Y-3, 0));
		gameObjects.add(this.mario);

		gameObjects.add(new Goomba(this, new Position(0, 19)));
	}
	*/
}
