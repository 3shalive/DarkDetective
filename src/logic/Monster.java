package logic;

import it.marteEngine.entity.Entity;

public class Monster extends Entity {
	private int hp = 100;

	public Monster(float x, float y) {
		super(x, y);
	}
	
	//для анимации ранения
	public void getHitted(int damage){
		hp -= damage;
	}

}
