package dodge.startup;

import org.junit.Test;

import za.ac.wits.dodge.gui.DodgeGUI;

public class GUITest {

	
	@Test
	public void initGUI(){
		DodgeGUI du = new DodgeGUI();
		
		assert(du != null);
	}
	
}
