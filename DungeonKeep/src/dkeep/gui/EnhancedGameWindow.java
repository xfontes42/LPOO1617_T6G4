package dkeep.gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import dkeep.logic.GameLevels;
import dkeep.logic.State;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPanel;

public class EnhancedGameWindow {

	//components
	private JFrame frmDungeonKeep;
	private JTextField tflNumberOfOgres;
	private JButton btnUp, btnDown, btnRight, btnLeft, btnStay;
	private JLabel lblMessages;
	//private JTextPane tpnGameField;
	private Gmap jpGamePanel = new Gmap();
	
	//game logic
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

//		tpnGameField = new JTextPane();
//		tpnGameField.setFont(new Font("monospaced", Font.PLAIN, 22));
//		tpnGameField.setBounds(20, 110, 270, 300);
//		frmDungeonKeep.getContentPane().add(tpnGameField);

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
//		String game_space = "";
//		char[][] whatspoppingB = estado_jogo.board;
//
//		for (int i = 0; i < whatspoppingB.length; i++) {
//			for (int j = 0; j < whatspoppingB[i].length; j++) {
//				game_space += ((char) whatspoppingB[j][i] + " ");
//			}
//			game_space += "\n";
//		}
		
		jpGamePanel.setEstadoJogo(estado_jogo);
		jpGamePanel.repaint();
		
		//tpnGameField.setText(game_space);
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
				//updateGameButtons();
				

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
			}
			else updateGameButtons();
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