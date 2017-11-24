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
import logic.Car;
import logic.LightTree;
import logic.Lost_Car;
import logic.Player;
import logic.Teleporter;

public class OutOfFuel extends MyWorld {
	public OutOfFuel(int id) {
		super(id);
	}

	Player player;
	Car car;
	Lost_Car lost_car;
	Teleporter enter;
	Teleporter leave;
	int map[][];

	@Override
	public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
		super.init(container, game);
		player = new Player((container.getWidth()+30)/2, (container.getHeight()+330)/2);
//	    camera = new Camera(player, new Rectangle(0, 0, 1280, 1024), container);
	    camera = new Camera(player, new Rectangle(0, 0, 880, 720), container);
	    background = new Image("textures/map.png");
	    car = new Car(player.x+100, player.y+50);
	    lost_car = new Lost_Car(300, 250);
	    leave = new Teleporter(240, 0, 400, 40, 2, game);
	    enter = new Teleporter(520, 680, 120, 40, 2, game);
	    enter.setAllowed(false, "Нет смысла возвращаться");
	    leave.setAllowed(false, "Сначала нужно все обыскать");
	    enter.debug = false;
	    leave.debug = false;
	    player.debug = false;
		    int map[][] = {
		    		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		    		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		    		{1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		    		{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
		    		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
		    		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		    		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    		{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
		    		{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
		    		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		    		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		    for(int i = 0; i<22; i++){
		    	for(int j = 0; j<18; j++){
		    		System.out.print(map[i][j]);
		    		if(map[i][j]==1){
		    			add(new LightTree(40*i, 40*j));
		    		}	    		
		    		if(j==18){
		    			System.out.println();
		    		}
		    		
		    	}
		    }
		    this.map = map;
	    add(player);
	    add(car);
	    add(lost_car);
	    add(leave);
	    add(enter);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.setColor(Color.green);
		g.drawString("Camera: "+camera, player.x - 250, player.y - 170);
		g.drawString("Hero "+player, player.x - 250, player.y - 150);
//		player.render(container, g);
//		car.render(container, g);
//		lost_car.render(container, g);
		for(Entity en :this.getEntities()){
			en.render(container, g);
		}
	}

	@Override
	public void update(final GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		if(lost_car.wasted)leave.setAllowed(true, "Вперёд!");

	}
}



