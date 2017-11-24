package logic;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.entity.Entity;

public class Teleporter extends Entity {
	
	public int location;
	private StateBasedGame game;
	public boolean isAllowed = true;
	public String dained_message;
	public boolean debug = false;
	public boolean renderHint = false;
	private Rectangle hitBox;
	private int time = 0;
	Font font = new Font("Courier New", Font.PLAIN, 14);
	TrueTypeFont slicFont = new TrueTypeFont(font, true,("éöóêåíãøùçõúôûâàïðîëäæýÿ÷ñìèòüáþ".toUpperCase()+"éöóêåíãøùçõúôûâàïðîëäæýÿ÷ñìèòüáþ").toCharArray());

	
	public Teleporter(float x, float y, int width, int height, int location, StateBasedGame game) {
		super(x, y);
		this.location = location;
		this.game = game;
		setHitBox(0,0,width,height);
		hitBox = new Rectangle(x, y, width, height);
		addType(Entity.SOLID);
	}
	
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setFont(slicFont);
		super.render(container, g);
		if(!isAllowed&&renderHint&&time>0)g.drawString(dained_message, x+10, y+20);
		if(debug)g.draw(hitBox);
	}
	
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		if(time>0)time--;
	}
	
	@Override
	public void collisionResponse(Entity other) {
		if(other instanceof Player){
			if(isAllowed) game.enterState(location);
			else {
				renderHint = true;
				time = 2000;
			}
		}
	}
	
	public void setAllowed(boolean isAllowed, String dained_message){
		this.isAllowed = isAllowed;
		this.dained_message = dained_message;
	}

	public void setImage(Image image){
		if(image!=null)setGraphic(image);
	}

	public void Debug(boolean debug){
		this.debug = debug;
	}	

}
