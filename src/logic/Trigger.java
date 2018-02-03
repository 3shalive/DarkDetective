package logic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import core.Player;
import it.marteEngine.entity.Entity;

public abstract class Trigger extends Entity {

	private Rectangle hitBox;
	public boolean debug = false;
	
	/**При активации совершает действие, описаное в event
	 * <br>x - позиция по горизонтали
	 * <br>y - позиция по вертикали
	 * <br>width - ширина области обнаружения столкновений
	 * <br>height - высота области обнаружения столкновений
	 * */
	public Trigger(float x, float y, int width, int height) {
		super(x, y);
		setHitBox(0,0,width,height);
		hitBox = new Rectangle(x, y, width, height);
		addType(Entity.SOLID);
	}
	
	public abstract void event();
	
	public void teleport(StateBasedGame game, int state){
		game.enterState(state);
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		super.render(container, g);
		if(debug)g.draw(hitBox);
	} 	
	
	@Override
	public void collisionResponse(Entity other) {
		super.collisionResponse(other);
		if(other instanceof Player) event();
	}
	
}
