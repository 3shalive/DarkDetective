package logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import core.MyWorld;
import it.marteEngine.entity.Entity;

public class Save extends Entity {
	boolean flag=false;
	private Rectangle hitBox;
	Input input;
	int counter=0;
	public Save(float x, float y,Input input) throws SlickException {

		super(x, y);
		setGraphic(new Image("textures/save1.png"));
		setHitBox(0, 0, width, height);
		hitBox = new Rectangle(x, y, width, height);
	this.input=input;
	addType(SOLID);
	}
 @Override
public void render(GameContainer container, Graphics g) throws SlickException {
	super.render(container, g);
	g.draw(hitBox);
	if(flag)g.drawString("Игра сохранена", MyWorld.octavian.x-50,MyWorld.octavian.y-20);
 }

@Override
public void update(GameContainer container, int delta) throws SlickException {
	super.update(container, delta);
if(flag)counter++;
if(counter>=300) {
	counter=0;
	flag=false;
}
}
@Override
public void collisionResponse(Entity other) {
		super.collisionResponse(other);
		try {
			if(input.isKeyPressed(Input.KEY_ENTER)) {
			flag=true;
			FileWriter writer_stream = new FileWriter("data/Untitled.txt");
			BufferedWriter writer = new BufferedWriter(writer_stream);
			writer.write(MyWorld.octavian.x + "");
			writer.newLine();
			writer.write(MyWorld.octavian.y + "");
			writer.newLine();
			writer.write(MyWorld.sasha.x + "");
			writer.newLine();
			writer.write(MyWorld.sasha.y + "");
			writer.newLine();
			writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}