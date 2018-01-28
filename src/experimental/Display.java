package experimental;

import java.awt.Font;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import core.FreeFormForestGenerator;
import core.TrueTypeFont;
import it.marteEngine.World;
import logic.Tree;

public class Display extends World {

	FreeFormForestGenerator generator;
	int generateMOAR = 150;
	Input input;// TODO: ПИЗДЕЦ ВАЖНО!!!
	Rectangle rect;
	Rectangle rect1;
	Rectangle rect2;
	StateBasedGame game;
	ReadFromFile filereader;
	Car car;
	Write2File filewriter;
	boolean lol = false;
	String text = "Пока что тут пусто!";
	Font font = new Font("Courier New", Font.PLAIN, 16);
	TrueTypeFont slicFont = new TrueTypeFont(font, true,
			("йцукенгшщзхъфывапролджэячсмитьбюё".toUpperCase() + "йцукенгшщзхъфывапролджэячсмитьбюё").toCharArray());
	
	public Display(int id) {
		super(id);

	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		Polygon area = new Polygon();
		area.addPoint(0, 0);
		area.addPoint(200, 50);
		area.addPoint(200, 300);
		area.addPoint(0, 400);
		generator = new FreeFormForestGenerator(area);
		input = container.getInput();// ЕЩЁ ВАЖНЕЕ ЧЕМ РАНЬШЕ!!!
		rect = new Rectangle(30, 30, 200, 30);
		rect1 = new Rectangle(30, 70, 200, 30);
		rect2 = new Rectangle(30, 110, 200, 30);
		this.game = game;
		filereader = new ReadFromFile();
		filewriter = new Write2File();
		car = new Car(400, 50);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// super.render(container, game, g);
		// g.draw(generator.getArea());
		g.setFont(slicFont);
		g.setColor(Color.white);
		g.draw(rect);
		g.draw(rect1);
		g.draw(rect2);
		g.drawString("Новая игра", 85, 35);
		g.drawString("Сохранить", 85, 75);
		g.drawString("Загрузить", 85, 115);
		g.drawString("Что считалось:", 250, 200);
		g.drawString(text, 280, 220);
		car.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		if(generateMOAR > 0) {
			generateMOAR--;
			Tree tree = new Tree(-1, -1, Tree.DARK);
			tree.width = 40;
			tree.height = 60;
			this.add(tree);
			System.out.println("iteration #"+generateMOAR);
			tree.depth = generateMOAR;
			generator.generate(tree);
			if(tree.x < 0) {
				this.remove(tree);
				System.out.println(tree + "- выпилено");
			}
		}
		if(car.x>250&&lol) {
			car.x--;
		}else {
			lol = false;
			car.x = 400;
		}

		if(generateMOAR==0) {
			generator.bubbleSort(this.getEntities());
			for(int i = 0; i<this.getEntities().size()-1; i++) System.out.println("sort #"+i+" = "+this.getEntities().get(i).y);
			//короче, сортируем Y, и потом по Y ищем его владельца, и присваиваем ему depth = i
			generateMOAR--;
		}
		
		Random rand = new Random();
		if(generateMOAR<=0)this.getEntities().get(rand.nextInt(10)).depth = rand.nextInt(10);
		
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		if (rect.contains(x, y)) {
			text = "Игра началась!";
			lol = true;
		}
		if (rect1.contains(x, y)) {
			filewriter.Write(car.x + "|"+car.y);
			text = "файл перезаписан," + "\n обновите данные";
		}
		if (rect2.contains(x, y)) {
			text = filereader.read();
			String arr[] = text.split("|");
			car.x = Integer.parseInt(arr[0]);
			car.y = Integer.parseInt(arr[1]);			
		}
		
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
		
	}
	
}
