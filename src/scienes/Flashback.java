package scienes;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import core.Camera;
import core.MyWorld;
import core.TrueTypeFont;
import it.marteEngine.entity.Entity;
import items.Blant;
import items.Gun;
import logic.AgentSasha;
import logic.Tree;
import logic.Trigger;

public class Flashback extends MyWorld {
	public Flashback(int id, AgentSasha sasha) {
		super(id, sasha);
	}

	Entity car;
	Entity tent;
	Entity fireplace;
	Trigger run_port;
	int map[][];
	Music leitmotive;
	int counter = 0;
	Image firstSlideshow[];
	Image line;
	Image big_line;
	Image lol;
	Sound[] sound_lib = new Sound[4];
	Font font = new Font("Courier New", Font.PLAIN, 16);
	TrueTypeFont slicFont = new TrueTypeFont(font, true,
			("йцукенгшщзхъфывапролджэячсмитьбюё".toUpperCase() + "йцукенгшщзхъфывапролджэячсмитьбюё").toCharArray());
	

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		leitmotive = new Music("data/Flashback.ogg");
		leitmotive.loop();
	}

	@Override
	public void init(GameContainer container, final StateBasedGame game) throws SlickException {
		super.init(container, game);
		sasha.x = (container.getWidth() + 30) / 2;
		sasha.y = (container.getHeight() + 30) / 2;
		octavian.x = (container.getWidth() + 30) / 2 + 100;
		octavian.y = (container.getHeight() + 330) / 2 + 100;
		inventary.putItem(new Gun(sasha));
		inventary.putItem(new Blant(octavian));
		camera = new Camera(sasha, new Rectangle(0, 0, 990, 810), container);
		background = new Image("textures/darkmap.png");
		car = new Entity(sasha.x - 100, sasha.y + 150) {};
		car.setGraphic(new Image("textures/car.png"));
		car.setHitBox(0, 20, 100, 44);
		car.addType(Entity.SOLID);
		fireplace = new Entity(550, 350) {};
		fireplace.setGraphic(new Image("textures/fireplace.png"));
		fireplace.setHitBox(10, 30, 30, 30);
		fireplace.addType(Entity.SOLID);
		tent = new Entity(600, 250) {};
		tent.setGraphic(new Image("textures/tent.png"));
		tent.setHitBox(15, 40, 90, 45);
		tent.addType(Entity.SOLID);
		run_port = new Trigger(260, 0, 400, 40) {
			@Override
			public void event() {
				this.teleport(game, Launcher.RUN);
			}
		};
		Image[] tempArray = { new Image("flashback_intro1.png"), new Image("flashback_intro2.png"),
				new Image("flashback_intro3.png") };
		firstSlideshow = tempArray;
		line = new Image("textures/line.png");
		big_line = new Image("textures/big_line.png");
		sound_lib[0] = new Sound("flashback1.ogg");
		sound_lib[1] = new Sound("flashback2.ogg");
		sound_lib[2] = new Sound("flashback3.ogg");
		sound_lib[3] = new Sound("flashback4.ogg");
		lol = new Image("textures/lol.png");
		run_port.debug = false;
		sasha.debug = false;
		octavian.debug = false;
		int map[][] = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 },
				{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
				{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

		for (int i = 0; i < 22; i++) {
			for (int j = 0; j < 18; j++) {
				if (map[i][j] == 1) add(new Tree(45 * i, 45 * j, Tree.DARK));
			}
		}
		this.map = map;
		add(car);
		add(tent);
		add(fireplace);
		add(run_port);
		add(sasha);
		add(octavian);
		primary_entity = octavian;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setFont(slicFont);
		g.setColor(Color.black);
		if (counter > 2700) {
			super.render(container, game, g);
			g.drawImage(lol, 520, 315);
			for (Entity en : this.getEntities()) {
				en.render(container, g);
			}
			if (showInvent)
				inventary.render(container, g);
		} else {
			if (counter < 600) {
				g.drawImage(firstSlideshow[0], 0, 0);
				g.drawImage(line, 0, 0);
				g.drawString("В тот день маленький Саша с родителями выехал на природу", 50, 10);
			}
			if (counter < 1400 && counter > 600) {
				g.drawImage(firstSlideshow[1], 0, 0);
				g.drawImage(line, 0, 0);
				g.drawString("Погода стояла отличная, солнечный день пролетел быстро.\n"
						+ "На поляну, где стоял их маленький лагерь спустились сумерки", 50, 0);
			}
			if (counter > 1400 && counter < 2800) {
				g.drawImage(firstSlideshow[2], 0, 0);
				if (counter > 1400 && counter < 2300) {
					g.drawImage(big_line, 0, 0);
					g.drawString("Ближе к полуночи он проснулся с явным ощущением тревоги..\n"
							+ " Либо приснился плохой сон, либо действительно чьё-то незримое \n"
							+ "присутствие разбудило его", 50, 20);
				}
				if (counter > 2300 && counter < 2800) {
					g.drawImage(line, 0, 0);
					g.drawString("Так или иначе, он решил тогда осмотреть лес возле лагеря...", 50, 10);
				}
			}

		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		counter++;
		if (counter == 70)
			sound_lib[0].play();
		if (counter == 600)
			sound_lib[1].play();
		if (counter == 1400)
			sound_lib[2].play();
		if (counter == 2300)
			sound_lib[3].play();
		
	}


	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		super.leave(container, game);
		leitmotive.stop();
	}

}
