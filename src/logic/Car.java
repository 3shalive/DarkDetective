package logic;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import it.marteEngine.entity.Entity;

public class Car extends Entity {

	int time = 0;
	int time4hint = 0;
	Image graphic;
	GameContainer container;
	boolean isAllowed2Draw = false;
	boolean isAllowed2DrawHint = false;
	Font font = new Font("Courier New", Font.PLAIN, 14);
	TrueTypeFont slicFont = new TrueTypeFont(font, true,("йцукенгшщзхъфывапролджэ€чсмитьбю".toUpperCase()+"йцукенгшщзхъфывапролджэ€чсмитьбю").toCharArray());
	
	
	public Car(float x, float y) throws SlickException {
		super(x, y);
		//TODO добавить путь к изображеньке
		graphic = new Image("textures/car.png");
		setHitBox(0, 0, 100, 64);
		addType(SOLID);
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if(graphic!=null) g.drawImage(graphic, x, y);
		else System.err.println("Car's image is null");
		g.setFont(slicFont);
		this.container = container;
		if(isAllowed2Draw&&time>0)g.drawString("ћо€ машина дальше не проедет", x-50, y + 50);
		if(isAllowed2DrawHint&&time4hint>0)g.drawString("ќсмотреть <Enter>", x-50, y -20);
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
			if(container.getInput().isKeyPressed(Input.KEY_ENTER)){
				isAllowed2Draw = true;
				time = 2500;
			}
		}
}
