package dkeep.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import dkeep.logic.State;

public class Gmap extends JPanel {

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
		} catch (IOException  e) {
			System.out.println("Could not load images.");
			e.printStackTrace();

		}

	}
	
	public void setEstadoJogo(State jogo){
		this.estado_atual = jogo;
		//estado_atual.printBoard();
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		char[][] tab = estado_atual.board;
		for(int i = 0; i < tab.length; i++)
			for(int j = 0; j < tab.length; j++){
				switch(tab[j][i]){
				case ' ':
					g.drawImage(imageGround,32*j , 32*i, 32, 32, (ImageObserver)this);
					break;
				case 'X':
					g.drawImage(imageWall,32*j , 32*i, 32, 32, (ImageObserver)this);
					break;
				case 'H':
					g.drawImage(imageHero,32*j , 32*i, 32, 32, (ImageObserver)this);
					break;
				case 'A':
					g.drawImage(imageHeroArmed,32*j , 32*i, 32, 32, (ImageObserver)this);
					break;
				case 'O':
					g.drawImage(imageOgre,32*j , 32*i, 32, 32, (ImageObserver)this);
					break;
				case '8':
					g.drawImage(imageOgreStunned,32*j , 32*i, 32, 32, (ImageObserver)this);
					break;
				case 'G':
					g.drawImage(imageGuard,32*j , 32*i, 32, 32, (ImageObserver)this);
					break;
				case 'g':
					g.drawImage(imageGuardSleeping,32*j , 32*i, 32, 32, (ImageObserver)this);
					break;
				case 'I':
					g.drawImage(imageDoorUn,32*j , 32*i, 32, 32, (ImageObserver)this);
					break;
				case 'S':
					g.drawImage(imageDoorOp,32*j , 32*i, 32, 32, (ImageObserver)this);
					break;
				case 'k':
					g.drawImage(imageKey,32*j , 32*i, 32, 32, (ImageObserver)this);
					break;
				case 'K':
					g.drawImage(imageHero,32*j , 32*i, 32, 32, (ImageObserver)this);
					break;
				case '*':
					g.drawImage(imageMassiveClub,32*j , 32*i, 32, 32, (ImageObserver)this);
					break;
				default:
					g.drawImage(imageWall,32*j , 32*i, 32, 32, (ImageObserver)this);
					break;
				}				
					
			}
		
		
		
		
	}

}
