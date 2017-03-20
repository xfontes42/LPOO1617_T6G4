package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LevelEditor extends JFrame {
	JFrame parent;
	
	public LevelEditor(JFrame parent) {
		this.parent = parent;
		getContentPane().setLayout(null);
		
		JButton btnExitEditor = new JButton("Exit Editor");
		btnExitEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExitEditor.setBounds(335, 227, 89, 23);
		getContentPane().add(btnExitEditor);
	}
	
	@Override
	public void dispose(){
		super.dispose();
		this.parent.setVisible(true);
	}

}
