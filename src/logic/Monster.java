package logic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.marteEngine.entity.Entity;

public class Monster extends Entity {

	AgentSasha player;

	public Monster(float x, float y) throws SlickException {
		super(x, y);
		setGraphic(new Image("textures/Gear.jpg"));
		setHitBox(0, 0, 40, 40);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
	}

	public void getHitted(int damage) {
		// анимация получения повреждений
	}
}
