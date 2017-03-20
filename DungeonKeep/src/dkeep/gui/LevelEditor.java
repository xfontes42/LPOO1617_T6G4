package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class LevelEditor extends JFrame {
	JFrame parent;
	
	public LevelEditor(JFrame parent) {
		setResizable(false);
		this.parent = parent;
		getContentPane().setLayout(null);
		
		JButton btnExitEditor = new JButton("Exit Editor");
		btnExitEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExitEditor.setBounds(395, 287, 89, 23);
		getContentPane().add(btnExitEditor);
		
		JButton btnSaveLevel = new JButton("Save Level");
		btnSaveLevel.setBounds(395, 253, 89, 23);
		getContentPane().add(btnSaveLevel);
		
		JButton btnHero = new JButton("Hero");
		btnHero.setBounds(395, 11, 89, 23);
		getContentPane().add(btnHero);
		
		JButton btnOgre = new JButton("Ogre");
		btnOgre.setBounds(395, 45, 89, 23);
		getContentPane().add(btnOgre);
		
		JButton btnKey = new JButton("Key");
		btnKey.setBounds(395, 79, 89, 23);
		getContentPane().add(btnKey);
		
		JButton btnDoor = new JButton("Door");
		btnDoor.setBounds(395, 113, 89, 23);
		getContentPane().add(btnDoor);
		
		JButton btnWall = new JButton("Wall");
		btnWall.setBounds(395, 145, 89, 23);
		getContentPane().add(btnWall);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 239, 239);
		getContentPane().add(panel);
		
		JButton btnGround = new JButton("Ground");
		btnGround.setBounds(395, 179, 89, 23);
		getContentPane().add(btnGround);
	}
	
	@Override
	public void dispose(){
		super.dispose();
		this.parent.setVisible(true);
	}
}
