package za.ac.wits.dodge.sprites;

import javax.swing.ImageIcon;

import za.ac.dodge.sprites.AbstractSprite;

public class FallingObject extends AbstractSprite {

	public FallingObject(ImageIcon img, int x, int y) {
		super(img, x, y);
	}

	public FallingObject(ImageIcon img, int newX, int newY, boolean visible) {
		super(img, newX, newY);
		setVisible(visible);
	}

}
