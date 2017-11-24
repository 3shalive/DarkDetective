package logic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import it.marteEngine.entity.Entity;

public class Player extends Entity{
	public static final int LEFT = 1;
	public static final int UP = 2;
	public static final int RIGHT = 3;
	public static final int DOWN = 4;
	public Rectangle graphic;
	Image player;
	public boolean debug = false;
	
	public Player(float x, float y) throws SlickException {
		super(x, y);
		define("RIGHT", Input.KEY_D);
		define("LEFT", Input.KEY_A);
		define("UP", Input.KEY_W);
		define("DOWN", Input.KEY_S);
		define("ATTACK", Input.KEY_ENTER);
		this.setHitBox(0, 0, 40, 50);
		graphic = new Rectangle(x, y, 40, 50);
		player = new Image("textures/char.png");
		addType(PLAYER);
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if(debug)g.draw(graphic);
		g.drawImage(player, x, y);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(check("RIGHT")&&collide(SOLID, x+2, y)==null)x++;
		if(check("LEFT")&&collide(SOLID, x-2, y)==null)x--;
		if(check("UP")&&collide(SOLID, x, y-2)==null)y--; 
		if(check("DOWN")&&collide(SOLID, x, y+2)==null)y++;
		if(check("ATTAK"))System.out.println("Тык!");
		graphic.setX(x);	
		graphic.setY(y);
	}	
		
	}
