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

	public boolean updateBoard(Boolean lost) {

		if (key.coordX == hero.coordX && key.coordY == hero.coordY) {
			board[level][key.coordX][key.coordY] = 'K';
			key.openDoors(hero);
		} else {
			board[level][key.coordX][key.coordY] = 'k';
		}

		// update das keys --> assume que a porta está na parede da esquerda
		if (hero.hasKey) {
			for (int i = 0; i < board[level][0].length; i++) {
				if (board[level][0][i] == 'I')
					board[level][0][i] = 'S';
			}

			// for (int index1 = 0; index1 < board[level].length; index1++) {
			// for (int index2 = 0; index2 < board[level][index1].length;
			// index2++) {
			// if (board[level][index1][index2] == 'I' && index1 == 0)
			// board[level][index1][index2] = 'S';
			// }
			// }

			for (int i = 0; i < doors.size(); i++) {
				if (hero.coordX == doors.get(i).coordX && hero.coordY == doors.get(i).coordY)
					return true;
			}

		}

		// check if player lost before moving the enities
		if (Game.checkIfLose(this, level)) {
			lost = true;
			return false;
		}

		// moving entities

		for (int index_entities = 0; index_entities < entities.size(); index_entities++) {
			Entity element = entities.get(index_entities);
			if ((element instanceof Guard) && level == 0) { // mexe guarda
															// caminho
															// predefinido
				Guard guarda = (Guard) element;
				int comando = guarda.moves_pre2etermine2[guarda.nextMove];
				guarda.nextMove = (guarda.nextMove + 1) % (guarda.moves_pre2etermine2.length);
				int newX = Game.calculateNewX(comando, guarda.coordX);
				int newY = Game.calculateNewY(comando, guarda.coordY);
				this.updateEntity('G', guarda.coordX, guarda.coordY, newX, newY, level);
				guarda.moveEntity(comando);

			} else if (element instanceof Guard) { // mexe guarda random

//			} else if (element instanceof MassiveClub) {
//				board[level][element.coordX][element.coordY] = ' ';
//				entities.remove(element);
			} else if (element instanceof Ogre) { // mexe ogre random
				// movimento ogre
				Ogre shrek = (Ogre) element;
				//clean club
//				if(shrek.hasClub){
//					board[level][shrek.mclub.coordX][shrek.mclub.coordY] = ' ';
//				}
				
				
				int comando = shrek.generateMovement();
				while (!Game.checkMove(comando, shrek.coordX, shrek.coordY, this, level)) {
					comando = shrek.generateMovement();
				}
				int newX = Game.calculateNewX(comando, shrek.coordX);
				int newY = Game.calculateNewY(comando, shrek.coordY);
				this.updateEntity('O', shrek.coordX, shrek.coordY, newX, newY, level);
				shrek.moveEntity(comando);

				// shrek's club
//				shrek.hasClub = true; //a partir daqui tem sempre massive club
//				shrek.mclub.startAtPosition(newX, newY);
//				comando = shrek.generateMovement();
//				while (!Game.checkMove(comando, shrek.mclub.coordX, shrek.mclub.coordY, this, level)) {
//					comando = shrek.generateMovement();
//				}
//				newX = Game.calculateNewX(comando, shrek.mclub.coordX);
//				newY = Game.calculateNewY(comando, shrek.mclub.coordY);
//				board[level][shrek.mclub.coordX][shrek.mclub.coordY] = '*';
//				//this.updateEntity('*', shrek.mclub.coordX, shrek.mclub.coordY, newX, newY, level);
//				shrek.mclub.moveEntity(comando);
//				
//				//checks if it hit the player
//				if (this.hero.coordX == shrek.mclub.coordX) {
//					if (Math.abs(this.hero.coordY - shrek.mclub.coordY) == 1)
//						lost = true;
//				} else if (this.hero.coordY == shrek.mclub.coordY) {
//					if (Math.abs(this.hero.coordX - shrek.mclub.coordX) == 1)
//						lost = true;
//				}
				
			} // mais tipos de adversarios basta acomodar este if e a funcao de
				
		}

		return false;

	}
}
