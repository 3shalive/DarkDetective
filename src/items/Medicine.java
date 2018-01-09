package items;

import core.Item;
import it.marteEngine.entity.Entity;
import logic.Monster;
import logic.AgentSasha;

public class Medicine extends Item {

	float speed = 0.1f;
	int heals = 15;
	
	public Medicine(AgentSasha player) {
		super("Аптечка", "textures/MagicBook.png", player);
		TYPE = "TOOL";
		stats.add("Тип: инструмент");
		stats.add("Масса: 1.0 кг");
		stats.add("Восстанавливает: "+heals);
		stats.add("Скорость: "+speed);
		stats.add("Описание: простые медикаменты");
	}
	
	//для анимации лечения
	@Override
	public void effect(Entity target) {
		if(target instanceof AgentSasha){
		player.hp += heals; 
		}else System.out.println("монстров лечить не буду");
	}


}
