package dkeep.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class GameLevels {
	private Vector<char[][]> board_levels = new Vector<char[][]>();/*= { { { 'X', 'X', 'X', 'X', 'X', 'I', 'I', 'X', 'X', 'X' },
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
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } } };  */
	
	private int number_of_levels;
	
	public GameLevels(){
		//number_of_levels = board_levels.length;
		//le do ficheiro
		try{
			Scanner inputF = new Scanner(new File("src/resources/levels.txt"));
			//int niveis = Integer.parseInt(inputF.nextLine());
			
			//char [][][] novos_niveis = new char[niveis][rows][cols];
			int nivel = 0;
			String linha;
			
			while(true){
				int rows = Integer.parseInt(inputF.nextLine());
				int cols = Integer.parseInt(inputF.nextLine());
				//inputF.nextLine();
				char[][] nivelAtual = new char[rows][cols];
				for(int i = 0; i < rows; i++){
					linha = inputF.nextLine();
					char[] linhaC =  linha.toCharArray();
					for(int j = 0; j < cols; j++)
						nivelAtual[i][j]=linhaC[j];
					 
				}
				
				board_levels.add(nivelAtual);
				if(!inputF.hasNextLine())
					break;
				//inputF.nextLine();
				//nivel++;
			}
			
			//board_levels = novos_niveis.clone();
			number_of_levels = board_levels.size();
			
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
		
	}
	
	public char[][] getLevel(int level){
		if(level < 1 || level > number_of_levels)
			throw new ArrayIndexOutOfBoundsException (level);
		else return board_levels.get(level-1).clone();
	}
	
	public int getNumberOfLevels(){
		return number_of_levels;
	}
	
	public void addLevelToFile(int rows, int cols, char[][] level){
		try {
			FileWriter fw = new FileWriter("src/resources/levels.txt",true);
			fw.write("\n");
			fw.write(rows+"\n");
			fw.write(cols+"\n");
			
			//transpose matrix 
//			 char[][] temp = new char[level[0].length][level.length];
//		        for (int i = 0; i < level.length; i++)
//		            for (int j = 0; j < level[0].length; j++)
//		                temp[j][i] = level[i][j];
			char[][] temp = level;
			//write to file
			for(int i = 0; i < temp.length; i++){
				for(int j = 0; j < temp[0].length; j++){
					fw.write(temp[i][j]+"");
				}
				
				
				
				
				
				//fw.write(level[i].toString());
				
				if(i+1 < temp.length){
					fw.write("\n");
				}

			}
		    fw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}
}
