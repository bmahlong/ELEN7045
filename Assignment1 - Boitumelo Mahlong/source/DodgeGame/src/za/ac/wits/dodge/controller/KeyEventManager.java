package za.ac.wits.dodge.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import za.ac.dodge.sprites.AbstractSprite;
import za.ac.wits.dodge.utils.DodgeConfig;
import za.ac.wits.dodge.utils.GameState;

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
public class KeyEventManager extends KeyAdapter {
    
	DodgeConfig _config;
		
	public KeyEventManager(DodgeConfig _config) {
		super();
		this._config = _config;
	}

	public void keyReleased(KeyEvent e) {
    	for (AbstractSprite as: _config.getLeftMove().values()) {
    		as.setDx(0);  
    	}
    }
    
    public void keyPressed(KeyEvent e) {
    	if(e.isControlDown() && e.isAltDown()){
    		_config.setGameState(GameState.PLAYING);
    //		start();
    	}else if(e.isControlDown() && e.isShiftDown()){
    		_config.setGameState(GameState.PAUSED);
    	} else  if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
    		_config.setGameState(GameState.STOPPED);
    	//	stop();
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
