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
	
	/**��� ��������� ��������� ��������, �������� � event
	 * <br>x - ������� �� �����������
	 * <br>y - ������� �� ���������
	 * <br>width - ������ ������� ����������� ������������
	 * <br>height - ������ ������� ����������� ������������
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
