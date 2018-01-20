package core;

import java.util.List;
import java.util.Random;

import org.newdawn.slick.geom.Polygon;

import it.marteEngine.entity.Entity;

public class FreeFormForestGenerator {
	
	/**
	 * Использовать в цикле типа for, где итератор - кол-во генерируемых объектов
	 * generate новых объектов НЕ СОЗДАЁТ, он просто меняет параметры переданного what2generate
	 **/
	
	int tries = 3;
	private Polygon area = new Polygon();
	
	public FreeFormForestGenerator(Polygon area) {
		this.setArea(area);
	}
	
	public void generate(Entity what2generate) {
		regenerate(what2generate);
		boolean lol = true;
		while(lol) {
			System.out.println("what2generate.x = "+what2generate.x);
			System.out.println("what2generate.y = "+what2generate.y);
			System.out.println("Polygon friendly - "+getArea().contains(what2generate.x+what2generate.width, what2generate.y+what2generate.height));
			System.out.println("do not collides = "+(what2generate.collide(Entity.SOLID,  what2generate.x+what2generate.width/2,  what2generate.x+what2generate.width/2)==null));
			if(getArea().contains(what2generate.x+what2generate.width, what2generate.y+what2generate.height)
					&&(what2generate.collide(Entity.SOLID, what2generate.x+what2generate.width/2, what2generate.y+what2generate.height/2)==null)) {
				lol=true;
				System.out.println(what2generate+"- сгенерировано!");
				break;
			}
			
			regenerate(what2generate);
			tries--;
			if(tries<=0) {
				what2generate.x=-1;
				break;
			}
		}

	}
	
	private void regenerate(Entity what2generate) {
		Random rand = new Random();
		int x = rand.nextInt((int)getArea().getMaxX())+(int)getArea().getMinX();
		int y = rand.nextInt((int)getArea().getMaxY())+(int)getArea().getMinY();
		what2generate.x = x;
		what2generate.y = y;
		what2generate.setHitBox(x, y, what2generate.width, what2generate.height);
	}
	
	public void bubbleSort(List<Entity> arr) {
        for (int i = arr.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr.get(j).y > arr.get(j+1).y) {
                    float t = arr.get(j).y;
                    float z = arr.get(j).x;
                    arr.get(j).x = arr.get(j+1).x;
                    arr.get(j+1).x = z;
                    arr.get(j).y = arr.get(j+1).y;
                    arr.get(j+1).y = t;
                }
            }
        }
	}

	public Polygon getArea() {
		return area;
	}

	public void setArea(Polygon area) {
		this.area = area;
	}
}
