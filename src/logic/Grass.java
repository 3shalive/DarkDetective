package logic;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.marteEngine.entity.Entity;

public class Grass extends Entity {

	public static final String LIGHT = "textures/grass.png"; 
	public static final String DARK = "textures/grass.png"; 
	
	public Grass(float x, float y, String type) {
		super(x, y);
		setHitBox(0, 0, 40, 40);//64x85
		try {
			setGraphic(new Image(type));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
