package dkeep.logic;

import java.util.Vector;

import DungeonKeep.Door;
import DungeonKeep.Entity;
import DungeonKeep.Hero;
import DungeonKeep.Key;

public class State {
	char[][] board = new char[10][10];
	public Vector<Entity> entities = new Vector<Entity>();
	public Vector<Door> doors = new Vector<Door>();
	public Hero hero = new Hero();
	public Key key = new Key();
	
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print((char) board[j][i] + " ");
			}
			System.out.println();
		}
	}
}
