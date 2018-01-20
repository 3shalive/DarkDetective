package logic;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.marteEngine.entity.Entity;

public class Bush extends Entity {

		public static final String LIGHT = "textures/lolbush.png"; 
		public static final String LIGHT_VERT = "textures/lolbush2.png"; 
		public static final String DARK = "textures/darkbush.png"; 
		
		public Bush(float x, float y, String type) {
			super(x, y);
			try {
				setHitBox(20, 40, 20, 20);//60x60
				setGraphic(new Image(type));
				addType(SOLID);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}

	}