package za.ac.wits.dodge.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import za.ac.dodge.sprites.AbstractSprite;
import za.ac.wits.dodge.sprites.DodgePlayer;
import za.ac.wits.dodge.sprites.FallingObject;
import za.ac.wits.dodge.utils.DodgeConfig;
import za.ac.wits.dodge.utils.GameState;

/**
 * 
 * 
 * @author boitumelo
 *
 * The class loads all game characters thus setting the scene for game to be started 
 *
 */
public class DodgeScene extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DodgeConfig _config;
	
	public DodgeScene(DodgeConfig config) {
		
		_config = config;
		
		initConsole();
		loadPlayers();
		loadRainDrops();
		
	}


	private void initConsole(){
		
		setDoubleBuffered(true);
		setFocusable(true);
		setBackground(Color.DARK_GRAY);
		
		//get window width and height form the toolkit
		_config.setB_HEIGHT(Toolkit.getDefaultToolkit().getScreenSize().height); //window height subtract the window tile bar height
		_config.setB_HEIGHT((_config.getB_HEIGHT() / 4) * 3 + ((_config.getB_HEIGHT() / 4) / 2)); //set dodge ground level i.e. visible bottom of screen
		_config.setB_WIDTH(Toolkit.getDefaultToolkit().getScreenSize().width);
	}	
	

	//initial load game player/s and they default control keys
    private void loadPlayers() {
		for(int i = 0; i < _config.getNO_PLAYERS(); ++i){
			ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("img/rainman.png"));
			AbstractSprite player = new DodgePlayer(img,((_config.getB_WIDTH()/ 4) / _config.getNO_PLAYERS()), _config.getB_HEIGHT() );
			player.setVisible(true);
			_config.getGroundCharacters().add(i, player);
			
			_config.getLeftMove().put(String.valueOf(KeyEvent.VK_LEFT), player);		
			_config.getRightMove().put(String.valueOf(KeyEvent.VK_RIGHT), player);
		}
	}
    
    //initial loading of all raindrops
	private void loadRainDrops() {
		for (int i=0; i< _config.getNO_OF_FALLING_OBJECTS(); i++ ) {
			addRainDrop(i);
        }
	}

	public void addRainDrop(int i){
		try {		
			//ensure raindrops always start at the random top quarter positions
			int newX = Random.class.newInstance().nextInt(_config.getB_WIDTH()); 
			int newY = Random.class.newInstance().nextInt((_config.getB_HEIGHT()) / 4);		
			
			ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("img/raindrop.png"));
			_config.getFallingObjects().add(i, new FallingObject(img,newX,newY, true));
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        //---
        if(_config.getGameSate() == GameState.STOPPED){
            
        	String msg = " Game Over ";
            Font small = new Font("Helvetica", Font.BOLD, 25);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.red);
            g.setFont(small);
            g.drawString(msg, (getWidth() - metr.stringWidth(msg)) / 2, getHeight() / 2);
            
        }else if(_config.getGameSate() == GameState.PLAYING){
        	
            String msg = "Press Ctrl + Shift to pause game";
            Font small = new Font("Helvetica", Font.BOLD, 15);
            g.setColor(Color.green);
            g.setFont(small);
            g.drawString(msg, 10, 15);
                
            drawGameCharacters(g2d, _config.getFallingObjects());
            drawGameCharacters(g2d, _config.getGroundCharacters());
                        
        }else if(_config.getGameSate() == GameState.PAUSED){
            
        	String msg = "Game paused. Press Ctrl + Alt to start playing the game";
            Font small = new Font("Helvetica", Font.BOLD, 20);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.orange);
            g.setFont(small);
            g.drawString(msg, (getWidth() - metr.stringWidth(msg)) / 2, getHeight() / 2);

            drawGameCharacters(g2d, _config.getFallingObjects());
            drawGameCharacters(g2d, _config.getGroundCharacters());

        }else {
        	
        	String msg = "Press Ctrl + Alt to start playing the game";
            Font small = new Font("Helvetica", Font.BOLD, 25);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.green);
            g.setFont(small);
            g.drawString(msg, (getWidth() - metr.stringWidth(msg)) / 2, getHeight() / 2);
            
            drawGameCharacters(g2d, _config.getFallingObjects());
            drawGameCharacters(g2d, _config.getGroundCharacters());
        }
        //---
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    private void drawGameCharacters(Graphics2D g2d, LinkedList<AbstractSprite> characters) {
        for(AbstractSprite as: characters){
        	g2d.drawImage(as.getImage(), as.getX(), as.getY(), this);       
        }
	}
}
