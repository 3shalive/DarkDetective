package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.World;

public class MyWorld extends World {
	
	Vector2f where2draw;
	protected Image background;
	protected Camera camera;
	
	public MyWorld(int id) {
		super(id);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if(camera!=null)camera.draw(container, g);
		if(background!=null)background.draw(0, 0);
	}
}
