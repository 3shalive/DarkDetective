package logic;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.marteEngine.entity.Entity;

public class LightTree extends Entity {

	public LightTree(float x, float y) {
		super(x, y);
		setHitBox(0, 0, 64, 85);
		try {
			setGraphic(new Image("textures/tree.png"));
			addType(SOLID);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
