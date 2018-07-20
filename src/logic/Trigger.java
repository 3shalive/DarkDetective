package logic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import core.Player;
import it.marteEngine.entity.Entity;

public abstract class Trigger extends Entity {

	private Rectangle hitBox;
	public boolean debug = false;
	private Pointer pointer = null;
	private int counter = 0;
	public boolean markAsActive = false;
	
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
		addType("TRIGGER");
		pointer = new Pointer(x+width, y+height, 30);
	}
	
	public abstract void event();
	
	public void teleport(StateBasedGame game, int state){
		game.enterState(state);
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		super.render(container, g);
		if(markAsActive)pointer.render(g);
		if(debug)g.draw(hitBox);
	} 	
	
	@Override
	public void collisionResponse(Entity other) {
		super.collisionResponse(other);
		if(other instanceof Player) event();
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		if(markAsActive) pointer.update(delta, counter);
		counter += delta / 17;
	}
}
