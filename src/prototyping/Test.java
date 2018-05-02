package prototyping;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.MyWorld;
import core.Slideshow;

public class Test extends MyWorld {

	Slideshow slideshow = new Slideshow("data/PrototypingSlideShow.xml");
	
	public Test(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		slideshow.draw(g);
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
}
