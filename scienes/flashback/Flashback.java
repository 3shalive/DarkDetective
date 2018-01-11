package flashback;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
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
import logic.AgentOctavian;
import logic.AgentSasha;
import logic.Tree;
import logic.Teleporter;

public class Flashback extends MyWorld {
	public Flashback(int id, AgentSasha sasha) {
		super(id, sasha);
	}

	Car car;
	Tent tent;
	Fireplace fireplace;
	Teleporter enter;
	Teleporter leave;
	int map[][];
	Music leitmotive ;
	int counter = 3000;
	Image firstSlideshow[];
	Image line;
	Image big_line;
	Sound so1;
	Sound so2;
	Sound so3;
	Sound so4;
	Font font = new Font("Courier New", Font.PLAIN, 16);
	TrueTypeFont slicFont = new TrueTypeFont(font, true,
			("йцукенгшщзхъфывапролджэячсмитьбюё".toUpperCase() + "йцукенгшщзхъфывапролджэячсмитьбюё").toCharArray());

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		leitmotive = new Music("data/Flashback.ogg");
		leitmotive.loop();
		leitmotive.play();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		sasha.x = (container.getWidth() + 30) / 2;
		sasha.y = (container.getHeight() + 30) / 2;
		octavian.x = (container.getWidth() + 30) / 2 + 100;
		octavian.y = (container.getHeight() + 330) / 2 + 100;
		sashasInventary.putItem(new Gun(sasha));
		sashasInventary.putItem(new Blant(octavian));
		camera = new Camera(sasha, new Rectangle(0, 0, 880, 720), container);
		background = new Image("textures/darkmap.png");
		car = new Car(sasha.x - 100, sasha.y + 150);
		fireplace = new Fireplace(550, 350);
		tent = new Tent(600, 250);
		leave = new Teleporter(240, 0, 400, 40, 2, game);
		enter = new Teleporter(520, 680, 120, 40, 2, game);
		enter.setAllowed(false, "Нет смысла возвращаться");
		leave.setAllowed(true, "Сначала нужно все обыскать");
		Image[] tempArray = {
				new Image("flashback_intro1.png"),
				new Image("flashback_intro2.png"),
				new Image("flashback_intro3.png")
				};
		firstSlideshow = tempArray;
		line = new Image("textures/line.png");
		big_line = new Image("textures/big_line.png");
		so1 = new Sound("flashback1.ogg");
		so2 = new Sound("flashback2.ogg");
		so3 = new Sound("flashback3.ogg");
		so4 = new Sound("flashback4.ogg");
		enter.debug = false;
		leave.debug = false;
		sasha.debug = true;
		octavian.debug = true;
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
				if (map[i][j] == 1) {
					add(new Tree(40 * i, 40 * j, Tree.DARK));
				}
			}
		}
		this.map = map;
		add(car);
		add(tent);
		add(leave);
		add(enter);
		add(sasha);
		add(octavian);
		add(fireplace);
		primary_player = octavian;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setFont(slicFont);
		g.setColor(Color.black);
		if(counter>2700){
		super.render(container, game, g);
		g.setColor(Color.black);
		for (Entity en : this.getEntities()) {
			en.render(container, g);
		}
		if (showInvent)
			sashasInventary.render(container, g);
		}else{
			if(counter<600){
				g.drawImage(firstSlideshow[0], 0, 0);
				g.drawImage(line, 0, 0);
				g.drawString("В тот день маленький Саша с родителями выехал на природу", 50, 10);
			}
			if(counter<1400&&counter>600){
				g.drawImage(firstSlideshow[1], 0, 0);
				g.drawImage(line, 0, 0);
				g.drawString("Погода стояла отличная, солнечный день пролетел быстро.\n"
						+ "На поляну, где стоял их маленький лагерь спустились сумерки", 50, 0);
			}
			if(counter>1400&&counter<2800){
				g.drawImage(firstSlideshow[2], 0, 0);
				if(counter>1400&&counter<2300){
					g.drawImage(big_line, 0, 0);
					g.drawString("Ближе к полуночи он проснулся с явным ощущением тревоги..\n"
						+ " Либо приснился плохой сон, либо действительно чьё-то незримое \n"
						+ "присутствие разбудило его", 50, 20);	
				}
				if(counter>2300&&counter<2800){
					g.drawImage(line, 0, 0);
					g.drawString("Так или иначе, он решил тогда осмотреть лес возле лагеря...", 50,10);
				}
			}
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		counter++;
		if(counter==70) so1.play();
		if(counter==600) so2.play();
		if(counter==1400) so3.play();
		if(counter==2300) so4.play();
		
		if(sasha.y<octavian.y && primary_player instanceof AgentSasha){
			this.remove(octavian);
			this.add(octavian);
			primary_player = octavian;
		}
		if(octavian.y < sasha.y && primary_player instanceof AgentOctavian){
			this.remove(sasha);
			this.add(sasha);
			primary_player = sasha;
		} 		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if(key == Input.KEY_ESCAPE){
			game.enterState(10);
		}
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		super.leave(container, game);
		leitmotive.stop();
	}
	
}
