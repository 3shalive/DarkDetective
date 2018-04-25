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
	Image line, big_line, lightEffect;
	Image bushimage;
	Trigger bushevent, bushevent1, bushevent2, finish;
	Bush[] bush = new Bush[4];
	Bush[] bush1 = new Bush[4];
	Bush[] bush2 = new Bush[4];
	Sound[] sound_lib = new Sound[4];
	boolean pause = false;
	boolean isFirstQuestDone, isSecondQuestDone, isThirdQuestDone = false;
	Save save;
	Flashback one = this;
	int delta = 0;
	boolean finitaLaComedia = false;
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		reset();
		sasha.x = 400; sasha.y = -100;//фикс бага иерархии отрисовки виртуальным сашей
		octavian.debug = true;
		camera = new Camera(octavian, new Rectangle(0, 0, 990, 810), container);
		octavian.x = 450;
		octavian.y = 400;		
		leitmotive = new Music("data/Flashback.ogg");
		//leitmotive.loop(); 
		counter = 3000;
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
		fireplace.setGraphic(new Image("textures/fireplace.png"));
		fireplace.setHitBox(10, 30, 30, 30);
		fireplace.addType(Entity.SOLID);
		tent = new Entity(600, 250) {};
		tent.setGraphic(new Image("textures/tent.png"));
		tent.setHitBox(15, 40, 90, 45);
		tent.addType(Entity.SOLID);
		for(int i = 0; i<4; i++)bush1[i]=new Bush(0, 0, Bush.DARK);
		for(int i = 0; i<4; i++)bush2[i]=new Bush(0, 0, Bush.DARK);
		Image[] tempArray = { 
				new Image("flashback_intro1.png"), 
				new Image("flashback_intro2.png"), //866 616
				new Image("flashback_intro3.png")};//836 646
		firstSlideshow = tempArray;
		bushimage = new Image("textures/flashback_intro1.png");
		line = new Image("textures/line.png");
		big_line = new Image("textures/big_line.png");
		lightEffect = new Image("textures/lol.png");
		sound_lib[0] = new Sound("flashback1.ogg");
		sound_lib[1] = new Sound("flashback2.ogg");
		sound_lib[2] = new Sound("flashback3.ogg");
		sound_lib[3] = new Sound("flashback4.ogg");
		bush1[0].x = 186; bush1[0].y = 50;
		bush1[1].x = 220; bush1[1].y = 50;
		bush1[2].x = 186; bush1[2].y = 80;		
		bush1[3].x = 186; bush1[3].y = 110;		
		bush2[0].x = 866; bush2[0].y = 616;
		bush2[1].x = 866; bush2[1].y = 648;
		bush2[2].x = 820; bush2[2].y = 667;		
		bush2[3].x = 820; bush2[3].y = 697;	
		for(Bush bush: bush2) this.add(bush);
		for(int i = 0; i<bush.length; i++) {
			bush[i] = new Bush(90, 400+30*i, Bush.DARK);
			add(bush[i]);
		}
		bush[3].x = 120; bush[3].y = 430;		
		finish = new Trigger(tent.x, tent.y, tent.width, tent.height) {
			@Override
			public void event() {
				if(isFirstQuestDone&&isSecondQuestDone&&isThirdQuestDone) {
					finitaLaComedia = true;
					counter = 3000;
				}
			}
		};
		
		bushevent = new Trigger(100, 420, 60, 80) {
			@Override
			public void event() {
				if(!pause)counter = 3000;
				pause = true;
			}
		};
		
		bushevent1 = new Trigger(186, 50, 60, 100) {
			@Override
			public void event() {
				if(!pause)counter = 3000;
				pause = true;
			}
		};		
		bushevent2 = new Trigger(836, 616, 80, 100) {
			@Override
			public void event() {
				if(!pause)counter = 3000;
				pause = true;
			}
		};		
		bushevent.markAsActive = true;
		bushevent1.markAsActive = true;
		bushevent2.markAsActive = true;
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
		save.id=Launcher.FLASHBACK;
		for(Bush bush: bush1) this.add(bush);
		add(fireplace);
		add(car);
		add(tent);
		add(save);
		add(octavian);
		for(Image img:firstSlideshow)img.setAlpha(0);
		add(bushevent);
		add(bushevent1);
		add(bushevent2);
		bushimage.setAlpha(0);
		}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setFont(slicFont);
		g.setColor(Color.black);
		if (counter > 2100) {
			super.render(container, game, g);
			g.drawImage(lightEffect, 520, 315);
			for (Entity en : this.getEntities()) en.render(container, g);
			if (showInvent)inventary.render(container, g);
		} else {
			if (counter < 500) {
				if(counter<70) firstSlideshow[0].setAlpha(firstSlideshow[0].getAlpha()+0.02f);
				g.drawImage(firstSlideshow[0], 0, 0);
				g.drawImage(line, 0, 0);
				g.drawString("¬ тот день маленький —аша с родител€ми выехал на природу", 50, 10);
				if(counter>400) firstSlideshow[0].setAlpha(firstSlideshow[0].getAlpha()-0.01f);
			}
			if (counter < 1000 && counter > 500) {
				if(counter<550) firstSlideshow[1].setAlpha(firstSlideshow[1].getAlpha()+0.02f);
				g.drawImage(firstSlideshow[1], 0, 0);
				g.drawImage(line, 0, 0);
				g.drawString("ѕогода сто€ла отлична€, солнечный день пролетел быстро.\n"
						+ "Ќа пол€ну, где сто€л их маленький лагерь спустились сумерки", 50, 0);
				if(counter>900) firstSlideshow[1].setAlpha(firstSlideshow[1].getAlpha()-0.01f);
			}
			if (counter > 1000 && counter < 2100) {
				if(counter<1050) firstSlideshow[2].setAlpha(firstSlideshow[2].getAlpha()+0.02f);
				g.drawImage(firstSlideshow[2], 0, 0);
				if (counter > 1000 && counter < 1600) {
					g.drawImage(big_line, 0, 0);
					g.drawString("Ѕлиже к полуночи он проснулс€ с €вным ощущением тревоги..\n"
							+ " Ћибо приснилс€ плохой сон, либо действительно чьЄ-то незримое \n"
							+ "присутствие разбудило его", 50, 20);
				}
				if (counter > 1600 && counter < 2000) {
					g.drawImage(line, 0, 0);
					g.drawString("“ак или иначе, он решил тогда осмотреть лес возле лагер€...", 50, 10);
					if(counter>1900) firstSlideshow[2].setAlpha(firstSlideshow[2].getAlpha()-0.01f);
				}
			}

		}
		if(pause) {
			if(bushimage.getAlpha()>1)bushimage.setAlpha(0);
			if(counter<3050) bushimage.setAlpha(bushimage.getAlpha()+0.02f);
			if(counter>3050&&counter<3100) bushimage.setAlpha(1);
			if(octavian.collideWith(bushevent, octavian.x-2, octavian.y-2) != null)g.drawImage(bushimage, octavian.x-140, octavian.y-290);	
			else if(octavian.collideWith(bushevent1, octavian.x-2, octavian.y-2) != null) g.drawImage(bushimage, 20, 20);
			else if(octavian.collideWith(bushevent2, octavian.x+2, octavian.y+2) != null) g.drawImage(bushimage, octavian.x-600,  octavian.y-380);
			Entity en = octavian.collide("TRIGGER", octavian.x-2, octavian.y-2);
			if(en==null) en = octavian.collide("TRIGGER", octavian.x+octavian.width+2, octavian.y+octavian.height-2);
			if(en==null) en = octavian.collide("TRIGGER", octavian.x+octavian.width+2, octavian.y+2);
			if(en==null) en = octavian.collide("TRIGGER", octavian.x-2, octavian.y+octavian.height-2);
			if(!isFirstQuestDone) isFirstQuestDone = true;
			else if(!isSecondQuestDone&&getEntities().indexOf(en)!=-1) {
				isSecondQuestDone = true;
				bushimage = new Image("textures/flashback_intro2.png");
			}
			else if(!isThirdQuestDone&&getEntities().indexOf(en)!=-1) {
				isThirdQuestDone = true;
				bushimage = new Image("textures/flashback_intro3.png");
			}
			getEntities().remove(en);
			counter += delta/17;
			if(counter>3300) bushimage.setAlpha(bushimage.getAlpha()-0.01f);
			if(counter>3400) pause = false;
		}
		if(finitaLaComedia) {
			if (counter < 500) {
				if(counter<70) firstSlideshow[0].setAlpha(firstSlideshow[0].getAlpha()+0.02f);
				g.drawImage(firstSlideshow[0], 0, 0);
				g.drawImage(line, 0, 0);
				g.drawString("¬ тот день маленький —аша с родител€ми выехал на природу", 50, 10);
				if(counter>400) firstSlideshow[0].setAlpha(firstSlideshow[0].getAlpha()-0.01f);
			}
			if (counter < 1000 && counter > 500) {
				if(counter<550) firstSlideshow[1].setAlpha(firstSlideshow[1].getAlpha()+0.02f);
				g.drawImage(firstSlideshow[1], 0, 0);
				g.drawImage(line, 0, 0);
				g.drawString("ѕогода сто€ла отлична€, солнечный день пролетел быстро.\n"
						+ "Ќа пол€ну, где сто€л их маленький лагерь спустились сумерки", 50, 0);
				if(counter>900) firstSlideshow[1].setAlpha(firstSlideshow[1].getAlpha()-0.01f);
			}
			if (counter > 1000 && counter < 2100) {
				if(counter<1050) firstSlideshow[2].setAlpha(firstSlideshow[2].getAlpha()+0.02f);
				g.drawImage(firstSlideshow[2], 0, 0);
				if (counter > 1000 && counter < 1600) {
					g.drawImage(big_line, 0, 0);
					g.drawString("Ѕлиже к полуночи он проснулс€ с €вным ощущением тревоги..\n"
							+ " Ћибо приснилс€ плохой сон, либо действительно чьЄ-то незримое \n"
							+ "присутствие разбудило его", 50, 20);
				}
				if (counter > 1600 && counter < 2000) {
					g.drawImage(line, 0, 0);
					g.drawString("“ак или иначе, он решил тогда осмотреть лес возле лагер€...", 50, 10);
					if(counter>1900) firstSlideshow[2].setAlpha(firstSlideshow[2].getAlpha()-0.01f);
				}
			}
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(!pause) {
			super.update(container, game, delta);
			counter += delta/17;
			if (counter == 70)sound_lib[0].play();
			if (counter == 550)sound_lib[1].play();
			if (counter == 1050)sound_lib[2].play();
			if (counter == 1650)sound_lib[3].play();
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
		isFirstQuestDone = false;
		isSecondQuestDone = false;
		isThirdQuestDone = false;
		pause = false;
	}

}
