package dkeep.logic;

import java.util.Random;
import java.util.Vector;

import dkeep.logic.Door;
import dkeep.logic.Entity;
import dkeep.logic.Guard.GuardType;
import dkeep.logic.Hero;
import dkeep.logic.Key;

public class State {
	private int MAX_OGRES = 1/* 5 */;
	public boolean won = false;
	public boolean lever = false;

	public char[][] board = new char[10][10];
	public Vector<Entity> entities = new Vector<Entity>();

	// checkng out this solution
	public Vector<MassiveClub> allClubs = new Vector<MassiveClub>();

	public Vector<Door> doors = new Vector<Door>();
	public Hero hero = new Hero();
	public Key key = new Key();

	public State(char[][] nivel) {
		board = nivel.clone();
		startEntities();
	}

	public void updateEntity(char entity, int oldX, int oldY, int newX, int newY) {
		board[oldX][oldY] = ' ';
		board[newX][newY] = entity;
		// board[oldX][oldY] = ' ';
	}

	public void printClub(int newX, int newY) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++)
				if (board[i][j] == '*')
					board[i][j] = ' ';
		}
		board[newX][newY] = '*';
	}

	// TODO - use this function on the other functions that need to check this
	// situation
	public boolean adjacent(int x1, int y1, int x2, int y2) {
		boolean result = false;
		if (x1 == x2) {
			if (Math.abs(y1 - y2) == 1)
				result = true;
		}
		if (y1 == y2) {
			if (Math.abs(x1 - x2) == 1)
				result = true;
		}
		return result;

	}

	/**
	 * @brief Starts the game entities existent in the game.
	 * @param guards
	 *            number of guards
	 * @param ogres
	 *            number of ogres
	 */
	public void startEntities(int guards, int ogres) {
		entities.clear();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 'H') {
					hero.startAtPosition(i, j);
				}

				else if (board[i][j] == 'G') {
					Guard guard = new Guard(guards);
					guard.startAtPosition(i, j);
					entities.add(guard);
					lever = true;
				}

				else if (board[i][j] == 'O') {
					hero.setSprite('A');
					hero.hasClub = true;
					int numberOfOgres = ogres;
					for (int in = 1; in <= numberOfOgres; in++) {
						Ogre ogre = new Ogre();
						ogre.startAtPosition(i, j);
						entities.add(ogre);
					}

					lever = false;

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

	public void startEntities() {
		Random rand = new Random();
		startEntities(rand.nextInt(3), rand.nextInt(MAX_OGRES) + 1);
	}

	public boolean updateBoard(Boolean lost) {

		if (key.getX() == hero.getX() && key.getY() == hero.getY()) {
			board[key.getX()][key.getY()] = 'K';
			key.openDoors(hero);
		} else {
			board[key.getX()][key.getY()] = 'k';
		}

		if (lever == true) {
			if (hero.hasKey) {
				for (int i = 0; i < board[0].length; i++) {
					if (board[0][i] == 'I')
						board[0][i] = 'S';
				}

				for (int i = 0; i < doors.size(); i++) {
					if (hero.getX() == doors.get(i).getX() && hero.getY() == doors.get(i).getY())
						return true;
				}

			}
		} else {
			if (hero.hasKey && hero.getX() == 1) {
				for (int i = 0; i < board[0].length; i++) {
					if (board[0][i] == 'I' && hero.getY() == i)
						board[0][i] = 'S';
				}
			}

			for (int i = 0; i < doors.size(); i++) {
				if (hero.getX() == doors.get(i).getX() && hero.getY() == doors.get(i).getY())
					return true;
			}

		}

		// check if player lost before moving the entities
		for (int indexEntities = 0; indexEntities < entities.size(); indexEntities++) {
			if (entities.get(indexEntities) instanceof Ogre)
				stunOgre((Ogre) entities.get(indexEntities));
		}

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

				if (guarda.type == GuardType.drunken && ((BehaviorDrunken) guarda.getBehavior()).sleeping)
					guarda.setSprite('g');
				else
					guarda.setSprite('G');

				this.updateEntity(guarda.getSprite(), guarda.getX(), guarda.getY(), newX, newY);

				guarda.moveEntity(comando);

			} else if (element instanceof Ogre) { // mexe ogre random
				// movimento ogre

				Ogre shrek = (Ogre) element;
				
				//se o ogre esta atordodado
				if (shrek.stunnedForNTurns != 0) {
					//ogre nao sai do sitio
					shrek.sprite = '8';
					shrek.stunnedForNTurns--;
					this.updateEntity(shrek.sprite, shrek.getX(), shrek.getY(), shrek.getX(), shrek.getY());
					
					//ogre continua a abanar o bastao
					board[shrek.mclub.getX()][shrek.mclub.getY()] = ' ';
					for (int i = 0; i < allClubs.size(); i++)
						if (shrek.mclub == allClubs.get(i)) { 
							allClubs.removeElementAt(i);
							break;
						}
					
					
					int comClub = shrek.generateMovement();
					while (!checkMove(comClub, shrek.getX(), shrek.getY())) {
						comClub = shrek.generateMovement();
					}
					int clubX = calculateNewX(comClub, shrek.getX());
					int clubY = calculateNewY(comClub, shrek.getY());
					shrek.mclub.startAtPosition(clubX, clubY);
					allClubs.addElement(shrek.mclub);
					board[clubX][clubY] = '*';
				} else {
					shrek.sprite = 'O';

					if (!shrek.hasClub) {
						// mexe ogre
						int comando = shrek.generateMovement();
						while (!checkMove(comando, shrek.getX(), shrek.getY())) {
							comando = shrek.generateMovement();
						}
						int newX = calculateNewX(comando, shrek.getX());
						int newY = calculateNewY(comando, shrek.getY());
						this.updateEntity('O', shrek.getX(), shrek.getY(), newX, newY);
						shrek.moveEntity(comando);
						//

						shrek.mclub = new MassiveClub();
						int comClub = shrek.generateMovement();
						while (!checkMove(comClub, shrek.getX(), shrek.getY())) {
							comClub = shrek.generateMovement();
						}
						int clubX = calculateNewX(comClub, shrek.getX());
						int clubY = calculateNewY(comClub, shrek.getY());
						shrek.mclub.startAtPosition(clubX, clubY);
						allClubs.addElement(shrek.mclub);
						board[clubX][clubY] = '*';
					} else {

						// apaga current club
						board[shrek.mclub.getX()][shrek.mclub.getY()] = ' ';
						for (int i = 0; i < allClubs.size(); i++)
							if (shrek.mclub == allClubs.get(i)) { // v� se
																	// s�o a
																	// mesma
																	// referencia
								allClubs.removeElementAt(i);
								break;
							}

						// mexe ogre
						int comando = shrek.generateMovement();
						while (!checkMove(comando, shrek.getX(), shrek.getY())) {
							comando = shrek.generateMovement();
						}
						int newX = calculateNewX(comando, shrek.getX());
						int newY = calculateNewY(comando, shrek.getY());
						this.updateEntity('O', shrek.getX(), shrek.getY(), newX, newY);
						shrek.moveEntity(comando);
						//

						int comClub = shrek.generateMovement();
						while (!checkMove(comClub, shrek.getX(), shrek.getY())) {
							comClub = shrek.generateMovement();
						}
						int clubX = calculateNewX(comClub, shrek.getX());
						int clubY = calculateNewY(comClub, shrek.getY());
						shrek.mclub.startAtPosition(clubX, clubY);
						allClubs.addElement(shrek.mclub);
						board[clubX][clubY] = '*';
					}
					shrek.hasClub = true;
				}
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

	public boolean checkIfLose() {
		boolean result = false;
		for (int i = 0; i < entities.size(); i++) {
			if (!(entities.get(i) instanceof Ogre)) {
				if (hero.getX() == entities.get(i).getX()) {
					if (Math.abs(hero.getY() - entities.get(i).getY()) == 1)
						result = true;
				} else if (hero.getY() == entities.get(i).getY()) {
					if (Math.abs(hero.getX() - entities.get(i).getX()) == 1)
						result = true;
				}
			} else {
				boolean stunned = (((Ogre) entities.get(i)).stunnedForNTurns != 0);
				if (hero.getX() == entities.get(i).getX() && !stunned) {
					if (Math.abs(hero.getY() - entities.get(i).getY()) == 1) {
						System.out.println("called stun ogre");
						stunOgre((Ogre) entities.get(i)); // result = true;
					}
				} else if (hero.getY() == entities.get(i).getY() && !stunned) {
					if (Math.abs(hero.getX() - entities.get(i).getX()) == 1) {
						System.out.println("called stun ogre");
						stunOgre((Ogre) entities.get(i)); // result = true;
					}
				}
			}
		}

		if (adjacentToClub())
			result = true;

		return result;
	}

	public boolean stunOgre(Ogre ogre) {
		System.out.println("stunned was called");
		System.out.println("hero has club = " + hero.hasClub);
		if (adjacent(hero.getX(), hero.getY(), ogre.getX(), ogre.getY()) && hero.hasClub) {
			ogre.stunnedForNTurns = 2;
			ogre.sprite = '8';
			System.out.println("adjacent");
			return true;
		} else
			return false;
	}

	public boolean adjacentToClub() {
		boolean result = false;

		int x = hero.getX();
		int y = hero.getY();
		if (board[x - 1][y] == '*' || board[x + 1][y] == '*' || board[x][y - 1] == '*' || board[x][y + 1] == '*')
			result = true;

		return result;
	}

	public boolean checkIfWin(int newX, int newY) {
		if (board[newX][newY] == 'S')
			return true;
		else
			return false;
	}

	public boolean checkForKey(int newX, int newY) {
		if (board[newX][newY] == 'k') {
			key.openDoors(hero);
			return true;
		} else {
			return false;
		}
	}

	public int calculateNewX(int movement, int x) {
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
