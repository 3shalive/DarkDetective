package scienes;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.Player;
import logic.AgentSasha;


public class Launcher extends StateBasedGame {

	public static final int PROLOGUE = 0;
	public static final int FLASHBACK = 1;
	public static final int RUN = 2;
	public static final int MENU_SCREEN = 10;
	public static final int DEATH_SCREEN = 9;
	
	public Launcher(String name) {
		super(name);
	}
 
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		AgentSasha player = new AgentSasha(0, 0);
		this.addState(new Prologue(0));
		this.addState(new Flashback(1, player));
		this.addState(new Run(2, player));
		this.addState(new Village(3, player));
		this.addState(new Motel(4, player));
		this.addState(new CrimeSciene(5, player));
		this.addState(new FirstOnfall(6, player));
		this.addState(new Epilogue(8, player));
		this.addState(new Death(9, player));
		this.addState(new MainMenu(10));
		enterState(1);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer game = new AppGameContainer(new Launcher("The Beast"));
		game.setDisplayMode(800, 600, false); // TODO: в релизных и демо версиях использовать (800, 600, true)
		game.setTargetFrameRate(60);
		game.start();
	}

}
