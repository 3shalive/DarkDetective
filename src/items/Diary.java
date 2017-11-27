package items;

import core.Item;
import it.marteEngine.entity.Entity;
import logic.Player;

public class Diary extends Item {

	public Diary(Player player) {
		super("Дневник", "textures/MagicBook.png", player);
		stats.add("Тип: книга");
		stats.add("Масса: 0.3 кг");
		stats.add("Описание: Потрёпаный дневник");
	}

	@Override
	public void effect(Entity target) {}
	
}
