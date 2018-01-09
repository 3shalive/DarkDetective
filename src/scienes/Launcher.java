package scienes;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import logic.Player;

/**ќтсюда запускаетс€ игра*/
public class Launcher extends StateBasedGame {

	public Launcher(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		Player player = new Player(0,0);//инициализаци€ игрока
		this.addState(new Prologue(0));
		this.addState(new Flashback(1, player));
		this.addState(new Run(2, player));
		this.addState(new Village(3, player));
		this.addState(new Motel(4, player));
		this.addState(new CrimeSciene(5, player));
		this.addState(new FirstOnfall(6, player));
		this.addState(new Waterfall(7, player));
		this.addState(new Epilogue(8, player));
		this.addState(new Death(9));
		enterState(0);//можно изменить id сцены, когда нужно что-то отладить
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer game = new AppGameContainer(new Launcher("The Beast"));
		game.setDisplayMode(512, 512, false);
		game.setTargetFrameRate(60);
		game.start();
	}

}
