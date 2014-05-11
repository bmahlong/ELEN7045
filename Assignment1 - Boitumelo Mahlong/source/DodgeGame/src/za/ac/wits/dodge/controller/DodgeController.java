package za.ac.wits.dodge.controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import javax.swing.Timer;

import za.ac.dodge.sprites.AbstractSprite;
import za.ac.wits.dodge.gui.DodgeGUI;
import za.ac.wits.dodge.gui.DodgeScene;
import za.ac.wits.dodge.utils.DodgeConfig;
import za.ac.wits.dodge.utils.GameState;

public class DodgeController implements ActionListener{
	
	private static DodgeGUI  dodge;
	private static DodgeConfig _config;
	private static Timer timer;

	public DodgeController(){
		_config = new DodgeConfig();
		dodge  = new DodgeGUI();
		dodge.createDodgeGui("The Dodge Game", new DodgeScene(_config));
		dodge.getDodgeScene().addKeyListener(new KeyEventManager());
		
		_config.setGameState(GameState.INITIALISED);		
	}
	
	public static void main(String[] args){
		intialiseDodge();
	}

	private static void intialiseDodge() {
		DodgeController dc = new DodgeController();
		timer = new Timer(_config.getTIMER_DELAY(),dc);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(_config.getGameSate() == GameState.PLAYING){				
			playDodge();
		}
		if(_config.getGameSate() == GameState.STOPPED){
			stop();
		}
		
	}

	/**
    * Start game 
    */
	public void start(){
		timer.start();
	}
	
	/**
	 * Stop game
	 */
	public void stop(){
		timer.stop();
	}

	
    private void playDodge() {
		movePlayers();
		rainFall();
		checkCollisions();
		
		DodgeScene dc = (DodgeScene)dodge.getDodgeScene();
		dc.repaint();
	}

    private void movePlayers() {
 		for(AbstractSprite gc: _config.getGroundCharacters()){
 			//ensure player/s never move off the game window
 			if(gc.getX() > _config.getB_WIDTH()){
 				gc.setX(_config.getB_WIDTH() - gc.getBounds().width);
 			}
 			if(gc.getX() < _config.getB_WIDTH() - _config.getB_WIDTH()){
 				gc.setX(0 + gc.getBounds().width);
 			}
 			
         	gc.move();
         }
 	} 
     
 	/**
 	 * Regenerate and move rain droplets as long as the game is playing 
 	 * 
 	 */
    private void rainFall() {

     	if(_config.getGameSate() == GameState.PLAYING){
     		Iterator<AbstractSprite> itrFo = _config.getFallingObjects().iterator();
     		int removed = 0;
     		
     		while(itrFo.hasNext()){
 				AbstractSprite fo = itrFo.next();
 				//if raindrop has reached the ground
 				if(fo.getY() == (_config.getB_HEIGHT() - fo.getBounds().height)){
 					fo.setVisible(false);
 				}
 				
 				if(fo.isVisible()){
 					fo.setY(fo.getY() + 1);
 					fo.move();
 				}else{
 					itrFo.remove();
 					removed+=1;
 				}
 			}
     		
     		//load same number of removed rain droplets
     		for(int y = 0; y < removed; y++){
     			((DodgeScene) dodge.getDodgeScene()).addRainDrop(y);
     		}   		
     	}
         
     }    

	 /**
	  * Check Collisions, when the visible raindrop and a player intersect the game is over  
	  */
    private void checkCollisions(){		
 		for (AbstractSprite gC: _config.getGroundCharacters()) {
 			 Rectangle r1 = gC.getBounds();
 			 for (AbstractSprite fO: _config.getFallingObjects()) {
 		         Rectangle r2 = fO.getBounds();
 		         if ((gC.isVisible() && fO.isVisible()) && r1.intersects(r2)) {		        	 
 		        	_config.setGameState(GameState.STOPPED); //game over	        	 
 		         }
 		     }
 		 }
 	}

 	
	/**
	 * 
	 * @author boitumelo
	 * 
	 * Class manages keyboard event to:
	 * 
	 * - start, pause and stop the game
	 * - move game characters / players dodging the rain drops
	 * 
	 */
	private class KeyEventManager extends KeyAdapter {
        
		public void keyReleased(KeyEvent e) {
        	for (AbstractSprite as: _config.getLeftMove().values()) {
        		as.setDx(0);  
        	}
        }
        
        public void keyPressed(KeyEvent e) {
        	if(e.isControlDown() && e.isAltDown()){
        		_config.setGameState(GameState.PLAYING);
        		start();
        	}else if(e.isControlDown() && e.isShiftDown()){
        		_config.setGameState(GameState.PAUSED);
        	} else  if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
        		_config.setGameState(GameState.STOPPED);
        		stop();
        	}
        	
        	if(!e.isControlDown() && _config.getGameSate() == GameState.PLAYING){
                processKeyPressEvent(e);
        	}
        }
        
        private void processKeyPressEvent(KeyEvent e){
        	AbstractSprite player;
        	player = _config.getLeftMove().get(String.valueOf(e.getKeyCode()));  
        	if(null != player){
        		player.setDx(-_config.getPLAYER_SPEED());
        	}
        	player = _config.getRightMove().get(String.valueOf(e.getKeyCode()));
        	if(null != player){
        		player.setDx(+_config.getPLAYER_SPEED());
        	}
        }
    }
}
