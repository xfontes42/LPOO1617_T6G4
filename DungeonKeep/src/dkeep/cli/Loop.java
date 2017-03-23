package dkeep.cli;

import java.util.Scanner;

import dkeep.gameLevels.GameLevels;
import dkeep.logic.*;

public class Loop {
	
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		welcomeMessage();
		GameLevels library = new GameLevels();
		gameLoop(library, scan);		
		scan.close();
		
	}
	
	public static void moveHero(State gameplay, int command){
		int newX = gameplay.calculateNewX(command, gameplay.hero.getX());
		int newY = gameplay.calculateNewY(command, gameplay.hero.getY());
		gameplay.updateEntity(gameplay.hero.getSprite(), gameplay.hero.getX(), gameplay.hero.getY(), newX, newY);
		gameplay.hero.moveEntity(command);
	}
	
	public static void processMove(Scanner scan, State gameplay){
		boolean validMove = false;
		while (!validMove) {
			int command = getCommand(scan);
			
			if (!gameplay.checkMove(command, gameplay.hero.getX(), gameplay.hero.getY())) {
				validMove = false;
				System.out.println("Invalid command. Please try again.");
			} else {
				validMove = true;
				moveHero(gameplay, command);
				
			}
		}
	}
	
	public static void printGameOver(State gameplay){
		gameplay.printBoard();
		System.out.println('\n' + "You were caught! Game over.");
	}	
	
	public static void gameLoop(GameLevels library, Scanner scan){
		int level = 1;
		State gameplay = new State(library.getLevel(level));
		Boolean lost_game = false;
		while(level <= library.getNumberOfLevels()){
			gameplay.printBoard();
			processMove(scan, gameplay);

			if (gameplay.updateBoard(lost_game) == true) { // won the game
				if (++level <= library.getNumberOfLevels()) {
					System.out.println('\n' + "You won! Next Level.");
					gameplay = new State(library.getLevel(level));
				} else {
					System.out.println('\n' + "You won the game! Congratulations!");
					break;
				}
				
			} else {
				if(lost_game.booleanValue() == true){
					printGameOver(gameplay);
					break;
				}
			}
			//checking for lose
			if (gameplay.checkIfLose()){
				printGameOver(gameplay);
				break;
			}
		}
	}
	
	/**
	 * @brief Prints out a simple game logo.
	 */
	public static void welcomeMessage(){
		
		System.out.println("   __________________");
		System.out.println("  /\\                 \\");
		System.out.println("  \\_|                |");
		System.out.println("    |  DUNGEON KEEP  |");
		System.out.println("    |                |");
		System.out.println("    |  ______________|_");
		System.out.println("    \\_/_______________/");
		
		
	}
	
	/**
	 * @brief Receives a move using the command line.
	 * @param scan The input buffer
	 * @return 1 if player goes up, 2 if down, 3 if left, 4 if right, 5 if no-move and 0 for invalid inputs.
	 */
	public static int getCommand(Scanner scan){
		String resultString;
		int resultInt;
		System.out.print("Insert command (up, down, left, right, stay): ");
		resultString = scan.next(); 
		switch (resultString) {
		case "u": //up --> 1
			resultInt = 1;
			break;
		case "d": //down --> 2
			resultInt = 2;
			break;
		case "l": //left --> 3
			resultInt = 3;
			break;
		case "r": //right --> 4
			resultInt = 4;
			break;	
		case "s": //stay in place --> 5
			resultInt = 5;
			break;
		default:
			resultInt = 0;
			break;
		}
		return resultInt;
	}
}
