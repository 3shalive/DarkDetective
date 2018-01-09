package logic;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.entity.Entity;

public class VoidMonster extends Entity {
	Random rand = new Random();
	AgentSasha player;
	StateBasedGame game;
	
	public VoidMonster(float x, float y, AgentSasha player, StateBasedGame game) throws SlickException {
		super(x, y);
		setGraphic(new Image("textures/Gear.jpg"));
		setHitBox(0, 0, 40, 40);
		this.player = player;
		this.game = game;
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		if(player.y>y)game.enterState(9);
 		if(rand.nextBoolean()&&(collideInto(SOLID, x+20, y)==null)) x += rand.nextInt(3)+1;
 		else if(collideInto(SOLID, x-20, y)==null) x -= rand.nextInt(3)+1;
		y--;
}
}







