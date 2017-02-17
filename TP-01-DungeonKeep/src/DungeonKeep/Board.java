package DungeonKeep;

import java.util.Vector;

public class Board {
	public char[][][] board = {{{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
						 { 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' },
						 { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
						 { 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' }, 
						 { 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
						 { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						 { 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						 { 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' },
						 { 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
						 { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }},
			
			            {{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
						 { 'I', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'k', 'X' },
						 { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						 { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						 { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						 { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						 { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						 { 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						 { 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						 { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } } };

	public int level;
	public Vector<Entity> entidades_ingame;
	
	public Board(int level){
		this.level = level;
	}
	
	public void printBoard() {
		for (int i = 0; i < board[level].length; i++) {
			for (int j = 0; j < board[level][i].length; j++) {
				System.out.print((char) board[level][i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void updateEntity(char entity, int oldX, int oldY, int newX, int newY, int level){
		board[level][newX][newY] = entity;
		board[level][oldX][oldY] = ' ';
	}

}
