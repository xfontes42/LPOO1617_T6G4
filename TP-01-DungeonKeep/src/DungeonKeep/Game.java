package DungeonKeep;
import java.util.Scanner;

public class Game {

	public int receiveCommand(){
		Scanner scan = new Scanner(System.in);
		System.out.print("Insert direction (u,d,l,r): ");
		String movement = scan.nextLine();
		
		switch(movement){
		case "u":
			return 1;
		case "d":
			return 2;
		case "l":
			return 3;
		case "r":
			return 4;
		default:
			return 0;
		}
		
	}
	
	 
}
