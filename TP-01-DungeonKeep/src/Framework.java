import DungeonKeep.*;

public class Framework {

	public static void main(String[] args) {
		int level = 0;
		Board board0 = new Board(level);
		Game game = new Game();
		board0.startEntities(level);
		//TODO fazer uma condição de terminação para o ciclo
		/*while (!fim)	<-- É PRECISO ATUALIZAR A CONDIÇÃO
		 * lê movimento (feito)
		 * processa
		 * aplica regras
		 * avalia estado
		 */
		
		while (true) {
			board0.printBoard();
			
			//processing player input
			boolean validMove = false;
			while (!validMove) {
				int command = game.receiveCommand();
				if (!game.checkMove(command, board0.hero.coordX, board0.hero.coordY, board0, level)) {
					System.out.println("Invalid command. Please try again.");
				} else {
					validMove = true;
					int newX = game.calculateNewX(command, board0.hero.coordX);
					int newY = game.calculateNewY(command, board0.hero.coordY);
					board0.updateEntity('H', board0.hero.coordX, board0.hero.coordY, newX, newY, level);
				}
			}
			
			//checking if lose
			if (game.checkIfLose(board0, level))
				break;
		}
		
//		System.out.println();
//		Board b1 = new Board(1);
//		b1.printBoard();
	}

}
