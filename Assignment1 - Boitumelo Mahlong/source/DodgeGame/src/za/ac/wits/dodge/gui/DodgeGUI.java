package za.ac.wits.dodge.gui;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class DodgeGUI {
	
	private JComponent dodgescene = null;
	
	public void createDodgeGui(String title,JComponent dodgescene){
		
		JFrame dodgeFrame = new JFrame(title);
		dodgeFrame.pack();
		dodgeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dodgeFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		dodgeFrame.setLocationRelativeTo(null);
		dodgeFrame.setResizable(false);
		
		dodgeFrame.add(dodgescene);
		this.dodgescene = dodgescene;
		
		dodgeFrame.setVisible(true);
		
	}

	public JComponent getDodgeScene() {
		return dodgescene;
	}
	
}
