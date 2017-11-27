package scienes;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.World;
import logic.Player;

public class Launcher extends StateBasedGame {

	public Launcher(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		Player player = new Player(0,0);
		this.addState(new Prologue(0));
		World state = new OutOfFuel(1, player);
		state.container = container;
		this.addState(state);
		this.addState(new Run(2, player));
		this.addState(new Village(3, player));
		this.addState(new Motel(4, player));
		this.addState(new CrimeSciene(5, player));
		this.addState(new FirstOnfall(6, player));
		this.addState(new Waterfall(7, player));
		this.addState(new Epilogue(8, player));
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
