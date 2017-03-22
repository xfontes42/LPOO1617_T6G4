package dkeep.test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;
import dkeep.logic.*;

public class TestOtherComponents {
	
	
		@Test(expected = ArrayIndexOutOfBoundsException.class)
		public void testGameLevels(){
			GameLevels jogos = new GameLevels();
			//assertEquals(2, jogos.getNumberOfLevels());
			char[][] lvl1 =  { { 'X', 'X', 'X', 'X', 'X', 'I', 'I', 'X', 'X', 'X' },
					{ 'X', 'H', 'X', ' ', 'X', ' ', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'I', 'X', ' ', ' ', 'X', 'I', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'I', 'X', 'I', 'X', ' ', ' ', 'X', 'I', 'X' },
					{ 'X', ' ', 'X', ' ', 'X', ' ', ' ', 'X', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', ' ', ' ', 'X', 'X', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', 'k', 'X' }, { 'X', 'G', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
			char [][] lvl2 = { { 'X', 'I', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'H', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'k', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
			
			for(int i = 0; i < 0 ; i++){
				assertArrayEquals(lvl1[i], jogos.getLevel(1)[i]);
			}
			
			for(int i = 0; i < 0; i++){
				assertArrayEquals(lvl2[i], jogos.getLevel(2)[i]);
			}
			
			jogos.getLevel(jogos.getNumberOfLevels()+1);	
		}
		
		
		
		@Test
		public void testPrintClubOgre(){
			char [][] map = { { 'X', 'I', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'H', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'O', '*', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'k', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
			
			
			State now = new State(map);
			assertTrue(now.adjacent(1, 2, 1, 3));
			assertFalse(now.adjacent(2, 3, 4, 3));
			assertTrue(now.adjacent(6, 3, 5, 3));
			now.startEntities();
			now.printClub(5, 5);
			assertEquals('*',now.board[5][5]);
			
		}
		
		@Test
		public void testGuards(){
			char [][] map =  { { 'X', 'X', 'X', 'X', 'X', 'I', 'I', 'X', 'X', 'X' },
					{ 'X', 'H', 'X', ' ', 'X', ' ', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'I', 'X', ' ', ' ', 'X', 'I', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'I', 'X', 'I', 'X', ' ', ' ', 'X', 'I', 'X' },
					{ 'X', ' ', 'X', ' ', 'X', ' ', ' ', 'X', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', ' ', ' ', 'X', 'X', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', 'k', 'X' }, { 'X', 'G', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
			
			
			State now = new State(map);
			now.startEntities(1,1);  // 1 drunken 1 ogre
			now.updateBoard(false);
			Vector<Entity> entidades = (Vector<Entity>) now.entities.clone();
			assertTrue((entidades.get(0) instanceof Guard));
			Guard testeRookie = new Guard(0);
			assertTrue(testeRookie.behavior instanceof BehaviorRookie);
			testeRookie.movement();
			
			Guard testeDrunken = new Guard(1);
			assertTrue(testeDrunken.behavior instanceof BehaviorDrunken);
			while(testeDrunken.movement() != 5);
			
			Guard testeSuspicious = new Guard(2);
			assertTrue(testeSuspicious.behavior instanceof BehaviorSuspicious);
			testeSuspicious.movement();
		}
		
		@Test
		public void testOgre(){
			char [][] map = { { 'X', 'I', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'H', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'O', '*', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'k', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
			
			
			State now = new State(map);
			now.startEntities(1,2);  // 1 drunken 2 ogre
			now.updateBoard(false);
			now.printBoard();

			for(int i = 0; i < now.entities.size(); i++){
				System.out.println(now.entities.get(i).getSprite());
				System.out.println(now.entities.get(i).getClass());
			}
			
		}

		
		@Test
		public void testGameCommands(){
		}

}