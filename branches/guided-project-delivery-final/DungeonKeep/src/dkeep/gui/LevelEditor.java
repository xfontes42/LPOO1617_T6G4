package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.Border;

import dkeep.gameLevels.GameLevels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class LevelEditor extends JFrame {
	JFrame parent;
	JFrame pai;
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

	public void loadImages() {
		try {
			loadIMGS();
		} catch (IOException e) {
			System.out.println("Could not load images.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadIMGS() throws Exception {
		imageOgre = ImageIO.read(new File("src/resources/OgreWithClub.png"));
		imageGuard = ImageIO.read(new File("src/resources/Guard.png"));
		imageGround = ImageIO.read(new File("src/resources/Ground.png"));
		imageHero = ImageIO.read(new File("src/resources/Hero.png"));
		imageWall = ImageIO.read(new File("src/resources/Wall.png"));
		imageDoorUn = ImageIO.read(new File("src/resources/Door.png"));
		imageKey = ImageIO.read(new File("src/resources/Key.png"));
	}

	public LevelEditor(JFrame parent, int nrows, int ncols) {
		this.ncols = ncols;
		this.nrows = nrows;
		this.parent = parent;
		this.pai = this;
		loadImages();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		preparesBoardAndBounds();
		int buttonX = width - 10 - buttonW; // buttons and their coordinates
		this.buttonX = buttonX;
		putsOutSaveButton();
		setButtonsOnRight();
		pnlBoardFunc();

	}

	private void putsOutSaveButton() {
		btnSaveLevel = new JButton("Save");
		btnSaveLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkOutLevel(); // guardar o nivel caso seja aceitavel
				dispose();
			}
		});
		btnSaveLevel.setBounds(buttonX, 10 + 7 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnSaveLevel);
	}

	protected void checkOutLevel() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				switch (to_save[j][i]) {
				case ' ':
					bool1 = true;
					break;
				case 'X':
					bool2 = true;
					break;
				case 'H':
					bool3 = true;
					break;
				case 'O':
					bool4 = true;
					break;
				case 'k':
					bool5 = true;
					break;
				case 'I':
					bool6 = true;
					break;
				}
			}
		}
		if (bool1 && bool2 && bool3 && bool4 && bool5 && bool6) {
			GameLevels jogos = new GameLevels(); // good level
			jogos.addLevelToFile(ncols, nrows, to_save);

		} else {
			JOptionPane.showMessageDialog(pnlBoard, "BAD LEVEL");
		}
	}

	private void setButtonsOnRight() {
		setHeroButton();
		setOgreButton();
		setKeyButton();
		setDoorButton();
		setWallButton();
		setGroundButton();
		setExitButton();
	}

	private void setExitButton() {
		JButton btnExitEditor = new JButton("Exit");
		btnExitEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExitEditor.setBounds(buttonX, 10 + 8 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnExitEditor);
	}

	private void setGroundButton() {
		btnGround = new JButton("Ground");
		btnGround.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChosenTile = ' ';
			}
		});
		btnGround.setBounds(buttonX, 10 + 5 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnGround);
	}

	private void setWallButton() {
		btnWall = new JButton("Wall");
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChosenTile = 'X';
			}
		});
		btnWall.setBounds(buttonX, 10 + 4 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnWall);
	}

	private void setDoorButton() {
		btnDoor = new JButton("Door");
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChosenTile = 'I';
			}
		});
		btnDoor.setBounds(buttonX, 10 + 3 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnDoor);
	}

	private void setKeyButton() {
		btnKey = new JButton("Key");
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChosenTile = 'k';
			}
		});
		btnKey.setBounds(buttonX, 10 + 2 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnKey);
	}

	private void setOgreButton() {
		btnOgre = new JButton("Ogre");
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChosenTile = 'O';
			}
		});
		btnOgre.setBounds(buttonX, 10 + 1 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnOgre);
	}

	private void setHeroButton() {
		btnHero = new JButton("Hero");
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChosenTile = 'H';
			}
		});
		btnHero.setBounds(buttonX, 10 + 0 * (10 + buttonH), buttonW, buttonH);
		getContentPane().add(btnHero);
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
				System.out.println("(" + x + "," + y + ") = " + ChosenTile);
				System.out.print(ncols + " " + nrows + "\n");
				int deltaX = (640 / ncols);
				int deltaY = (640 / nrows);
				pnlBoard.getGraphics().drawImage(getFromTile(ChosenTile), x * deltaX, y *deltaY,
						deltaX, deltaY, pnlBoard);
				//revalidate();
				//pai.repaint();
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
