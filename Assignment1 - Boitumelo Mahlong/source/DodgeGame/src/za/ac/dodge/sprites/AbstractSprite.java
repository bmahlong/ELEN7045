package za.ac.dodge.sprites;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
/**
 * All movable/immovable objects of the game will extend this class 
 * 
 * @author boitumelo
 *
 */
public abstract class AbstractSprite {

	private int dx = 0;
	private int dy = 0;
	private int x;
	private int y;
	private boolean visible=false;
	private Image image;

	
	public AbstractSprite(ImageIcon imageIcon, int x, int y){
		setImage(imageIcon.getImage());

		setX(x);
		setY(y - imageIcon.getIconHeight());					
	}
	
	/**
	 * @return the dx
	 */
	public int getDx() {
		return dx;
	}
	/**
	 * @param dx the dx to set
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}
	/**
	 * @return the dy
	 */
	public int getDy() {
		return dy;
	}
	/**
	 * @param dy the dy to set
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void move(){
		x+=dx;
		y+=dy;
	}
	
    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }  	
}
