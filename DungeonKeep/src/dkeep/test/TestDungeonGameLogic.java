package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;


public class TestDungeonGameLogic {
	
	char[][] testMap = {{'X','X','I','I','X'},
						{'X','H',' ','k','X'},
						{'X',' ',' ',' ','X'},
						{'X','G',' ',' ','X'},
						{'X','X','X','X','X'}};
	
	@Test
	public void testMoveHeroToFreeCell(){
		State game = new State(testMap);
		game.startEntities();
		
		//tests correct starting position (1,1)
		assertEquals(1, game.hero.getX());
		assertEquals(1, game.hero.getY());
		
		//tries moving down
		if (game.checkMove(2, game.hero.getX(), game.hero.getY())){
			game.hero.moveEntity(2);
		}		
		
		game.updateEntity(game.hero.sprite, 1, 1, game.hero.getX(), game.hero.getX());
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
	}
	
	@Test
	public void testMoveHeroToWall(){
		State game = new State(testMap);
		game.startEntities();
		
		//tests correct starting position (1,1)
		assertEquals(1, game.hero.getX());
		assertEquals(1, game.hero.getY());
		
		//tries moving up
		if (game.checkMove(1, game.hero.getX(), game.hero.getY())){
			game.hero.moveEntity(1);
		}
		
		game.updateEntity(game.hero.sprite, 1, 1, game.hero.getX(), game.hero.getX());
		assertEquals(1, game.hero.getX());
		assertEquals(1, game.hero.getY());		
	}
	
	@Test
	public void testHeroCapturedByGuard(){
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
	public void testHeroRunsIntoClosedDoor(){
		
		State game = new State(testMap);
		game.startEntities();
		
		//tests correct starting position (1,1)
		assertEquals(1, game.hero.getX());
		assertEquals(1, game.hero.getY());
		
		//tries moving down
		if (game.checkMove(2, game.hero.getX(), game.hero.getY())){
			game.hero.moveEntity(2);
		}		
		
		game.updateEntity(game.hero.sprite, 1, 1, game.hero.getX(), game.hero.getX());
		
		//tries going into the door
		if (game.checkMove(3, game.hero.getX(), game.hero.getY())){
			game.hero.moveEntity(3);
		}	
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
	}
	
//	@Test
//	public void testHeroFlipsLever(){
//		
//		
//	}
//	
//	@Test
//	public void testHeroLeavesDungeon(){
//		
//		
//	}

}
