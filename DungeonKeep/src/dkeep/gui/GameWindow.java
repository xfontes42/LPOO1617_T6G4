package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;

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
		frmDungeonKeep.setBounds(100, 100, 510, 396);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(24, 39, 143, 24);
		frmDungeonKeep.getContentPane().add(lblNumberOfOgres);
		
		tflNumberOfOgres = new JTextField();
		tflNumberOfOgres.setBounds(162, 42, 59, 19);
		frmDungeonKeep.getContentPane().add(tflNumberOfOgres);
		tflNumberOfOgres.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(24, 92, 143, 15);
		frmDungeonKeep.getContentPane().add(lblGuardPersonality);
		
		JComboBox cbbGuardPersonality = new JComboBox();
		cbbGuardPersonality.setBounds(162, 87, 32, 24);
		frmDungeonKeep.getContentPane().add(cbbGuardPersonality);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(348, 39, 117, 25);
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(348, 315, 117, 25);
		frmDungeonKeep.getContentPane().add(btnExit);
		
		JTextPane tpnGameField = new JTextPane();
		tpnGameField.setBounds(35, 139, 279, 212);
		frmDungeonKeep.getContentPane().add(tpnGameField);
		
		JButton btnNewButton = new JButton("Up");
		btnNewButton.setBounds(382, 161, 59, 24);
		frmDungeonKeep.getContentPane().add(btnNewButton);
		
		JButton btnDown = new JButton("Down");
		btnDown.setBounds(348, 276, 117, 25);
		frmDungeonKeep.getContentPane().add(btnDown);
		
		JButton btnLeft = new JButton("Left");
		btnLeft.setBounds(348, 139, 117, 25);
		frmDungeonKeep.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("Right");
		btnRight.setBounds(348, 239, 117, 25);
		frmDungeonKeep.getContentPane().add(btnRight);
		
		JButton btnStay = new JButton("Stay");
		btnStay.setBounds(348, 223, 117, 25);
		frmDungeonKeep.getContentPane().add(btnStay);
	}
}
