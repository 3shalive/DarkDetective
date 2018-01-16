package flashback;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.marteEngine.entity.Entity;

public class Tent extends Entity {

	int time = 0;
	int time4hint = 0;
	Image graphic;
	GameContainer container;
	
	public Tent(float x, float y) throws SlickException {
		super(x, y);
		graphic = new Image("textures/tent.png");
		setHitBox(15, 40, 90, 45);
		addType(SOLID);
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if(graphic!=null) g.drawImage(graphic, x, y);
		else System.err.println("Tent's image is null");
		this.container = container;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		time4hint -= delta;
		time -= delta;
		if((collide(PLAYER, x+10, y+10)!=null)
				||(collide(PLAYER, x-10, y-10)!=null)
				||(collide(PLAYER, x+10, y-10)!=null)
				||(collide(PLAYER, x-10, y+10)!=null)){
			//collision!
		}
		}
}
