package items;

import core.Item;
import it.marteEngine.entity.Entity;
import logic.AgentSasha;

public class Diary extends Item {

	public Diary(AgentSasha player) {
		super("�������", "textures/MagicBook.png", player);
		stats.add("���: �����");
		stats.add("�����: 0.3 ��");
		stats.add("��������: ��������� �������");
	}

	@Override
	public void effect(Entity target) {}
	
}
