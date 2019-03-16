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
import org.newdawn.slick.state.StateBasedGame;

import core.MyWorld;
import it.marteEngine.entity.Entity;

public class Save extends Entity {
	boolean flag = true;
	boolean debug = false;
	private Rectangle hitBox;
	Input input;
	int counter = 0;
	StateBasedGame game;
	public int id = 0;

	public Save(float x, float y, StateBasedGame game) throws SlickException {
		super(x, y);
		setGraphic(new Image("textures/save1.png"));
		setHitBox(0, 0, width, height);
		hitBox = new Rectangle(x, y, width, height);
		input = game.getContainer().getInput();
		addType(SOLID);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		super.render(container, g);
		if(debug)g.draw(hitBox);
		if (flag == false)g.drawString("Игра сохранена", MyWorld.octavian.x - 50, MyWorld.octavian.y - 20);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		if (flag == false)counter++;
		if (counter >= 300) {
			counter = 0;
			flag = true;
		}
	}

	@Override
	public void collisionResponse(Entity other) {
		super.collisionResponse(other);
		try {
			if (flag) {
				flag = false;
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
				writer.write(id + "");
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}