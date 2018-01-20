package owlstests;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Launcher extends StateBasedGame{


	public Launcher(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new Display(0));
		enterState(0);
	}
	
	public static void main(String[] args) throws SlickException, IOException {
		AppGameContainer game = new AppGameContainer(new Launcher("Display"));
		game.setDisplayMode(512, 512, false);
		game.setTargetFrameRate(60);
		game.start();
	}
}


