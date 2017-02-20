package DungeonKeep;

import java.util.Scanner;
import java.lang.Math;
import java.util.Vector;


public class Game {

	public boolean won = false;
	
	public int receiveCommand(Scanner scan) {
		System.out.print("Insert direction (u,d,l,r): ");
		String movement = scan.next();
		int direction;

		switch (movement) {
		case "u":
			direction = 1;
			break;
		case "d":
			direction = 2;
			break;
		case "l":
			direction = 3;
			break;
		case "r":
			direction = 4;
			break;
		default:
			direction = 0;
			break;
		}

		return direction;
	}

	public static boolean checkMove(int movement, int x, int y, Board b, int level) {
		boolean result = true;

		switch (movement) {
		case 1: // cima
			if (b.board[level][x][y - 1] == 'X' || b.board[level][x][y - 1] == 'I')
				result = false;
			break;
		case 2: // baixo
			if (b.board[level][x][y + 1] == 'X' || b.board[level][x][y + 1] == 'I')
				result = false;
			break;
		case 3: // esq
			if (b.board[level][x - 1][y] == 'X' || b.board[level][x - 1][y] == 'I')
				result = false;
			break;
		case 4: // dir
			if (b.board[level][x + 1][y] == 'X' || b.board[level][x + 1][y] == 'I')
				result = false;
			break;
		default:
			result = false;
			break;
		}

		return result;
	}

	public static boolean checkIfLose(Board board, int level) {
		boolean result = false;
		for (int i = 0; i < board.entities.size(); i++) {
			if (board.hero.coordX == board.entities.get(i).coordX) {
				if (Math.abs(board.hero.coordY - board.entities.get(i).coordY) == 1)
					result = true;
			} else if (board.hero.coordY == board.entities.get(i).coordY) {
				if (Math.abs(board.hero.coordX - board.entities.get(i).coordX) == 1)
					result = true;
			}

		}

		return result;
	}
	
	public boolean checkIfWin(Board board, int level, int newX, int newY){
		if (board.board[level][newX][newY] == 'S')
			return true;
		else
			return false;
	}
	
	public boolean checkForKey(Board board, int level, int newX, int newY){
		if (board.board[level][newX][newY] == 'k') {
			board.key.openDoors(board.hero);
			return true;
		} else {
			return false;
		}
	}

	public static int calculateNewX(int movement, int x) {
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

	public static int calculateNewY(int movement, int y) {
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
