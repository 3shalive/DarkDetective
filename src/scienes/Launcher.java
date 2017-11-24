package scienes;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.ME;
import it.marteEngine.World;

public class Launcher extends StateBasedGame {

	public Launcher(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new Prologue(0));
		World state = new OutOfFuel(1);
		state.container = container;
		this.addState(state);
		this.addState(new Run(2));
		this.addState(new Village(3));
		this.addState(new Motel(4));
		this.addState(new CrimeSciene(5));
		this.addState(new FirstOnfall(6));
		this.addState(new Waterfall(7));
		this.addState(new Epilogue(8));
		enterState(1);
	}

	public static void main(String[] args) throws SlickException {
		//ME.keyToggleDebug = Input.KEY_ENTER;
		AppGameContainer game = new AppGameContainer(new Launcher("The Beast"));
		game.setDisplayMode(512, 512, false);
		game.setTargetFrameRate(60);
		game.start();
	}

}
