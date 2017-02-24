package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.*;

public class Loop {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		welcomeMessage();
		
		/*TODO Criar loop:
		 * 1. perguntar direcao
		 * 2. invocar logica do jogo
		 * 3. mostrar o tabuleiro
		 */
		
		scan.close();
		
	}
	
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
	public int getCommand(Scanner scan){
		String resultString;
		int resultInt;
		
		System.out.print("Insert command (up, down, left, right, stay): ");
		resultString = scan.next();
		
		switch (resultString) {
		//up --> 1
		case "u":
		case "up":
			resultInt = 1;
			break;

		//down --> 2
		case "d":
		case "dn":
		case "down":
			resultInt = 2;
			break;
			
		//left --> 3
		case "l":
		case "left":
		case "lt":
			resultInt = 3;
			break;
		
		//right --> 4
		case "r":
		case "right":
		case "rt":
			resultInt = 4;
			break;
		
		//stay in place --> 5
		case "s":
		case "stay":
			resultInt = 5;
			break;
			
		//error (or undefined) --> 0
		default:
			resultInt = 0;
			break;
		}
		
		return resultInt;
	}
	
	

}
