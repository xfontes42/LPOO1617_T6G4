package dkeep.gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameWindow {

	private JFrame frmDungeonKeep;
	private JTextField tflNumberOfOgres;
	private JButton btnUp, btnDown, btnRight, btnLeft, btnStay;
	private JLabel lblMessages;
	private JTextPane tpnGameField;

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
		cbbGuardPersonality.setModel(new DefaultComboBoxModel(new String[] { "Rookie", "Drunken", "Suspicious" }));
		cbbGuardPersonality.setBounds(140, 50, 110, 22);
		frmDungeonKeep.getContentPane().add(cbbGuardPersonality);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String guardaEscolhido = (String) cbbGuardPersonality.getSelectedItem();
				int numberOgres = 1;
				if (isValid(tflNumberOfOgres.getText())) {
					if (Integer.parseInt(tflNumberOfOgres.getText()) < 6
							&& Integer.parseInt(tflNumberOfOgres.getText()) > 0) {
						numberOgres = Integer.parseInt(tflNumberOfOgres.getText());
					}
				}

				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				btnStay.setEnabled(true);

				char[][] lole = { { 'X', 'I', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
						{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'H', 'X' },
						{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						{ 'X', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						{ 'X', 'k', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
						{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }} ;
				
				String jogo ="";
				for(int i = 0; i < 10 ; i++){
					for(int j = 0; j < 10; j++)
						jogo += lole[j][i] + " ";
					jogo += "\n";
				}
						
				tpnGameField.setText(jogo);
				lblMessages.setText("You are now entering a mYsterious place...");

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

		tpnGameField = new JTextPane();
		tpnGameField.setFont(new Font("monospaced", Font.PLAIN, 22));
		tpnGameField.setBounds(20, 110, 270, 300);
		frmDungeonKeep.getContentPane().add(tpnGameField);

		btnUp = new JButton("Up");
		btnUp.setBounds(375, 150, 66, 24);
		frmDungeonKeep.getContentPane().add(btnUp);
		btnUp.setEnabled(false);

		btnDown = new JButton("Down");
		btnDown.setBounds(375, 224, 66, 25);
		frmDungeonKeep.getContentPane().add(btnDown);
		btnDown.setEnabled(false);

		btnLeft = new JButton("Left");
		btnLeft.setBounds(334, 188, 70, 25);
		frmDungeonKeep.getContentPane().add(btnLeft);
		btnLeft.setEnabled(false);

		btnRight = new JButton("Right");
		btnRight.setBounds(414, 188, 70, 25);
		frmDungeonKeep.getContentPane().add(btnRight);
		btnRight.setEnabled(false);

		btnStay = new JButton("Stay");
		btnStay.setBounds(375, 260, 66, 25);
		frmDungeonKeep.getContentPane().add(btnStay);
		btnStay.setEnabled(false);

		lblMessages = new JLabel("Welcome to our game!");
		lblMessages.setBounds(20, 420, 300, 20);
		frmDungeonKeep.getContentPane().add(lblMessages);
	}
}
