package DungeonKeep;

public class Board {
	char[][] board = {{'X','X','X','X','X','X','X','X','X','X'},
					  {'X','H',' ',' ','I',' ','X',' ','G','X'},
					  {'X','X','X',' ','X','X','X',' ',' ','X'},
					  {'X',' ','I',' ','I',' ','X',' ',' ','X'},
					  {'X','X','X',' ','X','X','X',' ',' ','X'},
					  {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
					  {'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
					  {'X','X','X',' ','X','X','X','X',' ','X'},
					  {'X',' ','I',' ','I',' ','X','k',' ','X'},
					  {'X','X','X','X','X','X','X','X','X','X'}};
	
	public void printBoard(){
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

}
