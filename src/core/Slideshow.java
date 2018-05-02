package core;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Слайдшоу, загружаемое из XML-файла
 * @author Сова
 * @see core.Slide
 **/
public class Slideshow {
	
	//внутренний счётчик
	private int counter = 0;
	//автоматически вычисляемое время жизни слайдшоу
	private int lifetime = 0;
	//текущий слайд
	private Slide current_slide = null;
	//коллекция слайдов
	private HashMap<Integer, Slide> slides = new HashMap<Integer, Slide>();
	
	/**
	 * Инициализирует слайдшоу
	 * @param path - путь к XML-файлу со сценарием слайдшоу
	 * */
	public Slideshow(String path) {
		parse(path);
	}

	/**
	 * Рисует слайды по-очереди, пока слайдшоу не закончится
	 * @param g - набор графических инструментов
	 * */
	public void draw(Graphics g) {
		if(counter<=1) Utils.setCharset_Russian(g);
		//слайдшоу живёт, пока счётчик меньше времени жизни, а затем почти полностью стирается из памяти
		if (counter < lifetime) {
				update();
				//слайды рисуются, пока могут
				if (current_slide != null) {
				current_slide.draw(g);
			}
		//функция локального экстерминатуса
		}else clear();
	}

	//время жизни предыдущих слайдов
	int coding_sucks = 0;
	/**контролирует жизненный цикл слайдшоу*/
	private void update() {
		slides.forEach((time, slide) -> {
			//если счетчик подошёл ровно к концу слайда.. 
			if(counter == coding_sucks) { 
				//..он устанавливает следующий слайд как текущий.. 
				current_slide = slide;
				}
			//в противном же случае - добавляет его время жизни во временную переменную, 
			//чтобы проверить конец следующего слайда на следующей итерации
			else coding_sucks += time;
		});
		coding_sucks = 0;
		counter++;
	}
	
	
	/**
	 * парсинг XML
	 * TODO:дописать функцию
	 * (ибо она не работает!)
	 * */
	@Deprecated
	private void parse(String path) {
		try {
			File inputFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList slidesList = doc.getElementsByTagName("slides");
			System.out.println("----------------------------");

			for (int i = 0; i < slidesList.getLength(); i++) {
				Node nNode = slidesList.item(i);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Slide lifetime: " + eElement.getAttribute("lifetime"));
					System.out.println(
							"picture : " + eElement.getElementsByTagName("picture").item(0).getTextContent());
					System.out.println(
							"text : " + eElement.getElementsByTagName("text").item(0).getTextContent());
					System.out.println(
							"voice : " + eElement.getElementsByTagName("voice").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void clear() {
		current_slide = null;
		slides = null;
		System.gc();
	}
}
