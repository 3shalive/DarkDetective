package core;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.marteEngine.entity.Entity;
import logic.Player;

public abstract class Item {
	public Player player;
	protected Image ico;
	protected String name;
	public String TYPE = "SOLID";
	public ArrayList<String> stats = new ArrayList<String>();
	
	/**стандартный размер изображения для предмета - 150х150*/
	public Item(String name, String ico, Player player) {
		this.setName(name);
		try {
			this.ico = new Image(ico);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.player = player;
	}
	
	/**иконка - это предмет, уменьшенный в 3 раза*/
	public Image getIcon(){
		return ico.getScaledCopy(0.3f);
	}

	public Image getImage(){
		return ico;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public abstract void effect(Entity target);

}
