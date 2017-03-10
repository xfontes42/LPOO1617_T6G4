package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;

public class GameWindow {

	private JFrame frmDungeonKeep;
	private JTextField tflNumberOfOgres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
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
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDungeonKeep = new JFrame();
		frmDungeonKeep.setTitle("Dungeon Keep");
		frmDungeonKeep.setBounds(100, 100, 510, 490);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);
		
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
		cbbGuardPersonality.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		cbbGuardPersonality.setBounds(140, 50, 110, 22);
		frmDungeonKeep.getContentPane().add(cbbGuardPersonality);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(345, 30, 120, 25);
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(348, 415, 117, 25);
		frmDungeonKeep.getContentPane().add(btnExit);
		
		JTextPane tpnGameField = new JTextPane();
		tpnGameField.setBounds(20, 110, 300, 300);
		frmDungeonKeep.getContentPane().add(tpnGameField);
		
		JButton btnNewButton = new JButton("Up");
		btnNewButton.setBounds(375, 150, 66, 24);
		frmDungeonKeep.getContentPane().add(btnNewButton);
		
		JButton btnDown = new JButton("Down");
		btnDown.setBounds(375, 224, 66, 25);
		frmDungeonKeep.getContentPane().add(btnDown);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setBounds(334, 188, 70, 25);
		frmDungeonKeep.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setBounds(414, 188, 70, 25);
		frmDungeonKeep.getContentPane().add(btnRight);
		
		JButton btnStay = new JButton("Stay");
		btnStay.setBounds(375, 260, 66, 25);
		frmDungeonKeep.getContentPane().add(btnStay);
		
		JLabel lblMessages = new JLabel("Welcome to our game!");
		lblMessages.setBounds(20, 420, 300, 20);
		frmDungeonKeep.getContentPane().add(lblMessages);
	}
}
