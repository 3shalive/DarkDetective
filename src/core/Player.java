package core;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import it.marteEngine.entity.Entity;
import logic.Inventary;
import logic.Monster;

public class Player extends Entity{
	public static final int LEFT = 1;
	public static final int UP = 2;
	public static final int RIGHT = 3;
	public static final int DOWN = 4;
	protected Rectangle debugBounds;
	protected Image player;
	public int speed = 1;
	public boolean debug = false;
	public Inventary invent;
	public Item wearpon;
	public Monster target = null;
	public int hp = 100;

	
	public Player(float x, float y) throws SlickException {
		super(x, y);
		invent = new Inventary(x + 50, y + 50, this);
		addType(PLAYER);
	}
	

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if(debug) {
			g.draw(debugBounds);
			g.drawString(x+"|"+y, x-40, y-30);
		}
		g.drawImage(player, x, y);
	}
	
	}
