package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class LevelEditor extends JFrame {
	JFrame parent;
	int height, width;		//window size
	int buttonW, buttonH;	//button size
	
	public LevelEditor(JFrame parent) {
		int boardX=10, boardY=10;
		
		buttonW = 75;
		buttonH = 23;
		height = 20 + 32 * boardY;
		width = 30 + 32 * boardX + buttonW;
		setResizable(false);
		super.setBounds(0, 0, width, height);
		this.parent = parent;
		getContentPane().setLayout(null);
		
		JButton btnExitEditor = new JButton("Exit");
		btnExitEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		//button coordinates
		int buttonX = width-10-buttonW;
		
		btnExitEditor.setBounds(buttonX, height - 10 - buttonH, buttonW, buttonH);
		getContentPane().add(btnExitEditor);

		JButton btnSaveLevel = new JButton("Save");
		btnSaveLevel.setBounds(buttonX, height - 2 * (10 - buttonH), buttonW, buttonH);
		getContentPane().add(btnSaveLevel);
		
		JButton btnHero = new JButton("Hero");
		btnHero.setBounds(buttonX, 10, buttonW, buttonH);
		getContentPane().add(btnHero);
		
		JButton btnOgre = new JButton("Ogre");
		btnOgre.setBounds(buttonX, 10+10+buttonH, buttonW, buttonH);
		getContentPane().add(btnOgre);
		
		JButton btnKey = new JButton("Key");
		btnKey.setBounds(buttonX, 10+2*(10+buttonH), buttonW, buttonH);
		getContentPane().add(btnKey);
		
		JButton btnDoor = new JButton("Door");
		btnDoor.setBounds(buttonX, 10+3*(10+buttonH), buttonW, buttonH);
		getContentPane().add(btnDoor);
		
		JButton btnWall = new JButton("Wall");
		btnWall.setBounds(buttonX, 10+4*(10+buttonH), buttonW, buttonH);
		getContentPane().add(btnWall);
		
		JButton btnGround = new JButton("Ground");
		btnGround.setBounds(buttonX, 10+5*(10+buttonH), buttonW, buttonH);
		getContentPane().add(btnGround);
		
		JPanel pnlBoard = new JPanel();
		pnlBoard.setBounds(10, 10, 32*boardX, 32*boardY);
		getContentPane().add(pnlBoard);
		
		
	}
	
	@Override
	public void dispose(){
		super.dispose();
		this.parent.setVisible(true);
	}
}
