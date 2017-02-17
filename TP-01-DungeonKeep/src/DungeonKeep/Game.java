package DungeonKeep;

import java.util.Scanner;

public class Game {

	public int receiveCommand() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Insert direction (u,d,l,r): ");
		String movement = scan.nextLine();
		scan.close();
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

	public void startEntities(Board b, int level) {
		for (int i = 0; i < b.board[level].length; i++) {
			for (int j = 0; j < b.board[level].length; j++) {
				if (b.board[level][i][j] == 'H') {
					Hero h = new Hero();
					h.startAtPosition(i, j);
				} 
				
				else if (b.board[level][i][j] == 'G') {
					Guard g = new Guard();
					g.startAtPosition(i, j);
				} 
				
				else if (b.board[level][i][j] == 'O') {
					Ogre o = new Ogre();
					o.startAtPosition(i, j);
				}
			}
		}
	}

	public boolean checkMove(int movement, int x, int y, Board b, int level) {
		boolean result = true;

		switch (movement) {
		case 1:
			if (b.board[level][x][y - 1] == 'X' || b.board[level][x][y - 1] == 'I')
				result = false;
			break;
		case 2:
			if (b.board[level][x][y + 1] == 'X' || b.board[level][x][y + 1] == 'I')
				result = false;
			break;
		case 3:
			if (b.board[level][x - 1][y] == 'X' || b.board[level][x - 1][y] == 'I')
				result = false;
			break;
		case 4:
			if (b.board[level][x + 1][y] == 'X' || b.board[level][x + 1][y] == 'I')
				result = false;
			break;
		default:
			result = false;
			break;
		}

		return result;
	}

}
