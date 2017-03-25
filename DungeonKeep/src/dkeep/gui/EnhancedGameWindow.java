package dkeep.gui;

import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import dkeep.gameLevels.GameLevels;
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
	private Gmap jpGamePanel = new Gmap();
	private transient LevelEditor jframeLevelEditor;
	// game logic
	private State estado_jogo = new State();
	private GameLevels niveis = new GameLevels();
	private Boolean lost_game = false;
	private int level = 0;
	private int guarda = 0;
	private int numberOgres = 1;
	protected JComboBox cbbGuardPersonality = new JComboBox();
	private boolean WON = false;

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
		initialize_frmDungeonKeep();
		try {
			ClassLoader cl = this.getClass().getClassLoader();
			ImageIcon programIcon = new ImageIcon(cl.getResource("resources/Icon.png"));
			frmDungeonKeep.setIconImage(programIcon.getImage());
		} catch (Exception whoJackedMyIcon) {
			System.out.println("Could not load program icon.");
		}
		initialize_firstContact();
		initialize_buttonsMove();
		initialize_LevelEditor_and_textPanel();
		initialize_SaveAndLoad();
	}

	private void initialize_LevelEditor_and_textPanel() {
		startPanels();
		startButtonLevelEditor();
	}

	private void startPanels() {
		lblMessages = new JLabel("Welcome to our game!");
		lblMessages.setBounds(20, 420, 300, 20);
		frmDungeonKeep.getContentPane().add(lblMessages);
		jpGamePanel = new Gmap();
		jpGamePanel.setBounds(10, 90, 320, 320);
		frmDungeonKeep.getContentPane().add(jpGamePanel);
		jpGamePanel.setEnabled(false);
	}

	private void startButtonLevelEditor() {
		JButton btnLevelEditor = new JButton("Level Editor");
		btnLevelEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmDungeonKeep.setVisible(false);
				int ncols = 10, nrows = 10;
				String[] nums = { "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
						"20" };
				nrows = Integer.parseInt((String) JOptionPane.showInputDialog(frmDungeonKeep, "How many rows?", "Rows",
						JOptionPane.QUESTION_MESSAGE, null, nums, null));
				ncols = Integer.parseInt((String) JOptionPane.showInputDialog(frmDungeonKeep, "How many columns?",
						"Columns", JOptionPane.QUESTION_MESSAGE, null, nums, null));
				jframeLevelEditor = new LevelEditor(frmDungeonKeep, nrows, ncols);
				jframeLevelEditor.setVisible(true);
			}
		});
		btnLevelEditor.setBounds(213, 21, 89, 23);
		frmDungeonKeep.getContentPane().add(btnLevelEditor);

	}

	private void initialize_buttonsMove() {
		initialize_buttonUp();
		initialize_buttonDown();
		initialize_buttonLeft();
		initialize_buttonRight();
		initialize_buttonStay();
	}

	private void initialize_buttonStay() {
		btnStay = new JButton("Stay");
		btnStay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fire_up_buttonStay();

				frmDungeonKeep.requestFocusInWindow();
			}
		});
		btnStay.setBounds(375, 260, 66, 25);
		frmDungeonKeep.getContentPane().add(btnStay);
		btnStay.setEnabled(false);
	}

	protected void fire_up_buttonStay() {
		int newX = estado_jogo.calculateNewX(5, estado_jogo.hero.getX());
		int newY = estado_jogo.calculateNewY(5, estado_jogo.hero.getY());
		estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(), newX,
				newY);
		estado_jogo.hero.moveEntity(5);
		lblMessages.setText("Hero stayed in his place.");
		updateGameLogic();
		updateGameButtons();
		printGameGUI();
	}

	private void initialize_buttonRight() {
		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fire_up_buttonRight();

				frmDungeonKeep.requestFocusInWindow();
			}
		});
		btnRight.setBounds(414, 188, 70, 25);
		frmDungeonKeep.getContentPane().add(btnRight);
		btnRight.setEnabled(false);
	}

	protected void fire_up_buttonRight() {
		int newX = estado_jogo.calculateNewX(4, estado_jogo.hero.getX());
		int newY = estado_jogo.calculateNewY(4, estado_jogo.hero.getY());
		estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(), newX,
				newY);
		estado_jogo.hero.moveEntity(4);
		lblMessages.setText("Hero moved right.");
		updateGameLogic();
		updateGameButtons();
		printGameGUI();
	}

	private void initialize_buttonLeft() {
		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fire_up_buttonLeft();
				frmDungeonKeep.requestFocusInWindow();
			}
		});
		btnLeft.setBounds(334, 188, 70, 25);
		frmDungeonKeep.getContentPane().add(btnLeft);
		btnLeft.setEnabled(false);
	}

	private void initialize_buttonDown() {
		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fire_up_buttonDown();
				frmDungeonKeep.requestFocusInWindow();
			}
		});
		btnDown.setBounds(375, 224, 66, 25);
		frmDungeonKeep.getContentPane().add(btnDown);
		btnDown.setEnabled(false);
	}

	protected void fire_up_buttonDown() {
		int newX = estado_jogo.calculateNewX(2, estado_jogo.hero.getX());
		int newY = estado_jogo.calculateNewY(2, estado_jogo.hero.getY());
		estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(), newX,
				newY);
		estado_jogo.hero.moveEntity(2);
		lblMessages.setText("Hero moved down.");
		updateGameLogic();
		updateGameButtons();
		printGameGUI();
	}

	private void initialize_buttonUp() {
		btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fire_up_buttonUp();
				frmDungeonKeep.requestFocusInWindow();
			}
		});
		btnUp.setBounds(375, 150, 66, 24);
		frmDungeonKeep.getContentPane().add(btnUp);
		btnUp.setEnabled(false);
	}

	private void initialize_SaveAndLoad() {
		initializeSave();
		initializeLoad();
	}

	private void initializeLoad() {
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					loadSerialization();
				} catch (IOException i) {
					i.printStackTrace();
					return;
				} catch (ClassNotFoundException c) {
					c.printStackTrace();
					return;
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
				printGameGUI();
				updateGameButtons();
				lblMessages.setText("Load Game.");
				frmDungeonKeep.setFocusable(true);
				frmDungeonKeep.requestFocusInWindow();
			}
		});
		btnLoad.setBounds(376, 90, 89, 23);
		frmDungeonKeep.getContentPane().add(btnLoad);

	}

	protected void loadSerialization() throws Exception {
		FileInputStream fileIn = new FileInputStream("src/resources/saveGame.ser");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		estado_jogo = (State) in.readObject();
		lost_game = (Boolean) in.readObject();
		level = (int) in.readObject();
		guarda = (int) in.readObject();
		numberOgres = (int) in.readObject();
		in.close();
		fileIn.close();
	}

	private void initializeSave() {
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					saveSerialization();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception w) {
					w.printStackTrace();
				}
				lblMessages.setText("Saved Game.");
				frmDungeonKeep.requestFocusInWindow();
			}
		});
		btnSave.setBounds(376, 64, 89, 23);
		frmDungeonKeep.getContentPane().add(btnSave);

	}

	protected void saveSerialization() throws Exception {
		FileOutputStream fileOut;
		fileOut = new FileOutputStream("src/resources/saveGame.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(estado_jogo);
		out.writeObject(lost_game);
		out.writeObject(level);
		out.writeObject(guarda);
		out.writeObject(numberOgres);
		out.close();
		fileOut.close();
	}

	private void initialize_firstContact() {
		initialize_GuardAndOgreContact();
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WON = false;
				turn_on_vis_buttons();
				preparesGuardAndNumberOgres();
				start_new_game();
				lblMessages.setText("You are now entering a mysterious place...");
				frmDungeonKeep.setFocusable(true);
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

	}

	protected void start_new_game() {
		level = 1;
		niveis = new GameLevels();
		estado_jogo = new State(niveis.getLevel(level));
		estado_jogo.startEntities(guarda, numberOgres);

		jpGamePanel.estado_atual = estado_jogo;
		jpGamePanel.setEnabled(true);
		updateGameButtons();
		printGameGUI();
	}

	protected void preparesGuardAndNumberOgres() {
		String guardaEscolhido = (String) cbbGuardPersonality.getSelectedItem();
		if (isValid(tflNumberOfOgres.getText())) {
			if (Integer.parseInt(tflNumberOfOgres.getText()) < 6 && Integer.parseInt(tflNumberOfOgres.getText()) > 0) {
				numberOgres = Integer.parseInt(tflNumberOfOgres.getText());
			}
		}
		// predetermined rookie
		if (guardaEscolhido == "Drunken")
			guarda = 1;
		else if (guardaEscolhido == "Suspicious")
			guarda = 2;
	}

	protected void turn_on_vis_buttons() {
		btnUp.setVisible(true);
		btnDown.setVisible(true);
		btnLeft.setVisible(true);
		btnRight.setVisible(true);
		btnStay.setVisible(true);
	}

	private void initialize_GuardAndOgreContact() {
		initialize_OgreContact();
		initialize_GuardContact();
	}

	private void initialize_GuardContact() {
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(20, 50, 110, 24);
		frmDungeonKeep.getContentPane().add(lblGuardPersonality);
		JComboBox cbbGuardPersonality = new JComboBox();
		cbbGuardPersonality.setModel(new DefaultComboBoxModel(new String[] { "Rookie", "Drunken", "Suspicious" }));
		cbbGuardPersonality.setBounds(140, 50, 110, 22);
		frmDungeonKeep.getContentPane().add(cbbGuardPersonality);
	}

	private void initialize_OgreContact() {
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(20, 20, 110, 24);
		frmDungeonKeep.getContentPane().add(lblNumberOfOgres);
		tflNumberOfOgres = new JTextField();
		tflNumberOfOgres.setBounds(140, 22, 30, 20);
		frmDungeonKeep.getContentPane().add(tflNumberOfOgres);
		tflNumberOfOgres.setColumns(10);
	}

	private void initialize_frmDungeonKeep() {
		setUp_frmDungeonKeep();
		frmDungeonKeep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				turn_off_vis_buttons();
				switch_keys(e);
			}

		});

	}

	private void setUp_frmDungeonKeep() {
		frmDungeonKeep = new JFrame();
		frmDungeonKeep.setFocusable(true);
		frmDungeonKeep.getContentPane().setBackground(Color.GREEN);
		frmDungeonKeep.setTitle("Dungeon Keep");
		frmDungeonKeep.setBounds(100, 100, 510, 490);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);
	}

	protected void switch_keys(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if (btnLeft.isEnabled()) {
				fire_up_buttonLeft();
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (btnRight.isEnabled()) {
				fire_up_buttonRight();
			}
			break;
		case KeyEvent.VK_UP:
			if (btnUp.isEnabled()) {
				fire_up_buttonUp();
			}
			break;
		case KeyEvent.VK_DOWN:
			if (btnDown.isEnabled()) {
				fire_up_buttonDown();
			}
			break;
		case KeyEvent.VK_SPACE:
			if (btnStay.isEnabled()) {
				fire_up_buttonStay();
			}
			break;
		}
	}

	protected void turn_off_vis_buttons() {
		btnUp.setVisible(false);
		btnDown.setVisible(false);
		btnLeft.setVisible(false);
		btnRight.setVisible(false);
		btnStay.setVisible(false);
	}

	private void fire_up_buttonUp() {
		int newX = estado_jogo.calculateNewX(1, estado_jogo.hero.getX());
		int newY = estado_jogo.calculateNewY(1, estado_jogo.hero.getY());
		estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(), newX,
				newY);
		estado_jogo.hero.moveEntity(1);
		lblMessages.setText("Hero moved up.");
		updateGameLogic();
		updateGameButtons();
		printGameGUI();
	}

	private void fire_up_buttonLeft() {
		int newX = estado_jogo.calculateNewX(3, estado_jogo.hero.getX());
		int newY = estado_jogo.calculateNewY(3, estado_jogo.hero.getY());
		estado_jogo.updateEntity(estado_jogo.hero.getSprite(), estado_jogo.hero.getX(), estado_jogo.hero.getY(), newX,
				newY);
		estado_jogo.hero.moveEntity(3);
		lblMessages.setText("Hero moved left.");
		updateGameLogic();
		updateGameButtons();
		printGameGUI();
	}

	private void updateGameButtons() {
		btnStay.setEnabled(true);
		if(WON)
			return;
		int x = estado_jogo.hero.getX();
		int y = estado_jogo.hero.getY();
		setUpButtonsAccordingHero(x, y);

	}

	private void setUpButtonsAccordingHero(int x, int y) {
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

	private void updateGameLogicEndLevel() {
		if (++level <= niveis.getNumberOfLevels()) {
			lblMessages.setText("You won! Next Level.");
			estado_jogo = new State(niveis.getLevel(level));
			estado_jogo.startEntities(guarda, numberOgres);
		} else {
			printGameGUI();
			lblMessages.setText("You won the Game!");
			btnStay.setEnabled(false);
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
			frmDungeonKeep.setFocusable(false);
			WON = true;
		}
	}

	private void updateGameLogicContinue() {
		if (lost_game.booleanValue() == true) {
			updateGameLogicLost();
		} else
			updateGameButtons();
	}

	private void updateGameLogicLost() {
		printGameGUI();
		lblMessages.setText("You were caught! Game over.");
		btnStay.setEnabled(false);
		btnStay.setVisible(false);
		btnUp.setEnabled(false);
		btnUp.setVisible(false);
		btnDown.setEnabled(false);
		btnDown.setVisible(false);
		btnLeft.setEnabled(false);
		btnLeft.setVisible(false);
		btnRight.setEnabled(false);
		btnRight.setVisible(false);
		frmDungeonKeep.setFocusable(false);
	}

	private void updateGameLogic() {
		printGameGUI();
		Boolean lole = estado_jogo.updateBoard(lost_game);
		if (lole) { // won the game
			updateGameLogicEndLevel();
			return;
		} else
			updateGameLogicContinue();
		if (estado_jogo.checkIfLose()) // checking for lose
			updateGameLogicLost();
	}
}