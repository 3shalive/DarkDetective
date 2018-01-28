package scienes;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.World;

public class Intro extends World {
	final int hoyMenuSlieds=3, t1=4000, t2=15000, h1=400;//€ не знаю как то нармальному назвать константы 1-€ число слайдов, 2-€ врем€ 3-€ высота
	boolean f1=false, fButten;
	int i2=0, i1, id;//первые 2 временные 3-€ тестова€
	double i4;//тоже временна€
	Input in ;
	Rectangle button1 = new Rectangle(400, 16, 128, 480);
	Sound mailboxSound[] = new Sound[hoyMenuSlieds];
	Image voise[]=new Image[hoyMenuSlieds];
	Music music;
	
	int x10, y10, d1;

	Font font = new Font("Courier New", Font.PLAIN, 16);
	TrueTypeFont slicFont = new TrueTypeFont(font, true,("йцукенгшщзхъфывапролджэ€чсмитьбюЄХ".toUpperCase()+"йцукенгшщзхъфывапролджэ€чсмитьбюЄХ").toCharArray());
	
	public Intro(int id) {
		super(id);
		this.id=id;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
		music = new Music("data/Light.ogg");
		voise[0]= new Image("char.png");
		voise[1]= new Image("Gear.jpg");
		voise[2]= new Image("lost_car.png");
		
		in=container.getInput();
		
		mailboxSound[0] = new Sound("data/la.ogg");
		mailboxSound[1] = new Sound("data/na.ogg");
		//mailboxSound[2] = new Sound;
	
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		//music.play();
	    //music.loop();
		if (mailboxSound[i2] != null)
			mailboxSound[i2].play();
		super.leave(container, game);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);	
		
		g.setColor(Color.green);
		g.setFont(slicFont);
		
		
		
		g.drawImage(voise[i2], 0, 0);
		if (i2<hoyMenuSlieds){
			switch (i2){
				case 0: g.drawString("Хћой отец при загадочных обсто€тельствах", 20, h1);break;//здесь необходимо ввести остальной текст
				case 1: g.drawString("Х—јЎј,ѕјЎ≈Ћ Ќј’ќ…", 50, h1);break;
				
			}
			
			if(i2==0){
				g.drawString("Хпропал дес€ть лет назад", 20, h1+20);
			}
			if(i2==1);
			
			if(i2==2)
				g.drawString("Хј«ј«јј«ј«јјјЌј’ќ…", 200, h1);
			}
		
		
		
		
		g.drawString(i2+" "+i1/1000.+"—ек.", 64, 96); //секундомер
		
		g.drawString("x="+x10+" y="+y10+" "+d1+" tic", x10-54+ (x10>=100? x10>457?-55:0 :x10>=10? 10: 20+(35)), y10-9-1);//координаты мышки XD
		
		if (fButten)
			g.draw(button1);
			
		/** for(int trtr=0;trtr<8192;trtr++){ //этот код был создан чтоб добавить лагов ложите проверить
				Rectangle a = new Rectangle(64, 64, 128, 128);
				g.draw(a);
		}//*/
			

	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		
		d1=delta;//при нармальных фпс delta  ~16.(6)
		x10 = in.getMouseX();
		y10 = in.getMouseY();
		
		i1+= i1<1073741823 ? delta :-1; //107...3 защита от переполнени€ int на вс€кй случ€й(1024 в 3-й степени)
		
		if (i2 < hoyMenuSlieds-1)
		if (i1 > t2 || f1) {
			f1 = fButten = false;
			i1=-1;
			i2++;
			if (mailboxSound[i2] != null)
				mailboxSound[i2].play();
		}
		else if (i1 > t1){
			if(in.isMousePressed(Input.MOUSE_LEFT_BUTTON) && x10>400)
				f1 = true;
			fButten=true;
		}
		
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		music.stop();
		super.enter(container, game);
	}
	
}
