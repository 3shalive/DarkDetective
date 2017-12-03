package logic;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.marteEngine.entity.Entity;

public class Solid extends Entity {

	public Solid(float x, float y) {
		super(x, y);
		setHitBox(0, 0, 40, 40);
		try {
			setGraphic(new Image("textures/Gear.jpg"));
			addType(SOLID);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
