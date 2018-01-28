package core;

import java.awt.Font;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.World;
import it.marteEngine.entity.Entity;
import logic.AgentOctavian;
import logic.AgentSasha;
import logic.Inventary;

public class MyWorld extends World {

	/*TODO: повыпиливать лишнее из проекта
	 * и написать документацию к сложным участкам кода
	 * */
	protected Image background;
	public Camera camera;
	protected boolean showInvent = false;
	protected Inventary inventary;
	public AgentSasha sasha;
	public AgentOctavian octavian;
	public int hours = 12;
	public int minutes = 0;
	public int sec = 0; 
	private int tempTime;
	private int day = 0;
	/**—тандартный счетчик*/
	protected int counter = 0;
	public StateBasedGame game;
	String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	protected Font font = new Font("Courier New", Font.PLAIN, 16);
	protected TrueTypeFont slicFont = new TrueTypeFont(font, true,
			("йцукенгшщзхъфывапролджэ€чсмитьбюЄ".toUpperCase() + "йцукенгшщзхъфывапролджэ€чсмитьбюЄ").toCharArray());
	
	
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
		g.setFont(slicFont);
		if(camera!=null)camera.draw(container, g);
		if(background!=null)background.draw(0, 0);
		if(showInvent)inventary.render(container, g);
		if(hours<=12) g.drawString(days[day]+" "+hours+":"+minutes+" am", sasha.x+140, sasha.y-240);
		else g.drawString(days[day]+" "+ (hours-12)+":"+minutes+" pm", sasha.x+140, sasha.y-240);
	}
	

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if (key == Input.KEY_ESCAPE) {
			game.enterState(10);
		}
		if(key==Input.KEY_TAB) showInvent = !showInvent; 
		sasha.invent.keyPressed(key);
	}
		
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		sec=(int)(tempTime+=delta)*6/100;
		if(sec>=60) {
			minutes++;
			tempTime-=1000;
			sec-=60;
		}
		if(minutes>=60) {
			hours++;
			minutes-=60;
		}
		if(hours>=24){
			hours-=024;
			day++;
		}
		if(day>6) day = 0;
		sasha.invent.update(container, delta);
		// +++
		List<Entity> list = getEntities();
		for (int i = 0; i < list.size() - 1; i++) {
			Entity en = list.get(i);
			// ===
			try {
				if (en.y > sasha.y) {
					if (i < list.indexOf(sasha)) {
						list.remove(list.indexOf(sasha));
						list.add(list.indexOf(en), sasha);
					}
				} else if (list.indexOf(sasha) < i) {
					list.remove(i);
					list.add(list.indexOf(sasha), en);
				}
				// ===
				if (en.y > octavian.y) {
					if (list.indexOf(en) < list.indexOf(octavian)) {
						list.remove(list.indexOf(octavian));
						list.add(list.indexOf(en), octavian);
					}
				} else if (list.indexOf(octavian) < list.indexOf(en)) {
					list.remove(i);
					list.add(list.indexOf(octavian), en);
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
				System.out.println("чота с индексами, но как-то похуй вообще");
			}
		}
	}
}
