package scienes;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import core.Camera;
import core.MyWorld;
import it.marteEngine.entity.Entity;
import items.Blant;
import items.Gun;
import items.Medicine;
import logic.Car;
import logic.LightTree;
import logic.Player;
import logic.Teleporter;
import logic.Tent;

public class Flashback extends MyWorld {
	public Flashback(int id, Player player) {
		super(id, player);
	}

	Car car;
	Tent tent;
	Teleporter enter;
	Teleporter leave;
	int map[][];

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		player.x = (container.getWidth() + 30) / 2;
		player.y = (container.getHeight() + 330) / 2;
		invent.putItem(new Gun(player));
		invent.putItem(new Blant(player));
		invent.putItem(new Medicine(player));
		camera = new Camera(player, new Rectangle(0, 0, 880, 720), container);
		background = new Image("textures/map.png");
		car = new Car(player.x + 100, player.y + 50);
		tent = new Tent(600, 250);
		leave = new Teleporter(240, 0, 400, 40, 2, game);
		enter = new Teleporter(520, 680, 120, 40, 2, game);
		enter.setAllowed(false, "Нет смысла возвращаться");
		leave.setAllowed(true, "Сначала нужно все обыскать");
		enter.debug = false;
		leave.debug = false;
		player.debug = false;
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
					add(new LightTree(40 * i, 40 * j));
				}
			}
		}
		this.map = map;
		add(player);
		add(car);
		add(tent);
		add(leave);
		add(enter);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.setColor(Color.black);
		for (Entity en : this.getEntities()) {
			en.render(container, g);
		}
		if (showInvent)
			invent.render(container, g);
		
	}

	@Override
	public void update(final GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
	}
}
