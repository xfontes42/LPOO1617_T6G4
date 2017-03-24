package dkeep.logic;

import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

import dkeep.logic.Door;
import dkeep.logic.Entity;
import dkeep.logic.Guard.GuardType;
import dkeep.logic.Hero;
import dkeep.logic.Key;

public class State implements Serializable {
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

	/**
	 * @brief Creates a game.
	 */
	public State(){
		
	}
	
	/**
	 * @brief Creates a game.
	 * @see startEntities
	 * @param nivel the game level to be started.
	 */
	public State(char[][] nivel) {
		board = nivel.clone();
		startEntities();
	}

	/**
	 * @brief Updates on the board an entity's location
	 * @param entity the entity's character representation
	 * @param oldX the old x coordinate
	 * @param oldY the old y coordinate
	 * @param newX the new x coordinate
	 * @param newY the new y coordinate
	 */
	public void updateEntity(char entity, int oldX, int oldY, int newX, int newY) {
		board[oldX][oldY] = ' ';
		board[newX][newY] = entity;

	}

	/**
	 * @brief Places on the board an ogre's massive club.
	 * @param newX the club's new x coordinate
	 * @param newY the club's new y coordinate.
	 */
	public void printClub(int newX, int newY) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++)
				if (board[i][j] == '*')
					board[i][j] = ' ';
		}
		board[newX][newY] = '*';
	}

	/**
	 * @brief Checks if two matrix cells are adjacent
	 * @param x1 the first cell's x coordinate
	 * @param y1 the first cell's y coordinate
	 * @param x2 the second cell's x coordinate
	 * @param y2 the second cell's y coordinate
	 * @return true if the cells are adjacent, false if otherwise
	 */
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
	 * @brief Creates the level's guards.
	 * @param guards the guard personality (0 for rookie, 1 for drunken, 2 for suspicious)
	 * @param i the guard's starting column
	 * @param j the guard's starting row
	 */
	public void startGuards(int guards, int i, int j){
		Guard guard = new Guard(guards);
		guard.startAtPosition(i, j);
		entities.add(guard);
		lever = true;
	}
	
	/**
	 * @brief Creates the level's ogres.
	 * @param ogres the number of ogres
	 * @param i the ogres' starting column
	 * @param j the ogres' starting row
	 */
	public void startOgres(int ogres, int i, int j){
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
	
	 /**
	  * @brief Creates a door in the level.
	  * @param i the door row
	  * @param j the door column
	  */
	public void startDoor(int i, int j){
		Door door = new Door();
		door.startAtPosition(i, j);
		doors.add(door);
	}

	/**
	 * @brief Starts the game entities existent in the game.
	 * @param guards type of guard
	 * @param ogres number of ogres
	 */
	public void startEntities(int guards, int ogres) {
		entities.clear();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				switch (board[i][j]) {
				case 'H':
					hero.startAtPosition(i, j);	break;
				case 'G':
					startGuards(guards, i, j); break;
				case 'O':
					startOgres(ogres, i, j); break;
				case 'k':
					key.startAtPosition(i, j); break;
				case 'I':
					startDoor(i, j); break;
				}
			}
		}
	}

	/**
	 * @brief Starts the game's entities with random values.
	 * @see startEntities(int, int)
	 */
	public void startEntities() {
		Random rand = new Random();
		startEntities(rand.nextInt(3), rand.nextInt(MAX_OGRES) + 1);
	}

	/**
	 * @brief Checks if the hero stepped on the key cell.
	 */
	public void checkForKey(){
		if (key.getX() == hero.getX() && key.getY() == hero.getY()) {
			board[key.getX()][key.getY()] = 'K';
			key.openDoors(hero);
		} else {
			board[key.getX()][key.getY()] = 'k';
		}
		
	}
	
	/**
	 * @brief Opens the level's dungeons.
	 */
	public void openDoors() {
		if (lever) {
			if (hero.hasKey) {
				for (int i = 0; i < board[0].length; i++) {
					if (board[0][i] == 'I')
						board[0][i] = 'S';
				}
			}
		} else {
			if (hero.hasKey && hero.getX() == 1) {
				for (int i = 0; i < board[0].length; i++) {
					if (board[0][i] == 'I' && hero.getY() == i)
						board[0][i] = 'S';
				}
			}
		}
	}
	
	/**
	 * @brief Checks if the hero exited the room.
	 * @return
	 */
	public boolean checkForExit(){
		for (int i = 0; i < doors.size(); i++) {
			if (hero.getX() == doors.get(i).getX() && hero.getY() == doors.get(i).getY())
				return true;
		}
		
		return false;
	}
	
	/**
	 * @brief Moves the board's entities.
	 */
	public void moveEntities(){
		for (int index_entities = 0; index_entities < entities.size(); index_entities++) {
			Entity element = entities.get(index_entities);
			
			if ((element instanceof Guard))
				moveGuard(element);
			
			else if (element instanceof Ogre)
				moveOgre(element);

		}
	}
	
	/**
	 * @brief Moves an element that's an instance of Guard.
	 * @param element the guard
	 */
	public void moveGuard(Entity element){
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
	}
	
	/**
	 * @brief Removes from the board an ogre's clubs (since their movement is not always linear)
	 * @param shrek the ogre
	 */
	public void cleanUpClubs(Ogre shrek){
		board[shrek.mclub.getX()][shrek.mclub.getY()] = ' ';
		for (int i = 0; i < allClubs.size(); i++)
			if (shrek.mclub == allClubs.get(i)) { 
				allClubs.removeElementAt(i);
				break;
			}
	}
	
	/**
	 * @brief Swings an ogre's club.
	 * @param shrek the ogre
	 */
	public void moveClub(Ogre shrek){
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
	
	/**
	 * @brief Performs the stunned ogre routine (does not move, swings club)
	 * @param shrek the stunned ogre
	 */
	public void stunnedOgreRoutine(Ogre shrek){
		shrek.sprite = '8';
		shrek.stunnedForNTurns--;
		this.updateEntity(shrek.sprite, shrek.getX(), shrek.getY(), shrek.getX(), shrek.getY());
		moveClub(shrek);
	}
	
	/**
	 * @brief Moves a non-stunned ogre
	 * @param shrek the ogre
	 */
	public void movingOgreRoutine(Ogre shrek){
		int comando = shrek.generateMovement();
		while (!checkMove(comando, shrek.getX(), shrek.getY())) {
			comando = shrek.generateMovement();
		}
		int newX = calculateNewX(comando, shrek.getX());
		int newY = calculateNewY(comando, shrek.getY());
		this.updateEntity('O', shrek.getX(), shrek.getY(), newX, newY);
		shrek.moveEntity(comando);
	}
	
	/**
	 * @brief Moves an element that's an instance of Ogre.
	 * @param element the guard
	 */
	public void moveOgre(Entity element){
		Ogre shrek = (Ogre) element;
		
		if (shrek.stunnedForNTurns != 0) {	//checks if ogre is stunned
			stunnedOgreRoutine(shrek);
			cleanUpClubs(shrek);
			moveClub(shrek);
		} else {
			shrek.sprite = 'O';
			if (!shrek.hasClub) {
				movingOgreRoutine(shrek);
				shrek.mclub = new MassiveClub(); // gives the ogre a club
				moveClub(shrek);
			} else {
				cleanUpClubs(shrek);
				movingOgreRoutine(shrek);
				moveClub(shrek);
			}
			shrek.hasClub = true;
		}
	}

	/**
	 * @brief Updates the board.
	 * @param lost given to know if the player has lost the game
	 * @return true if the player has won, false if the game is still ongoing
	 */
	public boolean updateBoard(Boolean lost) {
		checkForKey();
		openDoors();
		
		if (checkForExit())
			return true;

		if (checkIfLose()) {
			lost = true;
			return false;
		}
		
		moveEntities();
		return false;
	}

	//TODO isto nao devia estar no cli?
	/**
	 * @brief Prints the game board.
	 */
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print((char) board[j][i] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * @brief Checks if the move is possible
	 * @param movement the direction
	 * @param x the entity's column
	 * @param y the entity's row
	 * @return true if possible, false otherwise
	 */
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

	/**
	 * @brief Checks if the player is adjacent to an ogre.
	 * @param shrek the ogre being tested
	 * @see checkIfLose
	 */
	public void adjacentToOgre(Ogre shrek){
		boolean stunned = (shrek.stunnedForNTurns != 0);
		if (!stunned)
			if (adjacent(hero.getX(), hero.getY(), shrek.getX(), shrek.getY()))
				stunOgre(shrek);
	}
	
	/**
	 * @brief Checks if the player is adjacent to a guard
	 * @param guard the guard being tested
	 * @see checkIfLose
	 */
	public boolean adjacentToGuard(Guard guard){
		if (adjacent(hero.getX(), hero.getY(), guard.getX(), guard.getY())){
			if((guard.behavior instanceof BehaviorDrunken)){
				if(((BehaviorDrunken)guard.behavior).sleeping)
					return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * @brief Checks if the player lost (i.e. is next to an attacking entity)
	 * @return true if they lost, false if otherwise
	 */
	public boolean checkIfLose() {
		boolean result = false;
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Ogre && hero.hasClub)
				adjacentToOgre((Ogre) entities.get(i));

			else if (entities.get(i) instanceof Guard) {
				if (adjacentToGuard((Guard) entities.get(i)))
					result = true;
			} else if (adjacent(hero.getX(), hero.getY(), entities.get(i).getX(), entities.get(i).getY()))
				result = true;

		}

		if (adjacentToClub())
			result = true;

		return result;
	}

	/**
	 * @brief Stuns an ogre.
	 * @param ogre the ogre being stunned
	 * @return true if the ogre was stunned, false if otherwise
	 */
	public boolean stunOgre(Ogre ogre) {
		if (adjacent(hero.getX(), hero.getY(), ogre.getX(), ogre.getY()) && hero.hasClub) {
			ogre.stunnedForNTurns = 2;
			ogre.sprite = '8';
			return true;
		} else
			return false;
	}

	/**
	 * @brief Checks if the player is adjacent to any ogre's clubs.
	 * @return true if the player is adjacent to a club, false if otherwise.
	 */
	public boolean adjacentToClub() {
		boolean result = false;

		int x = hero.getX();
		int y = hero.getY();
		if (board[x - 1][y] == '*' || board[x + 1][y] == '*' || board[x][y - 1] == '*' || board[x][y + 1] == '*')
			result = true;

		return result;
	}

	/**
	 * @brief Checks if the player is about to exit a door.
	 * @param newX the hero's new column
	 * @param newY the hero's new line
	 * @return
	 */
	public boolean checkIfWin(int newX, int newY) {
		if (board[newX][newY] == 'S')
			return true;
		else
			return false;
	}

	/**
	 * @brief Checks if the player is about to get a key.
	 * @param newX the player's new column
	 * @param newY the player's new line
	 * @return true if the player got a key, false if otherwise.
	 */
	public boolean checkForKey(int newX, int newY) {
		if (board[newX][newY] == 'k') {
			key.openDoors(hero);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @brief Calculates the player's new column based on the direction they're heading.
	 * @param movement the player's direction (1 is up, 2 is down, 3 is left and 4 is right)
	 * @param x the player's current column
	 * @return the new column
	 */
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

	/**
	 * @brief Calculates the player's new line based on the direction they're heading.
	 * @param movement the player's direction (1 is up, 2 is down, 3 is left and 4 is right)
	 * @param x the player's current line
	 * @return the new line
	 */
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
