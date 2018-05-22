package prototyping;

import javax.swing.JOptionPane;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.MyWorld;
import core.Slideshow;

@Testable	
public class SlideshowTest extends MyWorld {

	Slideshow correct;
	Slideshow nullcase;
	
	public SlideshowTest(int id) {
		super(id);
	}
	
	@Test
	public void initalisingTest(){
		try {
			correct = new Slideshow("data/PrototypingSlideShow.xml");
			nullcase = new Slideshow("data/PrototypingSlideShow.xml");
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
		nullcase.draw(g);
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
}
