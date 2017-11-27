package scienes;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import core.Camera;
import core.MyWorld;
import it.marteEngine.entity.Entity;
import logic.LightTree;
import logic.Player;
import logic.Teleporter;

public class Run extends MyWorld {
	public Run(int id, Player player) {
		super(id, player);
	}

	Player player;
	Teleporter enter;
	Teleporter leave;
	int map[][];
	
	@Override
	public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
		super.init(container, game);
		player = new Player(270, 750);
	    camera = new Camera(player, new Rectangle(0, 0, 560, 1100), container);
	    background = new Image("textures/map2.png");
	    leave = new Teleporter(240, 0, 400, 40, 2, game);
	    enter = new Teleporter(480, 680, 120, 40, 2, game);
	    enter.setAllowed(false, "Я оттуда приехал - нет смысла возвращаться");
	    enter.debug = true;
	    leave.debug = true;
	    add(leave);
	    add(enter);
	    int map[][] = {
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,1,1,1,0,0,0,0},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		for(int i = 0; i<14; i++){
			for(int j = 0; j<28; j++){
				if(map[i][j]==1){
					add(new LightTree(40*i, 40*j));
				}	    		
			}
		}
		this.map = map;
	    add(player);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.setColor(Color.green);
		g.drawString("Camera: "+camera, player.x - 250, player.y - 170);
		g.drawString("Hero "+player, player.x - 250, player.y - 150);
		for(Entity en :this.getEntities()){
			en.render(container, g);
		}
		if(showInvent)invent.render(container, g);
	}

	@Override
	public void update(final GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
	}
}