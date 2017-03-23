package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;

public class TestKeepGameLogic {
	char[][] testMap = {{'X','X','I','I','X'},
						{'X','H',' ','k','X'},
						{'X',' ',' ',' ','X'},
						{'X','O',' ',' ','X'},
						{'X','X','X','X','X'}};
	
	
	@Test
	public void testHeroCapturedByOgre(){
		State game = new State(testMap);
		game.startEntities();
		//tries moving right (where the guard can catch them)
		if (game.checkMove(4, game.hero.getX(), game.hero.getY())){
			game.hero.moveEntity(4);
		}
		
		game.updateEntity(game.hero.sprite, 1, 1, game.hero.getX(), game.hero.getX());
		assertEquals(2, game.hero.getX());
		assertEquals(1, game.hero.getY());
		assertTrue(game.checkIfLose());
		
		
	}
	
	@Test
	public void testHeroChangesToK(){
		State game = new State(testMap);
		game.startEntities();
		game.entities.clear();
		
		//tests correct starting position (1,1)
		assertEquals(1, game.hero.getX());
		assertEquals(1, game.hero.getY());
		
		//tries moving down twice
		if (game.checkMove(2, game.hero.getX(), game.hero.getY())) {
			game.hero.moveEntity(2);
			game.updateEntity(game.hero.sprite, 1, 1, game.hero.getX(), game.hero.getX());
			if (game.checkMove(2, game.hero.getX(), game.hero.getY())){
				game.hero.moveEntity(2);
				game.updateEntity(game.hero.sprite, 1, 2, game.hero.getX(), game.hero.getX());
			}
		}

		assertEquals(1, game.hero.getX());
		assertEquals(3, game.hero.getY());
		assertTrue(game.checkForKey(game.hero.getX(), game.hero.getY()));
		game.updateBoard(false);
		assertTrue(game.board[game.hero.getX()][game.hero.getY()] == 'K');
	}
	
	
	@Test
	public void testHeroFailsToOpenDoor(){
		State game = new State(testMap);
		game.startEntities();
		game.entities.clear();
		
		//tests correct starting position (1,1)
		assertEquals(1, game.hero.getX());
		assertEquals(1, game.hero.getY());
		
		//tries moving down twice
		if (game.checkMove(2, game.hero.getX(), game.hero.getY())) {
			game.hero.moveEntity(2);
			game.updateEntity(game.hero.sprite, 1, 1, game.hero.getX(), game.hero.getX());
			
		}
		assertFalse(game.checkMove(3, game.hero.getX(), game.hero.getY()));
	}
	
	@Test
	public void testHeroChangesDoor(){
		State game = new State(testMap);
		game.startEntities();
		game.entities.clear();
		
		//tests correct starting position (1,1)
		assertEquals(1, game.hero.getX());
		assertEquals(1, game.hero.getY());
		
		//tries moving down twice
		if (game.checkMove(2, game.hero.getX(), game.hero.getY())) {
			game.hero.moveEntity(2);
			game.updateEntity(game.hero.sprite, 1, 1, game.hero.getX(), game.hero.getX());
			if (game.checkMove(2, game.hero.getX(), game.hero.getY())){
				game.hero.moveEntity(2);
				game.updateEntity(game.hero.sprite, 1, 2, game.hero.getX(), game.hero.getX());
			}
		}

		assertEquals(1, game.hero.getX());
		assertEquals(3, game.hero.getY());
		assertTrue(game.checkForKey(game.hero.getX(), game.hero.getY()));
		game.updateBoard(false);
		//opens door
		assertTrue(game.board[game.hero.getX()-1][game.hero.getY()] == 'S');
	}
	
	@Test
	public void testHeroWinsGame(){
		State game = new State(testMap);
		game.startEntities();
		game.entities.clear();
		
		//tests correct starting position (1,1)
		assertEquals(1, game.hero.getX());
		assertEquals(1, game.hero.getY());
		
		//tries moving down twice
		if (game.checkMove(2, game.hero.getX(), game.hero.getY())) {
			game.hero.moveEntity(2);
			game.updateEntity(game.hero.sprite, 1, 1, game.hero.getX(), game.hero.getX());
			if (game.checkMove(2, game.hero.getX(), game.hero.getY())){
				game.hero.moveEntity(2);
				game.updateEntity(game.hero.sprite, 1, 2, game.hero.getX(), game.hero.getX());
			}
		}

		assertEquals(1, game.hero.getX());
		assertEquals(3, game.hero.getY());
		assertTrue(game.checkForKey(game.hero.getX(), game.hero.getY()));
		game.updateBoard(false);
		//opens door
		assertTrue(game.board[game.hero.getX()-1][game.hero.getY()] == 'S');
		
		//tries going to door
		if (game.checkMove(3, game.hero.getX(), game.hero.getY())) {
			int newX, newY;
			newX = game.calculateNewX(3, game.hero.getX());
			newY = game.calculateNewY(3, game.hero.getY());
			assertTrue(game.checkIfWin(newX, newY));
		}
		
	}
	
	@Test
	public void testStunOgre(){
		State game = new State(testMap);
		game.startEntities();
		//game.entities.clear();
		
		assertEquals(1, game.hero.getX());
		assertEquals(1, game.hero.getY());
		
		game.hero.moveEntity(4);
		game.updateEntity(game.hero.sprite, 1, 1, game.hero.getX(), game.hero.getX());
		
		assertTrue(game.entities.get(0) instanceof Ogre);
		assertTrue(((Ogre)game.entities.get(0)).stunnedForNTurns > 0);
	}
	
}
