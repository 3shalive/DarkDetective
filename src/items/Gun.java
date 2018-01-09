package items;

import core.Item;
import core.Player;
import it.marteEngine.entity.Entity;
import logic.Monster;
import logic.AgentSasha;

public class Gun extends Item {
	int bullets = 30;
	int ready = 10;
	int capacity = 10;
	float speed = 2;
	int damage = 10;
	
	public Gun(Player player) {
		super("штатный пистолет", "textures/MagicBook.png", player);
		TYPE = "WEARPON";
		stats.add("Тип: оружие");
		stats.add("Масса: 0.4 кг");
		stats.add("Боезапас: "+bullets);
		stats.add("Ёмкость магазина: "+capacity);
		stats.add("Скорость: "+speed);
		stats.add("Описание: 9мм пистолет");
	}

	//для анимации выстрела
	@Override
	public void effect(Entity target) {
		if(target instanceof Monster){
		Monster monster = (Monster)target;
		monster.getHitted(damage);
		}else if(target instanceof AgentSasha)
			player.wearpon = new Gun(player);
		else System.out.println("нету монстров, некого пиздить");
	}
	
}
