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
		super("������� ��������", "textures/MagicBook.png", player);
		TYPE = "WEARPON";
		stats.add("���: ������");
		stats.add("�����: 0.4 ��");
		stats.add("��������: "+bullets);
		stats.add("������� ��������: "+capacity);
		stats.add("��������: "+speed);
		stats.add("��������: 9�� ��������");
	}

	//��� �������� ��������
	@Override
	public void effect(Entity target) {
		if(target instanceof Monster){
		Monster monster = (Monster)target;
		monster.getHitted(damage);
		}else if(target instanceof AgentSasha)
			player.wearpon = new Gun(player);
		else System.out.println("���� ��������, ������ �������");
	}
	
}
