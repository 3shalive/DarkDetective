package core;

import java.awt.Dimension;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import it.marteEngine.entity.Entity;

public class Camera {
	
	Entity toFollow;
	Rectangle bounds;
	Rectangle hitBox2Follow = new Rectangle(0,0,20,20);
	public Dimension size;
	Vector2f position;
	private Vector2f OutOfBounds = new Vector2f();
	private GameContainer container;
	
	public Camera(Entity toFollow, Rectangle bounds, GameContainer container) {
		this.toFollow = toFollow;
		this.bounds = bounds;
		position = new Vector2f(bounds.getX(), bounds.getY());
		size = new Dimension((int)bounds.getWidth(), (int)bounds.getHeight());
		hitBox2Follow = new Rectangle(toFollow.x,toFollow.y,toFollow.hitboxWidth,toFollow.hitboxHeight);
		
		if(bounds.getWidth()>container.getWidth()){
			bounds.setWidth(bounds.getWidth()-(container.getWidth()+20)/2);
			bounds.setX(bounds.getX()+(container.getWidth()+20)/2);
		}
		if(bounds.getHeight()>container.getHeight()){
			bounds.setHeight(bounds.getHeight()-(container.getHeight()+20)/2);
			bounds.setY(bounds.getY()+(container.getHeight()+20)/2);
		}
		this.container = container;
	}
	public void draw(GameContainer container, Graphics g) throws SlickException{

		if(toFollow.x>bounds.getX()&&toFollow.x<bounds.getWidth())
			OutOfBounds.x = toFollow.x;
		if(toFollow.y>bounds.getY()&&toFollow.y<bounds.getHeight())
			OutOfBounds.y = toFollow.y;
		g.translate(-(OutOfBounds.x-(container.getWidth()-20)/2),-(OutOfBounds.y-(container.getHeight()-20)/2));
	}
	
	@Override
	public String toString() {
		String result = "Camera:";
		result+=bounds.getX();
		result+="x"+bounds.getY();
		result+="x"+bounds.getWidth();
		result+="x"+bounds.getHeight();
		return result;
	}
	
}
