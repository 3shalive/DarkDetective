package scienes;

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
import it.marteEngine.entity.Entity;
import logic.AgentOctavian;
import logic.AgentSasha;
import logic.Bush;
import logic.Tree;
import logic.Trigger;

public class Flashback extends MyWorld {
	
	public Flashback(int id) {
		super(id);
	}

	Entity car;
	Entity tent;
	Entity fireplace;
	int map[][];
	Music leitmotive;
	Image firstSlideshow[];
	Image line;
	Image big_line;
	Image lol;
	Image bushimage;
	Trigger bushevent;
	Bush[] bush = new Bush[4];
	Sound[] sound_lib = new Sound[4];
	boolean pause = false;

	
//97 | 426
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		octavian.x = 430;
		octavian.y = 400;
		octavian.debug = true;
		add(octavian);
		camera = new Camera(octavian, new Rectangle(0, 0, 990, 810), container);
		leitmotive = new Music("data/Flashback.ogg");
		//leitmotive.loop();
		counter = 3000;
	}

	@Override
	public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
		super.init(container, game);
		sasha.x = 400; 
		sasha.y = -100;//фикс бага иерархии отрисовки виртуальным сашей

		background = new Image("textures/darkmap.png");
		car = new Entity(container.getWidth() + 30 / 2 - 100, (container.getHeight() + 30) / 2 + 150) {};
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
		for(int i = 0; i<bush.length; i++) {
			bush[i] = new Bush(90, 400+30*i, Bush.DARK);
			add(bush[i]);
		}
		bush[3].x = 120;
		bush[3].y = 430;		
		bushevent = new Trigger(100, 420, 60, 80) {
			@Override
			public void event() {
				try {
					pause = true;
					bushimage = new Image("textures/beast_bush1.png");
					counter = 3000; 
					game.enterState(Launcher.RUN);
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		};
		bushevent.debug = true;
		add(bushevent);
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

		add(fireplace);
		add(car);
		add(tent);
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
				g.drawString("¬ тот день маленький —аша с родител€ми выехал на природу", 50, 10);
			}
			if (counter < 1400 && counter > 600) {
				g.drawImage(firstSlideshow[1], 0, 0);
				g.drawImage(line, 0, 0);
				g.drawString("ѕогода сто€ла отлична€, солнечный день пролетел быстро.\n"
						+ "Ќа пол€ну, где сто€л их маленький лагерь спустились сумерки", 50, 0);
			}
			if (counter > 1400 && counter < 2800) {
				g.drawImage(firstSlideshow[2], 0, 0);
				if (counter > 1400 && counter < 2300) {
					g.drawImage(big_line, 0, 0);
					g.drawString("Ѕлиже к полуночи он проснулс€ с €вным ощущением тревоги..\n"
							+ " Ћибо приснилс€ плохой сон, либо действительно чьЄ-то незримое \n"
							+ "присутствие разбудило его", 50, 20);
				}
				if (counter > 2300 && counter < 2800) {
					g.drawImage(line, 0, 0);
					g.drawString("“ак или иначе, он решил тогда осмотреть лес возле лагер€...", 50, 10);
				}
			}

		}
		if(pause) {
			getEntities().remove(bushevent);
			g.drawImage(bushimage, octavian.x-140, octavian.y-290);
			counter++;//TODO: присобачить сюда дельту
			if(counter>3500) pause = false;
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(!pause) {
			super.update(container, game, delta);
			counter+=1;//TODO: присобачить сюда дельту и помен€ть тайминги
			if (counter == 70)
				sound_lib[0].play();
			if (counter == 600)
				sound_lib[1].play();
			if (counter == 1400)
				sound_lib[2].play();
			if (counter == 2300)
				sound_lib[3].play();
		}
	}


	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		super.leave(container, game);
		leitmotive.stop();
	}

}
