package dkeep.gui;

import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import dkeep.logic.GameLevels;
import dkeep.logic.State;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPanel;

public class EnhancedGameWindow implements Serializable {

	// components
	private JFrame frmDungeonKeep;
	private JTextField tflNumberOfOgres;
	private JButton btnUp, btnDown, btnRight, btnLeft, btnStay;
	private JLabel lblMessages;
	// private JTextPane tpnGameField;
	private Gmap jpGamePanel = new Gmap();
	private transient LevelEditor jframeLevelEditor;
	//EnhancedGameWindow this_window;
	// game logic
	private State estado_jogo = new State();
	private GameLevels niveis = new GameLevels();
	private Boolean lost_game = false;
	private int level = 0;
	private int guarda = 0;
	private int numberOgres = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnhancedGameWindow window = new EnhancedGameWindow();
					window.frmDungeonKeep.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EnhancedGameWindow() {
		//this_window = this;
		initialize();
	}

	private boolean isValid(String text) {
		try {
			Integer.parseInt(text);

		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmDungeonKeep = new JFrame();
		
		frmDungeonKeep.setFocusable(true);
		frmDungeonKeep.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	btnUp.setVisible(false);
            	btnDown.setVisible(false);
            	btnLeft.setVisible(false);
            	btnRight.setVisible(false);
            	btnStay.setVisible(false);
            	
            	switch (e.getKeyCode()) {
        		case KeyEvent.VK_LEFT:
        			if(btnLeft.isEnabled()){
        				int newX = estado_jogo.calculateNewX(3, estado_jogo.hero.getX());
        				int newY = estado_jogo.calculateNewY(3, estado_jogo.hero.getY());
        				estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(),
        						newX, newY);
        				estado_jogo.hero.moveEntity(3);
        				lblMessages.setText("Hero moved left.");

        				updateGameLogic();

        				printGameGUI();
        			}
        			break;
        		case KeyEvent.VK_RIGHT:
        			if(btnRight.isEnabled()){
        				int newX = estado_jogo.calculateNewX(4, estado_jogo.hero.getX());
        				int newY = estado_jogo.calculateNewY(4, estado_jogo.hero.getY());
        				estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(),
        						newX, newY);
        				estado_jogo.hero.moveEntity(4);
        				lblMessages.setText("Hero moved right.");
        				updateGameButtons();
        				printGameGUI();
        				updateGameLogic();
        			}
        			break;
        		case KeyEvent.VK_UP:
        			if(btnUp.isEnabled()){
        				int newX = estado_jogo.calculateNewX(1, estado_jogo.hero.getX());
        				int newY = estado_jogo.calculateNewY(1, estado_jogo.hero.getY());
        				estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(),
        						newX, newY);
        				estado_jogo.hero.moveEntity(1);
        				lblMessages.setText("Hero moved up.");
        				updateGameButtons();
        				printGameGUI();
        				updateGameLogic();
        			}
        			break;
        		case KeyEvent.VK_DOWN:
        			if(btnDown.isEnabled()){
        				int newX = estado_jogo.calculateNewX(2, estado_jogo.hero.getX());
        				int newY = estado_jogo.calculateNewY(2, estado_jogo.hero.getY());
        				estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(),
        						newX, newY);
        				estado_jogo.hero.moveEntity(2);
        				lblMessages.setText("Hero moved down.");
        				updateGameButtons();
        				printGameGUI();
        				updateGameLogic();
        			}
        			break;
        			
        		case KeyEvent.VK_SPACE:
        			if(btnStay.isEnabled()){
        				int newX = estado_jogo.calculateNewX(5, estado_jogo.hero.getX());
        				int newY = estado_jogo.calculateNewY(5, estado_jogo.hero.getY());
        				estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(),
        						newX, newY);
        				estado_jogo.hero.moveEntity(5);
        				lblMessages.setText("Hero stayed in his place.");
        				updateGameButtons();
        				printGameGUI();
        				updateGameLogic();
        			}
        			
        			break;
        		}
            }
        });
		
		frmDungeonKeep.getContentPane().setBackground(Color.GREEN);
		frmDungeonKeep.setTitle("Dungeon Keep");
		frmDungeonKeep.setBounds(100, 100, 510, 490);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);

		try {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon programIcon = new ImageIcon(cl.getResource("resources/Icon.png"));
			frmDungeonKeep.setIconImage(programIcon.getImage());
		} catch (Exception whoJackedMyIcon) {
			System.out.println("Could not load program icon.");
		}

		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(20, 20, 110, 24);
		frmDungeonKeep.getContentPane().add(lblNumberOfOgres);

		tflNumberOfOgres = new JTextField();
		tflNumberOfOgres.setBounds(140, 22, 30, 20);
		frmDungeonKeep.getContentPane().add(tflNumberOfOgres);
		tflNumberOfOgres.setColumns(10);

		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(20, 50, 110, 24);
		frmDungeonKeep.getContentPane().add(lblGuardPersonality);

		JComboBox cbbGuardPersonality = new JComboBox();
		cbbGuardPersonality.setModel(new DefaultComboBoxModel(new String[] { "Rookie", "Drunken", "Suspicious" }));
		cbbGuardPersonality.setBounds(140, 50, 110, 22);
		frmDungeonKeep.getContentPane().add(cbbGuardPersonality);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnUp.setVisible(true);
            	btnDown.setVisible(true);
            	btnLeft.setVisible(true);
            	btnRight.setVisible(true);
            	btnStay.setVisible(true);
				
				String guardaEscolhido = (String) cbbGuardPersonality.getSelectedItem();

				if (isValid(tflNumberOfOgres.getText())) {
					if (Integer.parseInt(tflNumberOfOgres.getText()) < 6
							&& Integer.parseInt(tflNumberOfOgres.getText()) > 0) {
						numberOgres = Integer.parseInt(tflNumberOfOgres.getText());
					}
				}
				// predetermined rookie
				if (guardaEscolhido == "Drunken")
					guarda = 1;
				else if (guardaEscolhido == "Suspicious")
					guarda = 2;

				level = 1;
				niveis = new GameLevels();
				estado_jogo = new State(niveis.getLevel(level));
				estado_jogo.startEntities(guarda, numberOgres);

				jpGamePanel.estado_atual = estado_jogo;
				jpGamePanel.setEnabled(true);
				updateGameButtons();
				printGameGUI();

				lblMessages.setText("You are now entering a mysterious place...");
				
				frmDungeonKeep.requestFocusInWindow();
			}

		});
		btnNewGame.setBounds(345, 30, 120, 25);
		frmDungeonKeep.getContentPane().add(btnNewGame);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(348, 415, 117, 25);
		frmDungeonKeep.getContentPane().add(btnExit);


		btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int newX = estado_jogo.calculateNewX(1, estado_jogo.hero.getX());
				int newY = estado_jogo.calculateNewY(1, estado_jogo.hero.getY());
				estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(),
						newX, newY);
				estado_jogo.hero.moveEntity(1);
				lblMessages.setText("Hero moved up.");
				updateGameButtons();
				printGameGUI();
				updateGameLogic();
				
				frmDungeonKeep.requestFocusInWindow();
			}
		});
		btnUp.setBounds(375, 150, 66, 24);
		frmDungeonKeep.getContentPane().add(btnUp);
		btnUp.setEnabled(false);

		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int newX = estado_jogo.calculateNewX(2, estado_jogo.hero.getX());
				int newY = estado_jogo.calculateNewY(2, estado_jogo.hero.getY());
				estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(),
						newX, newY);
				estado_jogo.hero.moveEntity(2);
				lblMessages.setText("Hero moved down.");
				updateGameButtons();
				printGameGUI();
				updateGameLogic();
				
				frmDungeonKeep.requestFocusInWindow();
			}
		});
		btnDown.setBounds(375, 224, 66, 25);
		frmDungeonKeep.getContentPane().add(btnDown);
		btnDown.setEnabled(false);

		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int newX = estado_jogo.calculateNewX(3, estado_jogo.hero.getX());
				int newY = estado_jogo.calculateNewY(3, estado_jogo.hero.getY());
				estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(),
						newX, newY);
				estado_jogo.hero.moveEntity(3);
				lblMessages.setText("Hero moved left.");

				updateGameLogic();

				printGameGUI();
				
				frmDungeonKeep.requestFocusInWindow();

			}
		});
		btnLeft.setBounds(334, 188, 70, 25);
		frmDungeonKeep.getContentPane().add(btnLeft);
		btnLeft.setEnabled(false);

		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int newX = estado_jogo.calculateNewX(4, estado_jogo.hero.getX());
				int newY = estado_jogo.calculateNewY(4, estado_jogo.hero.getY());
				estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(),
						newX, newY);
				estado_jogo.hero.moveEntity(4);
				lblMessages.setText("Hero moved right.");
				updateGameButtons();
				printGameGUI();
				updateGameLogic();
				
				frmDungeonKeep.requestFocusInWindow();
			}
		});
		btnRight.setBounds(414, 188, 70, 25);
		frmDungeonKeep.getContentPane().add(btnRight);
		btnRight.setEnabled(false);

		btnStay = new JButton("Stay");
		btnStay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int newX = estado_jogo.calculateNewX(5, estado_jogo.hero.getX());
				int newY = estado_jogo.calculateNewY(5, estado_jogo.hero.getY());
				estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(),
						newX, newY);
				estado_jogo.hero.moveEntity(5);
				lblMessages.setText("Hero stayed in his place.");
				updateGameButtons();
				printGameGUI();
				updateGameLogic();
				
				frmDungeonKeep.requestFocusInWindow();
			}
		});
		btnStay.setBounds(375, 260, 66, 25);
		frmDungeonKeep.getContentPane().add(btnStay);
		btnStay.setEnabled(false);

		lblMessages = new JLabel("Welcome to our game!");
		lblMessages.setBounds(20, 420, 300, 20);
		frmDungeonKeep.getContentPane().add(lblMessages);

		jpGamePanel = new Gmap();
		jpGamePanel.setBounds(10, 90, 320, 320);
		frmDungeonKeep.getContentPane().add(jpGamePanel);

		jpGamePanel.setEnabled(false);
		
		JButton btnLevelEditor = new JButton("Level Editor");
		btnLevelEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmDungeonKeep.setVisible(false);
				int ncols =10, nrows = 10;
				String[] nums = {"5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
				nrows =  Integer.parseInt((String)JOptionPane.showInputDialog(frmDungeonKeep, "How many rows?", "Rows", JOptionPane.QUESTION_MESSAGE, null, nums, null));
				ncols =  Integer.parseInt((String)JOptionPane.showInputDialog(frmDungeonKeep, "How many columns?", "Columns", JOptionPane.QUESTION_MESSAGE, null, nums, null));
				jframeLevelEditor = new LevelEditor(frmDungeonKeep, nrows, ncols);
				jframeLevelEditor.setVisible(true);
			}
		});
		btnLevelEditor.setBounds(213, 21, 89, 23);
		frmDungeonKeep.getContentPane().add(btnLevelEditor);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileOutputStream fileOut;
				try {
					fileOut = new FileOutputStream("src/resources/saveGame.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(estado_jogo);
					out.writeObject(lost_game);
					out.writeObject(level);
					out.writeObject(guarda);
					out.writeObject(numberOgres);

					out.close();
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception w){
					w.printStackTrace();
				}
				lblMessages.setText("Saved Game.");
				frmDungeonKeep.requestFocusInWindow();
			}
		});
		btnSave.setBounds(376, 64, 89, 23);
		frmDungeonKeep.getContentPane().add(btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
			      try {
			         FileInputStream fileIn = new FileInputStream("src/resources/saveGame.ser");
			         ObjectInputStream in = new ObjectInputStream(fileIn);
			         estado_jogo = (State) in.readObject();
			         lost_game = (Boolean) in.readObject();
			         level = (int) in.readObject();
			         guarda = (int) in.readObject();
			         numberOgres = (int) in.readObject();
			         in.close();
			         fileIn.close();
			      }catch(IOException i) {
			         i.printStackTrace();
			         return;
			      }catch(ClassNotFoundException c) {
			         System.out.println("Class Not Found.");
			         c.printStackTrace();
			         return;
			      }
			      
			      printGameGUI();
			      updateGameButtons();
			      lblMessages.setText("Load Game.");
			      frmDungeonKeep.requestFocusInWindow();
			}
		});
		btnLoad.setBounds(376, 90, 89, 23);
		frmDungeonKeep.getContentPane().add(btnLoad);
	}

	private void updateGameButtons() {
		btnStay.setEnabled(true);
		int x = estado_jogo.hero.getX();
		int y = estado_jogo.hero.getY();

		if (estado_jogo.checkMove(1, x, y))
			btnUp.setEnabled(true);
		else
			btnUp.setEnabled(false);

		if (estado_jogo.checkMove(2, x, y))
			btnDown.setEnabled(true);
		else
			btnDown.setEnabled(false);

		if (estado_jogo.checkMove(3, x, y))
			btnLeft.setEnabled(true);
		else
			btnLeft.setEnabled(false);

		if (estado_jogo.checkMove(4, x, y))
			btnRight.setEnabled(true);
		else
			btnRight.setEnabled(false);

	}

	private void printGameGUI() {
		jpGamePanel.setEstadoJogo(estado_jogo);
		jpGamePanel.repaint();
	}

	private void updateGameLogic() {
		printGameGUI();
		boolean lole = estado_jogo.updateBoard(lost_game);

		if (lole == true) { // won the game
			if (++level <= niveis.getNumberOfLevels()) {
				lblMessages.setText("You won! Next Level.");
				estado_jogo = new State(niveis.getLevel(level));
				estado_jogo.startEntities(guarda, numberOgres);
				return;

			} else {
				printGameGUI();
				lblMessages.setText("You won the Game!");
				btnStay.setEnabled(false);
				btnUp.setEnabled(false);
				btnDown.setEnabled(false);
				btnLeft.setEnabled(false);
				btnRight.setEnabled(false);
				return;
			}
		} else {
			if (lost_game.booleanValue() == true) {
				printGameGUI();
				lblMessages.setText("You were caught! Game over.");
				btnStay.setEnabled(false);
				btnUp.setEnabled(false);
				btnDown.setEnabled(false);
				btnLeft.setEnabled(false);
				btnRight.setEnabled(false);
			} else
				updateGameButtons();
		}
		// checking for lose
		if (estado_jogo.checkIfLose()) {
			printGameGUI();
			lblMessages.setText("You were caughterino! Game over.");
			btnStay.setEnabled(false);
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
		}

	}
}