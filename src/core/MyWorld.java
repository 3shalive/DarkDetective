package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.World;
import logic.AgentOctavian;
import logic.AgentSasha;
import logic.Inventary;

public class MyWorld extends World {
	
	protected Image background;
	public Camera camera;
	protected boolean showInvent = false;
	protected Inventary inventary;
	public AgentSasha sasha;
	public AgentOctavian octavian;
	public int hours = 12;
	public int minutes = 0;
	public int sec = 0;
	private int day = 0;
	public StateBasedGame game;
	String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	public MyWorld(int id, AgentSasha sasha) {
		super(id);
		this.sasha = sasha;
		try {
			octavian = new AgentOctavian(0, 0);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		inventary = sasha.invent;
		this.game = game;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		if(camera!=null)camera.draw(container, g);
		if(background!=null)background.draw(0, 0);
		if(showInvent)inventary.render(container, g);
		if(hours<=12) g.drawString(days[day]+" "+hours+":"+minutes+" am", sasha.x+140, sasha.y-240);
		else g.drawString(days[day]+" "+ (hours-12)+":"+minutes+" pm", sasha.x+140, sasha.y-240);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if(key==Input.KEY_TAB) showInvent = !showInvent; 
		sasha.invent.keyPressed(key);
	}
		
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		//каскадный таймер
		sec++;
		if(sec>=60) {
			minutes++;
			sec=0;
		}
		if(minutes>=60) {
			hours++;
			minutes=0;
		}
		if(hours>=24){
			hours=0;
			day++;
		}
		if(day>6) day = 0;
		sasha.invent.update(container, delta);
	}
	
	
}
