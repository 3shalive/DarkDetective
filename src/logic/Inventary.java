package logic;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import core.Camera;
import core.Item;
import core.Player;
import it.marteEngine.entity.Entity;

public class Inventary extends Entity{
private ArrayList<Item> inventList = new ArrayList<Item>();
private Item currentItem = null;
Player player;
Camera camera;
Font font = new Font("Courier New", Font.PLAIN, 14);
Font active_font = new Font("Courier New", Font.BOLD, 14);
TrueTypeFont slicFont = new TrueTypeFont(font, true,("יצףךוםדרשחץתפûגאןנמכהז‎ÿקסלטעüב‏¸".toUpperCase()+"יצףךוםדרשחץתפûגאןנמכהז‎ÿקסלטעüב‏").toCharArray());
TrueTypeFont AciveSlicFont = new TrueTypeFont(active_font, true,("יצףךוםדרשחץתפûגאןנמכהז‎ÿקסלטעüב‏¸".toUpperCase()+"יצףךוםדרשחץתפûגאןנמכהז‎ÿקסלטעüב‏").toCharArray());


public Inventary(float x, float y, Player player2) throws SlickException {
		super(x, y);
		this.player = player2; 
	}

@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
	g.setFont(slicFont);
		for (int i = 0; i<inventList.size(); i++) {
			Item item = inventList.get(i);
			if(item.equals(currentItem))g.setFont(AciveSlicFont);			
			g.drawString(item.getName(), x+50, i*50+y+50);
			g.setFont(slicFont);
			g.drawImage(item.getIcon(), x+10, i*50+y+25);
		}
		if(currentItem!=null){
		g.drawImage(currentItem.getImage(), x+150, y+50);
		for (int j = 0; j<currentItem.stats.size(); j++) {
			g.drawString(currentItem.stats.get(j), x+300, 20*j+y+100);
		}
		}
		g.drawString("Èסןמכüחףיעו סענוכמקךט הכÿ ףןנאגכוםטÿ טםגוםעאנ¸ל", x, y);		 
}

	public void putItem(Item item){
		inventList.add(item);
	}
	
	public void removeItem(int key){
		inventList.remove(key);	
	}
	
	public void keyPressed(int key){
		int idx = inventList.indexOf(currentItem);
		if(!inventList.isEmpty()&&idx<0)idx = 0;
//		System.out.println("current item:"+currentItem);
//		System.out.println("idx:"+idx);
		if(key==Input.KEY_ENTER&&currentItem!=null){
			currentItem.effect(player);
		}
		//if(key==Input.KEY_DOWN){
		if(key==Input.KEY_F){
			if((idx+1)<inventList.size()) currentItem = inventList.get(++idx);
			else currentItem = inventList.get(inventList.size()-1);
		}
		//if(key==Input.KEY_UP){
		if(key==Input.KEY_R){
			if(idx>0) currentItem = inventList.get(--idx);
			else currentItem = inventList.get(0);
		}
		
	}
	
	@Override
		public void update(GameContainer container, int delta) throws SlickException {
			//super.update(container, delta);
			x = player.x-250;
			y = player.y-250;
	}

}
