package logic;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Compas extends Image{
	
	ArrayList<Trigger> events = new ArrayList<Trigger>();
	double r = 0;
	double ang = 0; 
	
	public Compas(String ref) throws SlickException {	
		super(ref);
	}
	public void add(Trigger event) {
		events.add(event);
	}
	
	public void remove(Trigger event) {
		events.remove(events.indexOf(event));
	}
	//TODO: изменить точку отсчёта с края карты на игрока
	public void update() {
		if (!events.isEmpty()) {
			Trigger event = events.get(events.size()-1);
//			System.out.println("event = "+event);
			if (event != null) {
				r = Math.sqrt(Math.pow(event.x, 2) + Math.pow(event.y, 2));
				ang = Math.atan(event.x / event.y);
//				System.out.println("r = "+r);
//				System.out.println("ang = "+ang);
				this.setRotation((float)Math.abs((180+ang*100)));
			}
		}
	}
	
}
