package scienes;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.World;

public class Prologue extends World {
Image mass[]=new Image[3];
int i;
Sound mailboxSound;
Sound mailboxSound1;

Font font = new Font("Courier New", Font.PLAIN, 16);
TrueTypeFont slicFont = new TrueTypeFont(font, true,("יצףךוםדרשחץתפûגאןנמכהז‎ÿקסלטעüב‏¸".toUpperCase()+"יצףךוםדרשחץתפûגאןנמכהז‎ÿקסלטעüב‏¸").toCharArray());
	public Prologue(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		   Music music = new Music("data/Light.ogg");
		   music.play();
		   music.loop();
		       
		mass[0]= new Image("char.png");
		mass[1]= new Image("Gear.jpg");
		mass[2]= new Image("lost_car.png");
		mailboxSound = new Sound("data/la.ogg");
		mailboxSound1 = new Sound("data/na.ogg");
	
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
				super.render(container, game, g);
					g.setColor(Color.green);
					g.setFont(slicFont);
					if(i<100){
					g.drawImage(mass[0], 0, 0);
					g.drawString("Ìמי מעוצ ןנט חאדאהמקםûץ מבסעמÿעוכüסעגאץ", 20, 400);
					g.drawString("ןנמןאכ הוסÿעü כוע םאחאה", 20, 420);
					mailboxSound.play();
					}
					if(i<200&&i>100){
						g.drawImage(mass[1], 0, 0);
						g.drawString("ÑÀØÀ,ÏÀØÅË ÍÀÕÎÉ", 50, 400);
						mailboxSound1.play();
					}
					if(i>200){
						g.drawImage(mass[2], 0, 0);
						g.drawString("ÀÇÀÇÀÀÇÀÇÀÀÀÍÀÕÎÉ", 200, 400);
					
					}

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		i++;
		super.update(container, game, delta);
	}
}
