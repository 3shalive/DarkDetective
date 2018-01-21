package logic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import it.marteEngine.entity.Entity;

public class Tree extends Entity {

	public static final String LIGHT = "textures/tree.png"; 
	public static final String DARK = "textures/darktree.png"; 
	Rectangle rect;
	
	public Tree(float x, float y, String type) {
		super(x, y);
		setHitBox(10, 45, 40, 40);//64x85
		rect = new Rectangle(10, 45, 40, 40);
		try {
			setGraphic(new Image(type));
			addType(SOLID);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		super.render(container, g);
//		g.draw(rect);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		if(rect.getX()!=x)rect.setX(x+10);
		if(rect.getY()!=y)rect.setY(y+45);
	}
}
