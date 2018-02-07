package logic;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class Pointer extends Circle {

	/**
	 * serialUID
	 * i dunno what is dat shit, kek
	 */
	private static final long serialVersionUID = 1L;

	boolean isFull = false;
	int anim = 0;

	public Pointer(float centerPointX, float centerPointY, float radius) {
		super(centerPointX, centerPointY, radius);
	}

	public void render(Graphics g) {
		g.setAntiAlias(true);
		g.setColor(Color.green);
		g.draw(this);		
	}
	
	public void update(int delta, int counter) {
		if(isFull&&counter%4==0) {
			anim--;
			 setCenterX( getCenterX()+1);
			 setCenterY( getCenterY()+1);
		}
			else if(counter%4==0) {
				anim++;
				 setCenterX( getCenterX()-1);
				 setCenterY( getCenterY()-1);
			}
		setRadius(anim);
		if(anim == 0)isFull=false;
		if(anim == 15)isFull=true;
	}
	
}
