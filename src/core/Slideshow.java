package core;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
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
	//время жизни слайдшоу
	private int lifetime = 0;
	//текущий слайд
	private Slide current_slide = null;
	//коллекция слайдов
	private HashMap<Integer, Slide> slides = new HashMap<Integer, Slide>();
	//Флаг статуса слайд-шоу
	private boolean playing = false;
	
	/**
	 * Инициализирует слайдшоу, но не запускает его
	 * @param path - путь к XML-файлу со сценарием
	 * @see core.Slideshow.start();
	 * */
	public Slideshow(String path) throws Exception {
		parse(path);
	}
	
	/**
	 * Запускает слайд-шоу
	 */
	public void start() {
		playing = true;
	}
	
	/**
	 * Рисует слайды по-очереди, пока слайдшоу не закончится
	 * @param g - набор графических инструментов
	 * */
	public void draw(Graphics g) {
		if(counter<=1) Utils.setCharset_Russian(g);
		//слайдшоу живёт, пока счётчик меньше времени жизни, а затем почти полностью стирается из памяти
		if (counter < lifetime) {
			//то, что оно живёт - не значит, что оно работает
			if(playing) {
				update();
				//слайды рисуются, пока могут
				if (current_slide != null) {
				current_slide.draw(g);
				}
			}
		//функция локального экстерминатуса
		}else clear();
	}

	//TODO: поменять название переменной-счётчика
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
	 * парсинг слайдшоу из сценария
	 * @param path путь к сценарию
	 * */
	private void parse(String path) throws Exception{
			//Создаем логический файл
			File inputFile = new File(path);
			//Создаём фаабрику парсеров XML-документов
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			//Парсим документ
			Document doc = dBuilder.parse(inputFile);
			//втф??
			doc.getDocumentElement().normalize();
			//получаем корневой элемент
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			//Получаем корневой атрибут элемента, обозначающий имя слайдшоу
			System.out.println("Slideshow name" + doc.getDocumentElement().getAttribute("name"));
			//Получаем список слайдов
			NodeList slidesList = doc.getElementsByTagName("slides");
			//перебираем слайды по одному, и парсим
			for (int i = 0; i < slidesList.getLength(); i++) {
				Slide slide;
				//отдельный узел
				Node nNode = slidesList.item(i);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				//если тип уровня - узел..
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					//то переобразуем его в соответствующий тип 
					Element eElement = (Element) nNode;
					//Парсим путь к картинке слайда
					String pic = eElement.getElementsByTagName("picture").item(0).getTextContent();
					//парсим время жизни слайда
					int lifetime = Integer.parseInt(eElement.getAttribute("lifetime"));
					if(pic == null && lifetime == 0)
						throw new Exception("Слайд "+nNode.getAttributes().getNamedItem("name")+" повреждён"); 
					else slide = new Slide(new Image(pic), lifetime);
					//коллекция субтитров
					NodeList subtitles = eElement.getElementsByTagName("text");
					//коллекция озвучки
					NodeList voiceover = eElement.getElementsByTagName("voice");
					
					for (int j = 0; j < subtitles.getLength(); j++) {
						// узел субтитров
						Node subtitle = subtitles.item(j);
						// текст
						String subtitleText = subtitle.getTextContent();
						// тайминг появления на экране
						int timing = Integer.parseInt(subtitle.getAttributes().item(0).getTextContent());
						// добавляем к слайду
						slide.addText(timing, subtitleText);
					}
					
					
					//та же история
					for (int j = 0; j < voiceover.getLength(); j++) {
						Node voice = voiceover.item(j);
						String replica = voice.getTextContent();
						int timing = Integer.parseInt(voice.getAttributes().item(0).getTextContent());
						Sound sound = new Sound(replica);
						slide.addVoiceover(timing, sound);
					}
					
					
					//добавляем слайд..
					slides.put(lifetime, slide);
					//..и продлеваем время сущесвования всего слайдшоу
				}
			}
	}
	
	
	private void clear() {
		current_slide = null;
		slides = null;
		//так нельзя, но будем считать, что вы ничего не видели
		System.gc();
	}
	
	public int getLifetime() {
		return lifetime;
	}
	
	public int getCurrentSlideshowTic() {
		return counter;
	}
	
	public Slide getCurrentSlide() {
//		Slide slide = current_slide.copy();
//TODO: скопировать слайд и выдать, чтобы не отдавать ссылку
		//провести блочные тесты на слайд и индексы
		return current_slide;
	}	
	
	@Test
	public void indexesTest(){
		assertTrue(counter<=lifetime);
	}
	
	
	
}
