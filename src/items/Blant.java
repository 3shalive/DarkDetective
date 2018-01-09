package items;

import core.Item;
import it.marteEngine.entity.Entity;
import logic.Monster;
import logic.AgentSasha;

public class Blant extends Item {

	float speed = 1;
	int damage = 10;
	float block = 0.5f;
	float dodge = 0.2f;
	
	public Blant(AgentSasha player) {
		super("Дубинка", "textures/MagicBook.png", player);
		TYPE = "WEARPON";
		stats.add("Тип: оружие");
		stats.add("Масса: 1.2 кг");
		stats.add("Шанс блока: "+block*100+"%");
		stats.add("Шанс уворота: "+dodge*100+"%");
		stats.add("Урон: "+damage);
		stats.add("Скорость: "+speed);
		stats.add("Описание: резиновая дубинка");
		
	}
	
	//для анимации удара
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
