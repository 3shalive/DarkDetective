package logic;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import it.marteEngine.entity.Entity;
import items.Diary;

public class Lost_Car extends Entity {

	int time = 0;
	int time4hint = 0;
	public boolean wasted = false;
	Image graphic;
	GameContainer container;
	boolean isAllowed2Draw = false;
	boolean isAllowed2DrawHint = false;
	Player player;
	Font font = new Font("Courier New", Font.PLAIN, 14);
	TrueTypeFont slicFont = new TrueTypeFont(font, true,("йцукенгшщзхъфывапролджэячсмитьбю".toUpperCase()+"йцукенгшщзхъфывапролджэячсмитьбю").toCharArray());
	
	
	//добавить музыку и звуки! очень важно!
	//научить перса стрелять
	//(миск) анимаш
	
	public Lost_Car(float x, float y, Player player) throws SlickException {
		super(x, y);
		this.player = player;
		graphic = new Image("textures/lost_car.png");
		setHitBox(0, 0, 100, 64);
		addType(SOLID);
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if(graphic!=null) g.drawImage(graphic, x, y);
		g.setFont(slicFont);
		this.container = container;
		if(isAllowed2Draw&&time>0){
			g.drawString("Машина брошена, и поросла мхом", x-50, y + 30);
			g.drawString("Между сиденьями нашелся старый дневник", x-50, y + 50);
			g.drawString("Нажмите <Tab> чтобы открыть рюкзак", x-50, y + 70);
			
			
		} 
		if(isAllowed2DrawHint&&time4hint>0&&!wasted)g.drawString("Осмотреть <Enter>", x-50, y - 20);
		else if(time<=0&&time4hint>0)g.drawString("Тут больше ничего нет", x-50, y - 20);
	}
	
	@Override
	public void collisionResponse(Entity other) {
		isAllowed2DrawHint = true;
		time4hint = 2000;
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		time4hint -= delta;
		time -= delta;
		if((collide(PLAYER, x+10, y+10)!=null)
				||(collide(PLAYER, x-10, y-10)!=null)
				||(collide(PLAYER, x+10, y-10)!=null)
				||(collide(PLAYER, x-10, y+10)!=null))
			if(container.getInput().isKeyPressed(Input.KEY_ENTER)&&!wasted){
				isAllowed2Draw = true;
				time = 15000;
				wasted = true;
				System.out.println("player:"+player);
				System.out.println("inventory:"+player.invent);
				player.invent.putItem(new Diary(player));
			}
	}
}
