//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.logic;

import java.util.Objects;

/**
 * 
 * TODO: Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public final class Position {

	private final int col;
	private final int row;

	public Position(int x, int y) {
		if(Position.esValida(x,y)) {
			this.col = x;
			this.row = y;
		}
		else { //inicializacion a -1 por si acaso es posicion inválida. Entonces no será detectado el objeto y no causa error.
			this.col = -1;
			this.row = -1;
		}
	}

	public Position(Position pos) {
			this.col = pos.col;
			this.row = pos.row;
	}
	
	public boolean esValida() {
		 return (this.col >= 0 & this.col < Game.DIM_X && this.row >= 0 && this.row < Game.DIM_Y);
	}
	static public boolean esValida(int x, int y) {
		 return (x >= 0 & x < Game.DIM_X && y >= 0 && y < Game.DIM_Y);
	}
	
	public String toString() {
		return "(" + this.col + " " + this.row + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		return this==obj ||
			obj!=null && getClass() == obj.getClass() &&
			this.col == ((Position) obj).col &&
			this.row == ((Position) obj).row;
	}
	@Override 
	public int hashCode() {
		return Objects.hash(col,row);
	}
	
	public Position sumar(Action act) {
		return new Position(this.col + act.getX(), this.row + act.getY());
	}
	
	public Position sumar(Position pos) {
		return new Position(this.col + pos.col, this.row + pos.row);
	}

	
}
