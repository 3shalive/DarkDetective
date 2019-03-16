package prototyping;

import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.MyWorld;
import core.Slideshow;
import core.Utils;

public class SlideshowTest extends MyWorld {

	Slideshow correct;
	
	public SlideshowTest(int id) {
		super(id);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		this.container = container;
		System.out.println("container upper "+container);
		try {
			correct = new Slideshow("data/scenario/PrototypingSlideShow.xml", container);
			correct.start();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		Utils.setCharset_Russian(g);
		correct.draw(g);
	}
	
	@Override
	public void reset() {}
}
