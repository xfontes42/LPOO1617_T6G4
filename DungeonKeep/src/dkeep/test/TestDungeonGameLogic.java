package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.*;


public class TestDungeonGameLogic {
	
	char[][] testMap = {{'X','X','I','I','X'},
						{'X','H',' ','k','X'},
						{'X',' ',' ',' ','X'},
						{'X','G',' ',' ','X'},
						{'X','X','I','I','X'}};
	
	@Test
	public void testMoveHeroToFreeCell(){
		State game = new State(testMap);
		game.startEntities();
		
		//tests correct starting position (1,1)
		assertEquals(1, game.hero.getX());
		assertEquals(1, game.hero.getY());
		
		game.hero.moveEntity(2);//moves down
		
		game.updateEntity(game.hero.sprite, 1, 1, game.hero.getX(), game.hero.getX());
		assertEquals(1, game.hero.getX());
		assertEquals(2, game.hero.getY());
	}
	
//	@Test
//	public void testMoveHeroToWall(){
//		
//		
//	}
//	
//	@Test
//	public void testHeroCapturedByGuard(){
//		
//		
//	}
//	
//	@Test
//	public void testHeroRunsIntoClosedDoor(){
//		
//		
//	}
//	
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
