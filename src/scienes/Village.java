package scienes;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import core.Camera;
import core.MyWorld;
import it.marteEngine.entity.Entity;
import items.Blant;
import items.Diary;
import items.Gun;
import items.Medicine;
import logic.Inventary;
import logic.LightTree;
import logic.AgentSasha;
import logic.Solid;
import logic.Teleporter;

public class Village extends MyWorld {
	public Village(int id,AgentSasha player) {
		super(id, player);
	}

	AgentSasha player;
	Teleporter motel;
	Teleporter run;
	Teleporter crime_sciene;
	int map[][];
	Font font = new Font("Courier New", Font.BOLD, 72);
	TrueTypeFont slicFont = new TrueTypeFont(font, true,("יצףךוםדרשחץתפûגאןנמכהז‎ÿקסלטעüב‏¸".toUpperCase()+"יצףךוםדרשחץתפûגאןנמכהז‎ÿקסלטעüב‏¸").toCharArray());

	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		invent = new Inventary(0, 0, player);
		invent.putItem(new Gun(player));
		invent.putItem(new Blant(player));
		invent.putItem(new Medicine(player));
		invent.putItem(new Diary(player));
		player = new AgentSasha(270, 300);
		camera = new Camera(player, new Rectangle(0, 0, 920, 800), container);
	    background = new Image("textures/map2.png");
	    run = new Teleporter(350, 780, 140, 40, 2, game);
	    motel = new Teleporter(160, 50, 40, 40, 4, game);
	    crime_sciene = new Teleporter(820, 80, 40, 300, 5, game);
	    run.debug = true;
	    motel.debug = true;
	    crime_sciene.debug = true;
	    run.setAllowed(false, "Âמחגנאשאעüסÿ מןאסםמ!");
	    add(run);
	    add(motel);
	    add(crime_sciene);
	    int map[][] = {
	    		{2,2,0,0,0,0,0,1,1,1,1,1,1,1,1,1},
	    		{2,2,0,0,0,0,0,0,0,0,1,1,0,1,1,1},
	    		{2,2,0,0,0,0,0,0,0,0,1,0,0,1,1,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
	    		{2,2,0,0,0,0,0,0,1,0,1,0,0,1,1,1},
	    		{2,2,0,0,0,0,0,0,0,1,1,0,0,1,1,1},
	    		{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1}};
		for(int i = 0; i<23; i++){
			for(int j = 0; j<16; j++){
				if(map[i][j]==1){
					add(new LightTree(40*i, 50*j));
				}	    	
				if(map[i][j]==2){
					add(new Solid(40*i, 40*j));
				}
			}
		}
		this.map = map;
	    add(player);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.setFont(slicFont);
		g.setColor(Color.green);
		for(Entity en :this.getEntities()){
			en.render(container, g);
		}
		if(showInvent)invent.render(container, g);
	}
		
	
}





