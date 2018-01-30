package scienes;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import core.MyWorld;
import logic.AgentOctavian;
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
		MyWorld.sasha = new AgentSasha(400, 400);
		MyWorld.octavian = new AgentOctavian(400, 400);
		/*
		 * Инициализация сцены подразумевает запуск её init-а
		 * Не задавайте параметры игроков в этом методе - изменения отразятся на всех сценах!
		 */
		this.addState(new Prologue(PROLOGUE));
		this.addState(new Flashback(FLASHBACK));
		this.addState(new Run(RUN));
		this.addState(new Motel(4));
		this.addState(new Death(DEATH_SCREEN));
		this.addState(new MainMenu(MENU_SCREEN));
		enterState(MENU_SCREEN);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer game = new AppGameContainer(new Launcher("The Beast"));
		game.setDisplayMode(800, 600, false);
		game.setTargetFrameRate(60);
		game.start();
	}

}
