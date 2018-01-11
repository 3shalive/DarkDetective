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
import logic.Tree;
import logic.AgentSasha;
import logic.Solid;
import logic.Teleporter;

public class CrimeSciene extends MyWorld {
	public CrimeSciene(int id,AgentSasha player) {
		super(id, player);
	}

	AgentSasha player;
	Teleporter village;
	Teleporter first_onfall;
	int map[][];
	Font font = new Font("Courier New", Font.BOLD, 12);
	TrueTypeFont slicFont = new TrueTypeFont(font, true,("יצףךוםדרשחץתפûגאןנמכהז‎ÿקסלטעüב‏¸".toUpperCase()+"יצףךוםדרשחץתפûגאןנמכהז‎ÿקסלטעüב‏¸").toCharArray());

	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		sashasInventary = new Inventary(0, 0, player);
		sashasInventary.putItem(new Gun(player));
		sashasInventary.putItem(new Blant(player));
		sashasInventary.putItem(new Medicine(player));
		sashasInventary.putItem(new Diary(player));
		player = new AgentSasha(270, 300);
		camera = new Camera(player, new Rectangle(0, 0, 920, 800), container);
	    background = new Image("textures/map2.png");
	    village = new Teleporter(350, 780, 140, 40, 2, game);
	    first_onfall = new Teleporter(820, 80, 40, 300, 5, game);
	    village.debug = true;
	    first_onfall.debug = true;
	    village.setAllowed(false, "Âמחגנאשאעüסÿ מןאסםמ!");
	    add(village);
	    add(first_onfall);
	    int map[][] = {
	    		{1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1},
	    		{1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1},
	    		{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
	    		{2,2,0,0,0,0,0,0,0,0,0,0,0,1,1,1}};
		for(int i = 0; i<23; i++){
			for(int j = 0; j<16; j++){
				if(map[i][j]==1){
					add(new Tree(40*i, 50*j, Tree.LIGHT));
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
		if(showInvent)sashasInventary.render(container, g);
	}
		
	
}





