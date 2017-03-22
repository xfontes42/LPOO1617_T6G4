package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.Border;

import dkeep.logic.GameLevels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class LevelEditor extends JFrame {
	JFrame parent;
	int height, width; // window size
	int buttonW, buttonH; // button size
	char ChosenTile = 'X';
	char[][] to_save;
	private JPanel pnlBoard = new JPanel();
	private JButton btnSaveLevel = new JButton("Save");
	private JButton btnHero = new JButton("Hero");
	private JButton btnOgre = new JButton("Ogre");
	private JButton btnKey = new JButton("Key");
	private JButton btnDoor = new JButton("Door");
	private JButton btnWall = new JButton("Wall");
	private JButton btnGround = new JButton("Ground");

	private BufferedImage imageOgre;
	private BufferedImage imageGuard;
	private BufferedImage imageGround;
	private BufferedImage imageHero;
	private BufferedImage imageWall;
	private BufferedImage imageDoorUn;
	private BufferedImage imageKey;

	private boolean bool1 = false;
	private boolean bool2 = false;
	private boolean bool3 = false;
	private boolean bool4 = false;
	private boolean bool5 = false;
	private boolean bool6 = false;
	
	private int ncols, nrows;
	private int buttonX;

	public void loadImages(){
		try {
			imageOgre = ImageIO.read(new File("src/resources/OgreWithClub.png"));
			imageGuard = ImageIO.read(new File("src/resources/Guard.png"));
			imageGround = ImageIO.read(new File("src/resources//Ground.png"));
			imageHero = ImageIO.read(new File("src/resources/Hero.png"));
			imageWall = ImageIO.read(new File("src/resources/Wall.png"));
			imageDoorUn = ImageIO.read(new File("src/resources/Door.png"));
			imageKey = ImageIO.read(new File("src/resources/Key.png"));

		} catch (IOException e) {
			System.out.println("Could not load images.");
			e.printStackTrace();

		}
	}
	
	public LevelEditor(JFrame parent, int nrows, int ncols) {
		this.ncols = ncols;
		this.nrows = nrows;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// int boardX=10, boardY=10;
		
		
		preparesBoardAndBounds();

		
		// buttons and their coordinates
		int buttonX = width - 10 - buttonW;
		this.buttonX = buttonX;


		btnSaveLevel = new JButton("Save");
		btnSaveLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// guardar o nivel caso seja aceitavel
				
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++){
						switch(to_save[j][i]){
						case ' ' : bool1 = true; break;
						case 'X' : bool2 = true; break;
						case 'H' : bool3 = true; break;
						case 'O' : bool4 = true; break;
						case 'k' : bool5 = true; break;
						case 'I' : bool6 = true; break;
						}
					}
				}
				if(bool1 && bool2 && bool3 && bool4 && bool5 && bool6){
					//good level
					GameLevels jogos = new GameLevels();
					jogos.addLevelToFile(ncols,nrows,to_save);
					
					
					
				}
				else {
					JOptionPane.showMessageDialog(pnlBoard, "BAD LEVEL");
					
				}
				// for(int i = 0; i < 5; i++){
				// for(int j = 0; j < 5; j++)
				// System.out.print(to_save[j][i]);
				// System.out.println();
				// }
				dispose();
			}
		});
		btnSaveLevel.setBounds(buttonX, 10 + 7 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnSaveLevel);

		setButtonsOnRight();

		pnlBoardFunc();

	}

	private void setButtonsOnRight() {
		btnHero = new JButton("Hero");
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChosenTile = 'H';
			}
		});
		btnHero.setBounds(buttonX, 10 + 0 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnHero);

		btnOgre = new JButton("Ogre");
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChosenTile = 'O';
			}
		});
		btnOgre.setBounds(buttonX, 10 + 1 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnOgre);

		btnKey = new JButton("Key");
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChosenTile = 'k';
			}
		});
		btnKey.setBounds(buttonX, 10 + 2 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnKey);

		btnDoor = new JButton("Door");
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChosenTile = 'I';
			}
		});
		btnDoor.setBounds(buttonX, 10 + 3 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnDoor);

		btnWall = new JButton("Wall");
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChosenTile = 'X';
			}
		});
		btnWall.setBounds(buttonX, 10 + 4 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnWall);

		btnGround = new JButton("Ground");
		btnGround.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChosenTile = ' ';
			}
		});
		btnGround.setBounds(buttonX, 10 + 5 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnGround);
		
		
		JButton btnExitEditor = new JButton("Exit");
		btnExitEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExitEditor.setBounds(buttonX, 10 + 8 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnExitEditor);
		
	}

	private void preparesBoardAndBounds() {
		to_save = new char[ncols][nrows];
		for (int i = 0; i < ncols; i++)
			for (int j = 0; j < nrows; j++)
				to_save[i][j] = 'X';

		

		buttonW = 75;
		buttonH = 23;
		height = 60 + 64 * 10;
		width = 30 + 64 * 10 + buttonW;
		setResizable(false);
		super.setBounds(0, 0, width, height);
		this.parent = parent;
		getContentPane().setLayout(null);
		
	}

	private void pnlBoardFunc() {
		pnlBoard = new JPanel();
		pnlBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int x = arg0.getX() / (640 / ncols);
				int y = arg0.getY() / (640 / nrows);
				
				to_save[x][y] = ChosenTile;
				pnlBoard.getGraphics().drawImage(getFromTile(ChosenTile), x * (640 / ncols), y * (640 / nrows),
						(640 / ncols), (640 / nrows), null);

			}
		});
		pnlBoard.setBounds(10, 10, 640, 640);
		pnlBoard.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(pnlBoard);
		
	}

	@Override
	public void dispose() {
		super.dispose();
		this.parent.setVisible(true);

	}

	public BufferedImage getFromTile(char tile) {
		switch (tile) {
		case ' ':
			return imageGround;
		case 'X':
			return imageWall;
		case 'H':
			return imageHero;
		case 'O':
			return imageOgre;
		case 'G':
			return imageGuard;
		case 'I':
			return imageDoorUn;
		case 'k':
			return imageKey;
		default:
			return imageWall;
		}
	}
}
