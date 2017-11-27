package items;

import core.Item;
import it.marteEngine.entity.Entity;
import logic.Monster;
import logic.Player;

public class Medicine extends Item {

	float speed = 0.1f;
	int heals = 15;
	
	public Medicine(Player player) {
		super("�������", "textures/MagicBook.png", player);
		TYPE = "TOOL";
		stats.add("���: ����������");
		stats.add("�����: 1.0 ��");
		stats.add("���������������: "+heals);
		stats.add("��������: "+speed);
		stats.add("��������: ������� �����������");
	}
	
	//��� �������� �������
	@Override
	public void effect(Entity target) {
		if(target instanceof Player){
		player.hp += heals; 
		}else System.out.println("�������� ������ �� ����");
	}


}
