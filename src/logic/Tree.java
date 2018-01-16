package logic;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.marteEngine.entity.Entity;

public class Tree extends Entity {

	public static final String LIGHT = "textures/tree.png"; 
	public static final String DARK = "textures/darktree.png"; 
	
	public Tree(float x, float y, String type) {
		super(x, y);
		setHitBox(10, 45, 40, 40);//64x85
		try {
			setGraphic(new Image(type));
			addType(SOLID);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
