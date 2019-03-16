package scienes;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.World;

public class Intermediate extends World {
	Image Background;
	int i;
	int targetId;
	public Intermediate(Image image,int id, int tId) {
		super(id);
		Background=image;
		targetId = tId;
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.drawImage(Background, 0, 0);
	}
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		i++;
		if(i>4000) {
			game.enterState(targetId);
		}
	}
}
