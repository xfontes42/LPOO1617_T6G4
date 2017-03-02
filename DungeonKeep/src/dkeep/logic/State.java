package dkeep.logic;

import java.util.Vector;


import dkeep.logic.Door;
import dkeep.logic.Entity;
import dkeep.logic.Hero;
import dkeep.logic.Key;

public class State {
	public boolean won = false;
	
	public char[][] board = new char[10][10];
	public Vector<Entity> entities = new Vector<Entity>();
	public Vector<Door> doors = new Vector<Door>();
	public Hero hero = new Hero();
	public Key key = new Key();
	
	public State(char[][] nivel){
		board = nivel.clone();
		startEntities();
	}
	
	public void updateEntity(char entity, int oldX, int oldY, int newX, int newY) {
		board[oldX][oldY] = ' ';
		board[newX][newY] = entity;
		//board[oldX][oldY] = ' ';
	}
	
	public void printClub(int newX, int newY) {
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++)
				if (board[i][j] == '*')
					board[i][j] = ' ';
		}
		board[newX][newY] = '*';
	}

	public void startEntities() {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 'H') {
					hero.startAtPosition(i, j);
				}

				else if (board[i][j] == 'G') {
					Guard guard = new Guard();
					guard.startAtPosition(i, j);
					entities.add(guard);
				}

				else if (board[i][j] == 'O') {
					Ogre ogre = new Ogre();
					ogre.startAtPosition(i, j);
					entities.add(ogre);
				}

				else if (board[i][j] == 'k') {
					key.startAtPosition(i, j);

				}

				else if (board[i][j] == 'I') {
					Door door = new Door();
					door.startAtPosition(i, j);
					doors.add(door);
				}
			}
		}
	}

	public boolean updateBoard(Boolean lost) {

		if (key.getX() == hero.getX() && key.getY() == hero.getY()) {
			board[key.getX()][key.getY()] = 'K';
			key.openDoors(hero);
		} else {
			board[key.getX()][key.getY()] = 'k';
		}

		// update das keys --> assume que a porta est� na parede da esquerda
		if (hero.hasKey) {
			for (int i = 0; i < board[0].length; i++) {
				if (board[0][i] == 'I')
					board[0][i] = 'S';
			}

			// for (int index1 = 0; index1 < board[level].length; index1++) {
			// for (int index2 = 0; index2 < board[level][index1].length;
			// index2++) {
			// if (board[level][index1][index2] == 'I' && index1 == 0)
			// board[level][index1][index2] = 'S';
			// }
			// }

			for (int i = 0; i < doors.size(); i++) {
				if (hero.getX() == doors.get(i).getX() && hero.getY() == doors.get(i).getY())
					return true;
			}

		}

		// check if player lost before moving the entities
		if (checkIfLose()) {
			lost = true;
			return false;
		}

		// moving entities

		for (int index_entities = 0; index_entities < entities.size(); index_entities++) {
			Entity element = entities.get(index_entities);
			if ((element instanceof Guard)) { // mexe guarda
															// caminho
															// predefinido
				Guard guarda = (Guard) element;
				int comando = guarda.movement();
				int newX = calculateNewX(comando, guarda.getX());
				int newY = calculateNewY(comando, guarda.getY());
				this.updateEntity('G', guarda.getX(), guarda.getY(), newX, newY);
				guarda.moveEntity(comando);

			} else if (element instanceof Guard) { // mexe guarda random

//			} else if (element instanceof MassiveClub) {
//				board[level][element.getX()][element.getY()] = ' ';
//				entities.remove(element);
			} else if (element instanceof Ogre) { // mexe ogre random
				// movimento ogre
				Ogre shrek = (Ogre) element;
				//clean club
//				if(shrek.hasClub){
//					board[level][shrek.mclub.getX()][shrek.mclub.getY()] = ' ';
//				}
				
				
				int comando = shrek.generateMovement();
				while (!checkMove(comando, shrek.getX(), shrek.getY())) {
					comando = shrek.generateMovement();
				}
				int newX = calculateNewX(comando, shrek.getX());
				int newY = calculateNewY(comando, shrek.getY());
				this.updateEntity('O', shrek.getX(), shrek.getY(), newX, newY);
				shrek.moveEntity(comando);
				shrek.hasClub = true;
				
				if (shrek.hasClub){
					shrek.mclub = new MassiveClub();
					int comClub = shrek.generateMovement();
					while (!checkMove(comClub, shrek.getX(), shrek.getY())){
						comClub = shrek.generateMovement();
					}
					int clubX = calculateNewX(comClub, shrek.getX());
					int clubY = calculateNewY(comClub, shrek.getY());
					shrek.mclub.startAtPosition(clubX, clubY);
					printClub(clubX, clubY);
					
				}

				// shrek's club
//				shrek.hasClub = true; //a partir daqui tem sempre massive club
//				shrek.mclub.startAtPosition(newX, newY);
//				comando = shrek.generateMovement();
//				while (!Game.checkMove(comando, shrek.mclub.getX(), shrek.mclub.getY(), this, level)) {
//					comando = shrek.generateMovement();
//				}
//				newX = Game.calculateNewX(comando, shrek.mclub.getX());
//				newY = Game.calculateNewY(comando, shrek.mclub.getY());
//				board[level][shrek.mclub.getX()][shrek.mclub.getY()] = '*';
//				//this.updateEntity('*', shrek.mclub.getX(), shrek.mclub.getY(), newX, newY, level);
//				shrek.mclub.moveEntity(comando);
//				
//				//checks if it hit the player
//				if (this.hero.getX() == shrek.mclub.getX()) {
//					if (Math.abs(this.hero.getY() - shrek.mclub.getY()) == 1)
//						lost = true;
//				} else if (this.hero.getY() == shrek.mclub.getY()) {
//					if (Math.abs(this.hero.getX() - shrek.mclub.getX()) == 1)
//						lost = true;
//				}
				
			} // mais tipos de adversarios basta acomodar este if e a funcao de
				
		}

		return false;

	}
	
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print((char) board[j][i] + " ");
			}
			System.out.println();
		}
	}
	
	public boolean checkMove(int movement, int x, int y) {
		boolean result = true;

		switch (movement) {
		case 1: // cima
			if (board[x][y - 1] == 'X' || board[x][y - 1] == 'I')
				result = false;
			break;
		case 2: // baixo
			if (board[x][y + 1] == 'X' || board[x][y + 1] == 'I')
				result = false;
			break;
		case 3: // esq
			if (board[x - 1][y] == 'X' || board[x - 1][y] == 'I')
				result = false;
			break;
		case 4: // dir
			if (board[x + 1][y] == 'X' || board[x + 1][y] == 'I')
				result = false;
			break;
		case 5:
			break;
		default:
			result = false;
			break;
		}

		return result;
	}
	
	public  boolean checkIfLose() {
		boolean result = false;
		for (int i = 0; i < entities.size(); i++) {
			if (hero.getX() == entities.get(i).getX()) {
				if (Math.abs(hero.getY() - entities.get(i).getY()) == 1)
					result = true;
			} else if (hero.getY() == entities.get(i).getY()) {
				if (Math.abs(hero.getX() - entities.get(i).getX()) == 1)
					result = true;
			}

		}
		
		if (adjacentToClub())
			result = true;

		return result;
	}
	
	public  boolean adjacentToClub() {
		boolean result = false;

		int x = hero.getX();
		int y = hero.getY();
		if (board[x - 1][y] == '*' || board[x + 1][y] == '*'
				|| board[x][y - 1] == '*' || board[x][y + 1] == '*')
			result = true;

		return result;
	}
	
	public boolean checkIfWin(int newX, int newY){
		if (board[newX][newY] == 'S')
			return true;
		else
			return false;
	}
	
	public boolean checkForKey(int newX, int newY){
		if (board[newX][newY] == 'k') {
			key.openDoors(hero);
			return true;
		} else {
			return false;
		}
	}

	public  int calculateNewX(int movement, int x) {
		int result;
		switch (movement) {
		case 1: // cima
			result = x;
			break;
		case 2: // baixo
			result = x;
			break;
		case 3: // esq
			result = x - 1;
			break;
		case 4: // dir
			result = x + 1;
			break;
		default:
			result = x;
			break;
		}

		return result;
	}

	public int calculateNewY(int movement, int y) {
		int result;
		switch (movement) {
		case 1: // cima
			result = y - 1;
			break;
		case 2: // baixo
			result = y + 1;
			break;
		case 3: // esq
			result = y;
			break;
		case 4: // dir
			result = y;
			break;
		default:
			result = y;
			break;
		}

		return result;
	}
}
