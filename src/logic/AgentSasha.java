package logic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import core.Player;
import items.Gun;

public class AgentSasha extends Player{
	
	public AgentSasha(float x, float y) throws SlickException {
		super(x, y);
		define("RIGHT", Input.KEY_D);
		define("LEFT", Input.KEY_A);
		define("UP", Input.KEY_W);
		define("DOWN", Input.KEY_S);
		define("ATTACK", Input.KEY_LALT);
		this.setHitBox(0, 50, 40, 10);
		debugBounds = new Rectangle(x, y+50, 40, 10);
		player = new Image("textures/sasha.png");
		wearpon = new Gun(this);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(check("RIGHT")&&collide(SOLID, x+2, y)==null)x+=speed;
		if(check("LEFT")&&collide(SOLID, x-2, y)==null)x-=speed;
		if(check("UP")&&collide(SOLID, x, y-2)==null)y-=speed; 
		if(check("DOWN")&&collide(SOLID, x, y+2)==null)y+=speed;
		if(check("ATTAK"))wearpon.effect(target);
		debugBounds.setX(x);	
		debugBounds.setY(y+50);
	}	
		
	}
