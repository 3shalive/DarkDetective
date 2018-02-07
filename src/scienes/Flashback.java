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
import logic.Bush;
import logic.Pointer;
import logic.Save;
import logic.Tree;
import logic.Trigger;

public class Flashback extends MyWorld {
	
	public Flashback(int id) {
		super(id);
	}

	Entity car, tent, fireplace;
	int map[][];
	Music leitmotive;
	Image firstSlideshow[];
	Image line, big_line, lol;
	Image bushimage;
	Trigger bushevent, bushevent1;
	Bush[] bush = new Bush[4];
	Bush[] bush1 = new Bush[4];
	Sound[] sound_lib = new Sound[4];
	Pointer checkpoint1, checkpoint2, checkpoint3;
	boolean pause = false;
	boolean[] active = new boolean[3];
	boolean questFinised = false;
	Save save;
	Flashback one = this;
	int delta = 0;
	
//97 | 426
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		sasha.x = 400; sasha.y = -100;//фикс бага иерархии отрисовки виртуальным сашей
		octavian.debug = true;
		camera = new Camera(octavian, new Rectangle(0, 0, 990, 810), container);
		leitmotive = new Music("data/Flashback.ogg");
		//leitmotive.loop();
		counter = 3000;
		MyWorld.sasha.speed=1;
		MyWorld.octavian.speed=1;
	}

	@Override
	public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
		super.init(container, game);
		save=new Save(115,167,game); 
		background = new Image("textures/darkmap.png");
		car = new Entity(container.getWidth() + 30 / 2 - 100, (container.getHeight() + 30) / 2 + 150) {};
		car.setGraphic(new Image("textures/car.png"));
		car.setHitBox(0, 20, 100, 44);
		car.addType(Entity.SOLID);
		fireplace = new Entity(550, 350) {};
		fireplace.setGraphic(new Image("textures/fireplace.png"));//207|52
		fireplace.setHitBox(10, 30, 30, 30);
		fireplace.addType(Entity.SOLID);
		tent = new Entity(600, 250) {};
		tent.setGraphic(new Image("textures/tent.png"));
		tent.setHitBox(15, 40, 90, 45);
		tent.addType(Entity.SOLID);
		for(int i = 0; i<4; i++)bush1[i]=new Bush(0, 0, Bush.DARK);
		Image[] tempArray = { 
				new Image("flashback_intro1.png"), 
				new Image("flashback_intro2.png"), //186 50
				new Image("flashback_intro3.png")};
		firstSlideshow = tempArray;
		line = new Image("textures/line.png");
		big_line = new Image("textures/big_line.png");
		sound_lib[0] = new Sound("flashback1.ogg");
		sound_lib[1] = new Sound("flashback2.ogg");
		sound_lib[2] = new Sound("flashback3.ogg");
		sound_lib[3] = new Sound("flashback4.ogg");
		bush1[0].x = 186; bush1[0].y = 50;
		bush1[1].x = 220; bush1[1].y = 50;
		bush1[2].x = 186; bush1[2].y = 80;		
		bush1[3].x = 186; bush1[3].y = 110;		
		lol = new Image("textures/lol.png");
		for(int i = 0; i<bush.length; i++) {
			bush[i] = new Bush(90, 400+30*i, Bush.DARK);
			add(bush[i]);
		}
		bush[3].x = 120; bush[3].y = 430;		
		bushevent = new Trigger(100, 420, 60, 80) {
			@Override
			public void event() {
				try {
					pause = true;
					bushimage = new Image("textures/beast_bush1.png");
					counter = 3000; 
					one.active[0] = false;
					one.active[1] = true;
					checkpoint1.setCenterX(237);
					checkpoint1.setCenterY(122);	
				}  catch (SlickException e) {
					System.out.println("Произошла ошибка при выполнении первого задания!");
					System.out.println("А если конкретно, то "+e.getMessage());
				}
			}
		};
		
		bushevent1 = new Trigger(186, 50, 60, 100) {
			@Override
			public void event() {
				try {
					pause = true;
					bushimage = new Image("textures/beast_bush1.png");
					counter = 3000; 
					one.active[1] = false;
					one.active[2] = true;
					checkpoint2.setCenterX(220);
					checkpoint2.setCenterY(80);
				} catch (SlickException e) {
					System.out.println("Произошла ошибка при выполнении второго задания!");
					System.out.println("А если конкретно, то "+e.getMessage());
				}
			}
		};		
		bushevent.debug = true;
		bushevent1.debug = true;
		checkpoint1 = new Pointer(bushevent.x+bushevent.width/2+40, bushevent.y+bushevent.width/2+40, bushevent.width/2);
		checkpoint2 = new Pointer(bushevent1.x+bushevent1.width/2+40, bushevent1.y+bushevent1.width/2+40, bushevent1.width/2);
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
		active[0] = true;
		this.map = map;
		save.id=Launcher.FLASHBACK;
		for(Bush bush: bush1) this.add(bush);
		add(bushevent);
		add(bushevent1);
		add(fireplace);
		add(car);
		add(tent);
		add(save);
		add(octavian);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setFont(slicFont);
		g.setColor(Color.black);
		if (counter > 2700) {
			super.render(container, game, g);
			g.drawImage(lol, 520, 315);
			for (Entity en : this.getEntities()) en.render(container, g);
			if (showInvent)inventary.render(container, g);
			if(active[0]||active[1])checkpoint1.render(g);
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
		if(pause) {
			g.drawImage(bushimage, octavian.x-140, octavian.y-290);			
			counter+= delta / 17;
			if(counter>3200) {
				if(octavian.collideWith(bushevent, octavian.x-5, octavian.y) != null) getEntities().remove(bushevent);
				if(octavian.collideWith(bushevent1, octavian.x-5, octavian.y) != null) getEntities().remove(bushevent1);
				pause = false;
			}
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(!pause) {
			super.update(container, game, delta);
			counter+= delta / 17;
			if (counter == 70)sound_lib[0].play();
			if (counter == 600)sound_lib[1].play();
			if (counter == 1400)sound_lib[2].play();
			if (counter == 2300)sound_lib[3].play();
			if (questFinised) game.enterState(Launcher.RUN);
			if(active[0]||active[1]) checkpoint1.update(delta/2, counter);
			this.delta = delta;
		}
	}


	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		super.leave(container, game);
		leitmotive.stop();
	}

	@Override
	public void reset() {
		counter = 0;
		leitmotive.setPosition(0);
		for (int i = 0; i < active.length; i++) active[i] = false;		
		active[0] = true;
		pause = false;
		
	}

}
