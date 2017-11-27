package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.World;
import logic.Inventary;
import logic.Player;

public class MyWorld extends World {
	
	Vector2f where2draw;
	protected Image background;
	protected Camera camera;
	protected boolean showInvent = false;
	protected Inventary invent;
	public Player player;
	
	public MyWorld(int id, Player player) {
		super(id);
		this.player = player;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		invent = player.invent;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		if(camera!=null)camera.draw(container, g);
		if(background!=null)background.draw(0, 0);
		if(showInvent)invent.render(container, g);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if(key==Input.KEY_TAB) showInvent = !showInvent; 
		player.invent.keyPressed(key);
	}
	
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		invent.x = player.x-250;
		invent.y = player.y-250;
	}
	
}
