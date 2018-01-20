package owlstests;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;

import core.FreeFormForestGenerator;
import it.marteEngine.World;
import it.marteEngine.entity.Entity;
import logic.Tree;

public class Display extends World {
	

	FreeFormForestGenerator generator;
	int generateMOAR = 150;
	
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
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.draw(generator.getArea());
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
		
		if(generateMOAR==0) {
			generator.bubbleSort(this.getEntities());
			for(int i = 0; i<this.getEntities().size()-1; i++) System.out.println("sort #"+i+" = "+this.getEntities().get(i).y);
			//короче, сортируем Y, и потом по Y ищем его владельца, и присваиваем ему depth = i
			generateMOAR--;
		}
		
		Random rand = new Random();
		if(generateMOAR<=0)this.getEntities().get(rand.nextInt(10)).depth = rand.nextInt(10);
	}
	
	
}
