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
import logic.LightTree;
import logic.Player;
import logic.Teleporter;
import logic.VoidMonster;

public class Run extends MyWorld {
	public Run(int id,Player player) {
		super(id, player);
	}

	Player player;
	Teleporter leave;
	Image pic;
	VoidMonster monster;
	int map[][];
	boolean showMessage = false;
	Font font = new Font("Courier New", Font.BOLD, 72);
	TrueTypeFont slicFont = new TrueTypeFont(font, true,("יצףךוםדרשחץתפûגאןנמכהז‎ÿקסלטעüב‏¸".toUpperCase()+"יצףךוםדרשחץתפûגאןנמכהז‎ÿקסלטעüב‏¸").toCharArray());

	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		invent.putItem(new Gun(player));
		invent.putItem(new Blant(player));
		invent.putItem(new Medicine(player));
		invent.putItem(new Diary(player));
		player = new Player(270, 1500);
		camera = new Camera(player, new Rectangle(0, 0, 560, 1800), container);
	    background = new Image("textures/map2.png");
	    pic = background;
	    leave = new Teleporter(240, 0, 400, 40, 3, game);
	    leave.debug = true;
	    add(leave);
	    int map[][] = {
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0},
				{1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,0,1,1,1,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		for(int i = 0; i<14; i++){
			for(int j = 0; j<36; j++){
				if(map[i][j]==1){
					add(new LightTree(40*i, 50*j));
				}	    		
			}
		}
		this.map = map;
	    add(player);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		pic.draw(0, 1100);
		g.setFont(slicFont);
		g.setColor(Color.black);
		for(Entity en :this.getEntities()){
			en.render(container, g);
		}
		if(showInvent)invent.render(container, g);
		if(showMessage)g.drawString("RUN", player.x, player.y+100);
	}
		
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		if(player.y<1100&&monster==null) {
			monster = new VoidMonster(50, 1500, player, game);
			add(monster, GAME);
			showMessage = true;
			player.speed = 2;
		}
		if(player.y<800) showMessage = false;
		System.out.println("player.y: "+player.y);
		if(monster!=null)System.out.println("monster.y: "+monster.y);
	}
	
}




