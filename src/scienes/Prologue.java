package scienes;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import core.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.World;

public class Prologue extends World {
	Image mass[] = new Image[3];
	int i;
	Sound mailboxSound;
	Sound mailboxSound1;

	Font font = new Font("Courier New", Font.PLAIN, 16);
	TrueTypeFont slicFont = new TrueTypeFont(font, true,
			("йцукенгшщзхъфывапролджэячсмитьбюё".toUpperCase() + "йцукенгшщзхъфывапролджэячсмитьбюё").toCharArray());

	public Prologue(int id) {
		super(id);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		Music music = new Music("data/Light.ogg");
//		music.loop();
		mass[0] = new Image("char.png");
		mass[1] = new Image("Gear.jpg");
		mass[2] = new Image("lost_car.png");
		mailboxSound = new Sound("data/intro.ogg");
		mailboxSound1 = new Sound("data/na.ogg");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.setColor(Color.green);
		g.setFont(slicFont);
		if (i < 500) {
			g.drawString("Мой отец при загадочных обстоятельствах\nпропал десять лет назад", 20, 400);
			g.drawImage(mass[0], 0, 0);
		}
		if (i < 900 && i > 500) {
			g.drawImage(mass[1], 0, 0);
			g.drawString("Должность детектива не дают просто так,\n так что сначала я был патрульным.", 50, 400);
		}
		if (i > 900) {
			g.drawImage(mass[2], 0, 0);
			g.drawString("Недавно, мне выдали моё первое задание", 200, 400);

		}

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		i++;
		if(i==1)mailboxSound.play();
		if(i==500)mailboxSound1.play();
		if(i==1200)game.enterState(1);
		super.update(container, game, delta);
	}
}
