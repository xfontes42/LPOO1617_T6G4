package dkeep.logic;

import java.io.Serializable;

/**
 * A level element.
 * 
 * Entities can be humanoid characters (the hero, the guards and ogres) or level elements (the keys and the doors).
 * Each entity has a sprite (represented by a char) and a set of coordinates.
 */
public class Entity implements Serializable{
	private int coordX, coordY;
	public char sprite = '?';

	public Entity() {

	}

	/**
	 * Updates an entity's coordinates based on the direction they are heading.
	 * 
	 * @param direction the direction the entity is heading
	 */
	public void moveEntity(int direction) {
		switch (direction) {
		case 1:
			coordY--;
			break;
		case 2:
			coordY++;
			break;
		case 3:
			coordX--;
			break;
		case 4:
			coordX++;
			break;
		case 5:
			break;
		}
	}

	/**
	 * Creates an entity with a certain sprite with specific coordinates.
	 * 
	 * @param x the entity's x-coordinate
	 * @param y the entity's y-coordinate
	 * @param spr the entity's sprite
	 */
	public Entity(int x, int y, char spr) {
		coordX = x;
		coordY = y;
		sprite = spr;
	}

	/**
	 * Starts an entity in a specific set of coordinates.
	 * 
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 */
	public void startAtPosition(int x, int y) {
		coordX = x;
		coordY = y;
	}

	/**
	 * Replaces an entity's x-coordinate.
	 * 
	 * @param x the new x-coordinate
	 */
	public void setX(int x) {
		coordX = x;
	}

	/**
	 * Replaces an entity's y-coordinate.
	 * 
	 * @param y the new y-coordinate
	 */
	public void setY(int y) {
		coordY = y;
	}

	/**
	 * Replaces an entity's sprite.
	 * 
	 * @param spr the new sprite
	 */
	public void setSprite(char spr) {
		sprite = spr;
	}

	/**
	 * Retrieves an entity's x-coordinate.
	 * 
	 * @return the entity's x-coordinate
	 */
	public int getX() {
		return coordX;
	}

	/**
	 * Retrieves an entity's y-coordinate.
	 * 
	 * @return the entity's y-coordinate
	 */
	public int getY() {
		return coordY;
	}

	/**
	 * Retrieves an entity's sprite.
	 * 
	 * @return the entity's sprite
	 */
	public char getSprite() {
		return sprite;
	}

	/**
	 * Gets a default movement direction.
	 * 
	 * @return 5 by default (= stays in place)
	 */
	public int movement() {
		return 5;
	}

}
