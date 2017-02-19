package DungeonKeep;

import java.util.*;

public class Board {
	public char[][][] board = { { { 'X', 'X', 'X', 'X', 'X', 'I', 'I', 'X', 'X', 'X' },
			{ 'X', 'H', 'X', ' ', 'X', ' ', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'I', 'X', ' ', ' ', 'X', 'I', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'I', 'X', 'I', 'X', ' ', ' ', 'X', 'I', 'X' },
			{ 'X', ' ', 'X', ' ', 'X', ' ', ' ', 'X', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', ' ', ' ', 'X', 'X', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', 'k', 'X' }, { 'X', 'G', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } },

			{ { 'X', 'I', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'H', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'k', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } } };

	public int level;
	// public ArrayList<Entity> entities = new ArrayList<Entity>();
	public Vector<Entity> entities = new Vector<Entity>();
	public Vector<Door> doors = new Vector<Door>();
	public Hero hero = new Hero();
	public Key key = new Key();

	public Board(int level) {
		this.level = level;
	}

	public void printBoard() {
		for (int i = 0; i < board[level].length; i++) {
			for (int j = 0; j < board[level][i].length; j++) {
				System.out.print((char) board[level][j][i] + " ");
			}
			System.out.println();
		}
	}

	public void updateEntity(char entity, int oldX, int oldY, int newX, int newY, int level) {
		board[level][newX][newY] = entity;
		board[level][oldX][oldY] = ' ';
	}

	public void startEntities(int level) {

		for (int i = 0; i < board[level].length; i++) {
			for (int j = 0; j < board[level].length; j++) {
				if (board[level][i][j] == 'H') {
					hero.startAtPosition(i, j);
				}

				else if (board[level][i][j] == 'G') {
					Guard guard = new Guard();
					guard.startAtPosition(i, j);
					entities.add(guard);
				}

				else if (board[level][i][j] == 'O') {
					Ogre ogre = new Ogre();
					ogre.startAtPosition(i, j);
					entities.add(ogre);
				}

				else if (board[level][i][j] == 'k') {
					key.startAtPosition(i, j);

				}

				else if (board[level][i][j] == 'I') {
					Door door = new Door();
					door.startAtPosition(i, j);
					doors.add(door);
				}
			}
		}
	}

	public boolean updateBoard() {
		// Iterator<Entity> iterEnt = entities.iterator();
		// while(iterEnt.hasNext()){
		// Entity element = iterEnt.next();
		// if((element instanceof Key) && (element.coordX == hero.coordX)
		// && (element.coordY == hero.coordY))
		// hero.hasKey = true;
		// //((Key)element).openDoors(hero);
		// }
		// System.out.println(hero.hasKey);

		if (key.coordX == hero.coordX && key.coordY == hero.coordY) {
			board[level][key.coordX][key.coordY] = 'K';
			key.openDoors(hero);
		} else {
			board[level][key.coordX][key.coordY] = 'k';
		}

		// update das keys
		if (hero.hasKey) {
			for (int index1 = 0; index1 < board[level].length; index1++) {
				for (int index2 = 0; index2 < board[level][index1].length; index2++) {
					if (board[level][index1][index2] == 'I')
						board[level][index1][index2] = 'S';
				}
			}
			for (int i = 0; i < doors.size(); i++) {
				if(hero.coordX == doors.get(i).coordX && hero.coordY == doors.get(i).coordY)
					return true;
			}

		}
		
		
		
		return false;

	}
}
