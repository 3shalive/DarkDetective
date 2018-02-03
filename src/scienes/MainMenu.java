package scienes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import core.MyWorld;
import it.marteEngine.World;

public class MainMenu extends World {
	Image Exit;
	Image Load;
	Image Play;
	Image Background;
	Image Developers;
	int x = 680;
	int X = 680;
	int x1 = 680;
	int X1 = 680;
	int x2 = 680;
	int X2 = 680;
	int x3 = 680;
	int X3 = 680;
	int m_x = 680;
	int m_y = 0;
	StateBasedGame game;
	public MainMenu(int id) {
		super(id);
	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub
		super.init(container, game);
		this.game = game;
		Exit = new Image("textures/Exit.png");
		Load = new Image("textures/Load.png");
		Play = new Image("textures/Play.png");
		Background = new Image("textures/Background.jpg");
		Developers = new Image("textures/Developers.png");
	}

	@Override

	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		if (m_x > X && m_y < 126) {
			if (x >= 612) {
				x--;
			}
		} else if (!(x > 680)) {
			x++;
		}

		if (m_x > X && m_y < 252 && m_y > 126) {
			if (x1 >= 612) {
				x1--;
			}
		} else if (!(x1 > 680)) {
			x1++;
		}

		if (m_x > X && m_y < 378 && m_y > 252) {
			if (x2 >= 612) {
				x2--;
			}
		} else if (!(x2 > 680)) {
			x2++;
		}
		if (m_x > X && m_y < 504 && m_y > 378) {
			if (x3 >= 612) {
				x3--;
			}
		} else if (!(x3 > 680)) {
			x3++;
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.drawImage(Background, 0, 0);
		g.drawImage(Play, x, 0);
		g.drawImage(Load, x1, 126);
		g.drawImage(Developers, x2, 252);
		g.drawImage(Exit, x3, 378);

	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
		m_x = newx;
		m_y = newy;
	}

	@Override
	public void mousePressed(int button, int m_x, int m_y) {
		super.mousePressed(button, m_x, m_y);
		if (button == Input.MOUSE_LEFT_BUTTON) {
			if (m_x >= 612 && m_y < 126) {

			}
			if (m_x >= 612 && m_y < 252 && m_y > 126) {
				try {		
				    ArrayList<String> list = new ArrayList<String>();
					FileReader reader_stream = new FileReader("data/Untitled.txt");
					FileReader test_stream = new FileReader("data/Untitled.txt");
					BufferedReader reader = new BufferedReader(reader_stream);
					BufferedReader test_reader = new BufferedReader(test_stream);
					while (test_reader.readLine() != null) {
						list.add(reader.readLine());
					}
					MyWorld.octavian.x=Float.parseFloat(list.get(0));
					MyWorld.octavian.y=Float.parseFloat(list.get(1));
					MyWorld.sasha.x=Float.parseFloat(list.get(2));
					MyWorld.sasha.y=Float.parseFloat(list.get(3));
					reader.close();
					test_reader.close();
					for(String lol:list) {
						System.out.println(""+lol);
					}
					game.enterState(Launcher.FLASHBACK);
					System.out.println("Octavian "+MyWorld.octavian.x+" | "+ MyWorld.octavian.y);
					System.out.println("Sasha "+MyWorld.sasha.x+" | "+ MyWorld.sasha.y);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (m_x >= 612 && m_y < 378 && m_y > 252) {

			}

			if (m_x >= 612 && m_y < 504 && m_y > 378) {
				System.exit(0);
			}

		}
	}

}
