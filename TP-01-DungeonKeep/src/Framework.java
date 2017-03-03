import java.util.Scanner;
//import java.lang.Math;

import DungeonKeep.*;

public class Framework {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int level = 0; //meter zero para come�ar no primeiro nivel 
		Board board0 = new Board(level);
		Game game = new Game();
		board0.startEntities(level);
		//TO DO fazer uma condi��o de termina��o para o ciclo
		/*while (!fim)	<-- � PRECISO ATUALIZAR A CONDI��O
		 * l� movimento (feito)
		 * processa (feito (por agora))
		 * aplica regras
		 * avalia estado
		 */
		Boolean lost_game = false;
		while (level < board0.board.length) {
			board0.printBoard();
			
			//processing player input
			boolean validMove = false;
			while (!validMove) {
				int command = game.receiveCommand(scan);
//				System.out.println(command);
//				System.out.println(game.checkMove(command, board0.hero.coordX, board0.hero.coordY, board0, level));
				if (!game.checkMove(command, board0.hero.coordX, board0.hero.coordY, board0, level)) {
					validMove = false;
					System.out.println("Invalid command. Please try again.");
				} else {
					validMove = true;
					int newX = game.calculateNewX(command, board0.hero.coordX);
					int newY = game.calculateNewY(command, board0.hero.coordY);
					board0.updateEntity('H', board0.hero.coordX, board0.hero.coordY, newX, newY, level);
					board0.hero.moveEntity(command);
				}
			}
			//
			if(board0.updateBoard(lost_game) == true){ //won the game
				if (++level < board0.board.length) {
					System.out.println('\n' + "You won! Next Level.");
					// instanciar nivel seguinte
					board0 = new Board(level);
					game = new Game();
					board0.startEntities(level);
				} else {
					System.out.println('\n' + "You won the game! Congratulations!");
					break;
				}
				
			} else {
				if(lost_game.booleanValue() == true){
					board0.printBoard();
					System.out.println('\n' + "You were caught! Game over.");
					break;
				}
			}
			//checking for lose
			if (game.checkIfLose(board0, level)){
				board0.printBoard();
				System.out.println('\n' + "You were caught! Game over.");
				break;
			}
		}
		
//		System.out.println();
//		Board b1 = new Board(1);
//		b1.printBoard();
		
		scan.close();
	}

}
