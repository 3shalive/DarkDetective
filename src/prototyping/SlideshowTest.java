package prototyping;

import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.MyWorld;
import core.Slideshow;

public class SlideshowTest extends MyWorld {

	Slideshow correct;
	
	public SlideshowTest(int id) {
		super(id);
		try {
			correct = new Slideshow("data/scenario/PrototypingSlideShow.xml");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		correct.draw(g);
	}
	
	@Override
	public void reset() {}
}
