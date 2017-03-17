package dkeep.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameLevels {
	private char[][][] board_levels = { { { 'X', 'X', 'X', 'X', 'X', 'I', 'I', 'X', 'X', 'X' },
		{ 'X', 'H', 'X', ' ', 'X', ' ', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'I', 'X', ' ', ' ', 'X', 'I', 'X' },
		{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', 'I', 'X', 'I', 'X', ' ', ' ', 'X', 'I', 'X' },
		{ 'X', ' ', 'X', ' ', 'X', ' ', ' ', 'X', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', ' ', ' ', 'X', 'X', 'X' },
		{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', 'k', 'X' }, { 'X', 'G', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
		{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } },

				{ { 'X', 'I', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'H', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'k', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } } };  
	
	private int number_of_levels;
	
	public GameLevels(){
		//le do ficheiro
		try{
			Scanner inputF = new Scanner(new File("src/resources/levels.txt"));
			int niveis = inputF.nextInt();
			int rows = inputF.nextInt();
			int cols = inputF.nextInt();
			char [][][] novos_niveis = new char[niveis][rows][cols];
			int nivel = 0;
			String linha;
			inputF.nextLine();
			while(nivel < niveis){
				
				for(int i = 0; i < rows; i++){
					linha = inputF.nextLine();
					char[] linhaC =  linha.toCharArray();
					for(int j = 0; j < cols; j++)
						novos_niveis[nivel][i]=linhaC;
					 
					System.out.println(linha);
				}
				if(!inputF.hasNextLine())
					break;
				inputF.nextLine();
				nivel++;
			}
			
			board_levels = novos_niveis.clone();
			number_of_levels = novos_niveis.length;
			
		}
		catch(FileNotFoundException e){
			System.out.println("Damn it I couldnt find the file.");
		}
		catch(Exception whoScrewedThisUp){
			System.out.println("Well done you slob.");
			System.out.println(whoScrewedThisUp.getClass());
			whoScrewedThisUp.printStackTrace();
		}
		
		//end read file
		//number_of_levels = board_levels.length;
	}
	
	public char[][] getLevel(int level){
		if(level < 1 || level > number_of_levels)
			throw new ArrayIndexOutOfBoundsException (level);
		else return board_levels[level-1].clone();
	}
	
	public int getNumberOfLevels(){
		return number_of_levels;
	}
}
