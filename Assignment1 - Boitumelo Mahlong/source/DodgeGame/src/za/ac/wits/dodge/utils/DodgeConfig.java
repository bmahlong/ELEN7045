package za.ac.wits.dodge.utils;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import za.ac.dodge.sprites.AbstractSprite;

/**
 * 
 * 
 * @author boitumelo
 *
 *  Class for keeping dodge game configuration
 * 
 * 	TIMER_DELAY - Game speed. The timer delay determines the overall game reload speed which affect how fast the rain drops
 *
 *	B_HEIGHT, B_WIDTH - The fields contain the size of the window game. If the user would like to reset the game size they will specify the desired size here
 *
 *  MOVE_LEFT<Event.KEYCODE, AbtractSprite>,  MOVE_RIGHT<Event.KEYCODE, AbtractSprite> - Used to configure the player movement. The two fields will contain the key codes for moving the player left and right
 *  
 *  FALLING_OBJECTS - The field contains the no. of falling objects shown at any point in time
 *  
 *  NO_PLAYERS - The field hold the no of players allow per game sessions
 *  
 *  GAME_STATE - Keeps the current game state
 */
public class DodgeConfig {

	private int TIMER_DELAY = 5; //default to five.
	//no of falling objects at any given point i.e. rain drops, hail storm umbrella's etc.
	private int NO_OF_FALLING_OBJECTS = 35;
    private int NO_PLAYERS = 1; //default to 1 i.e. a single player
    private int PLAYER_SPEED = 5;	
    private int B_HEIGHT, B_WIDTH;
    
	private LinkedHashMap<String , AbstractSprite> leftMove = new LinkedHashMap<>();
	private LinkedHashMap<String , AbstractSprite> rightMove= new LinkedHashMap<>();
	private LinkedList<AbstractSprite> fallingObjects = new LinkedList<>();
	private LinkedList<AbstractSprite> groundCharacters = new LinkedList<>();
	private GameState gameState;
    
    public int getTIMER_DELAY() {
		return TIMER_DELAY;
	}
	
	public void setTIMER_DELAY(int tIMER_DELAY) {
		TIMER_DELAY = tIMER_DELAY;
	}
	
	public int getB_HEIGHT() {
		return B_HEIGHT;
	}
	
	public void setB_HEIGHT(int b_HEIGHT) {
		B_HEIGHT = b_HEIGHT;
	}
	
	public int getB_WIDTH() {
		return B_WIDTH;
	}
	
	public void setB_WIDTH(int b_WIDTH) {
		B_WIDTH = b_WIDTH;
	}
		
	public int getNO_OF_FALLING_OBJECTS() {
		return NO_OF_FALLING_OBJECTS;
	}
	
	public void setNO_OF_FALLING_OBJECTS(int fALLING_OBJECTS) {
		NO_OF_FALLING_OBJECTS = fALLING_OBJECTS;
	}
	
	public int getNO_PLAYERS() {
		return NO_PLAYERS;
	}
	
	public void setNO_PLAYERS(int nO_PLAYERS) {
		NO_PLAYERS = nO_PLAYERS;
	}

	public int getPLAYER_SPEED() {
		return PLAYER_SPEED;
	}

	public void setPLAYER_SPEED(int pLAYER_SPEED) {
		PLAYER_SPEED = pLAYER_SPEED;
	}

	public GameState getGameSate() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public LinkedHashMap<String, AbstractSprite> getLeftMove() {
		return leftMove;
	}

	public void setLeftMove(LinkedHashMap<String, AbstractSprite> leftMove) {
		this.leftMove = leftMove;
	}

	public LinkedHashMap<String, AbstractSprite> getRightMove() {
		return rightMove;
	}

	public void setRightMove(LinkedHashMap<String, AbstractSprite> rightMove) {
		this.rightMove = rightMove;
	}

	public LinkedList<AbstractSprite> getFallingObjects() {
		return fallingObjects;
	}

	public void setFallingObjects(LinkedList<AbstractSprite> fallingObjects) {
		this.fallingObjects = fallingObjects;
	}

	public LinkedList<AbstractSprite> getGroundCharacters() {
		return groundCharacters;
	}

	public void setGroundCharacters(LinkedList<AbstractSprite> groundCharacters) {
		this.groundCharacters = groundCharacters;
	}   
	
}
