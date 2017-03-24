package dkeep.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.*;

import dkeep.logic.State;

public class Gmap extends JPanel{

	private BufferedImage imageOgre;
	private BufferedImage imageOgreStunned;
	private BufferedImage imageGuard;
	private BufferedImage imageGuardSleeping;
	private BufferedImage imageGround;
	private BufferedImage imageHero;
	private BufferedImage imageHeroArmed;
	private BufferedImage imageWall;
	private BufferedImage imageDoorUn;
	private BufferedImage imageDoorOp;
	private BufferedImage imageKey;
	private BufferedImage imageMassiveClub;
	public State estado_atual = new State();
	private int ncols;
	private int nrows;
	private Map<Character, BufferedImage> mapeamento = new HashMap<Character, BufferedImage>();

	public Gmap() {
		super();
		
		try {
			imageOgre = ImageIO.read(new File("src/resources/OgreWithClub.png"));
			imageOgreStunned = ImageIO.read(new File("src/resources/OgreStunned.png"));
			imageGuard = ImageIO.read(new File("src/resources/Guard.png"));
			imageGuardSleeping = ImageIO.read(new File("src/resources/GuardSleeping.png"));
			imageGround = ImageIO.read(new File("src/resources//Ground.png"));
			imageHero = ImageIO.read(new File("src/resources/Hero.png"));
			imageHeroArmed = ImageIO.read(new File("src/resources/ArmedHero.png"));
			imageWall = ImageIO.read(new File("src/resources/Wall.png"));
			imageDoorUn = ImageIO.read(new File("src/resources/Door.png"));
			imageDoorOp = ImageIO.read(new File("src/resources/DoorOpen.png"));
			imageKey = ImageIO.read(new File("src/resources/Key.png"));
			imageMassiveClub = ImageIO.read(new File("src/resources/Blow.png"));
		} catch (IOException e) {
			System.out.println("Could not load images.");
			e.printStackTrace();

		}
		prepareMap();
	}


	public void setEstadoJogo(State jogo) {
		this.estado_atual = jogo;
		
	}

	@Override
	public void paintComponent(Graphics g) { //improve by using o this.getBbounds()
		char[][] tab = estado_atual.board;
		int ncols = estado_atual.board[0].length;
		int nrows =	estado_atual.board.length;
		this.ncols = ncols;
		this.nrows = nrows;
		for (int i = 0; i < ncols; i++)
			for (int j = 0; j < nrows; j++) {
				paintRespective(g, tab[j][i], j,i);
			}

	}

	private void paintRespective(Graphics g, char c, int j, int i) {
		if(!mapeamento.containsKey(c))
			g.drawImage(imageWall, (320/nrows) * j, (320/ncols) * i, (320/nrows) ,(320/ncols), (ImageObserver) this);
		else g.drawImage((BufferedImage)mapeamento.get(c), (320/nrows) * j, (320/ncols) * i, (320/nrows) ,(320/ncols), (ImageObserver) this);
	}

	private void prepareMap() {
		mapeamento.put(' ', imageGround);
		mapeamento.put('X', imageWall);
		mapeamento.put('H', imageHero);
		mapeamento.put('A', imageHeroArmed);
		mapeamento.put('O', imageOgre);
		mapeamento.put('8', imageOgreStunned);
		mapeamento.put('G', imageGuard);
		mapeamento.put('g', imageGuardSleeping);
		mapeamento.put('I', imageDoorUn);
		mapeamento.put('S', imageDoorOp);
		mapeamento.put('k', imageKey);
		mapeamento.put('K', imageHero);
		mapeamento.put('*', imageMassiveClub);	
	}


}
