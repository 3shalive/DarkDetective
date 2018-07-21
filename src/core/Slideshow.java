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
 * ��������, ����������� �� XML-�����
 * @author ����
 * @see core.Slide
 **/
public class Slideshow {
	
	//���������� �������
	private int counter = 0;
	//����� ����� ��������
	private int lifetime = 0;
	//������� �����
	private Slide current_slide = null;
	//��������� �������
	private HashMap<Integer, Slide> slides = new HashMap<Integer, Slide>();
	//���� ������� �����-���
	private boolean playing = false;
	
	/**
	 * �������������� ��������, �� �� ��������� ���
	 * @param path - ���� � XML-����� �� ���������
	 * @see core.Slideshow.start();
	 * */
	public Slideshow(String path) throws Exception {
		parse(path);
	}
	
	/**
	 * ��������� �����-���
	 */
	public void start() {
		playing = true;
	}
	
	/**
	 * ������ ������ ��-�������, ���� �������� �� ����������
	 * @param g - ����� ����������� ������������
	 * */
	public void draw(Graphics g) {
		if(counter<=1) Utils.setCharset_Russian(g);
		//�������� ����, ���� ������� ������ ������� �����, � ����� ����� ��������� ��������� �� ������
		if (counter < lifetime) {
			//��, ��� ��� ���� - �� ������, ��� ��� ��������
			if(playing) {
				update();
				//������ ��������, ���� �����
				if (current_slide != null) {
				current_slide.draw(g);
				}
			}
		//������� ���������� ��������������
		}else clear();
	}

	//TODO: �������� �������� ����������-��������
	//����� ����� ���������� �������
	int coding_sucks = 0;
	/**������������ ��������� ���� ��������*/
	private void update() {
		slides.forEach((time, slide) -> {
			//���� ������� ������� ����� � ����� ������.. 
			if(counter == coding_sucks) { 
				//..�� ������������� ��������� ����� ��� �������.. 
				current_slide = slide;
				}
			//� ��������� �� ������ - ��������� ��� ����� ����� �� ��������� ����������, 
			//����� ��������� ����� ���������� ������ �� ��������� ��������
			else coding_sucks += time;
		});
		coding_sucks = 0;
		counter++;
	}
	
	
	/**
	 * ������� �������� �� ��������
	 * @param path ���� � ��������
	 * */
	private void parse(String path) throws Exception{
			//������� ���������� ����
			File inputFile = new File(path);
			//������ �������� �������� XML-����������
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			//������ ��������
			Document doc = dBuilder.parse(inputFile);
			//���??
			doc.getDocumentElement().normalize();
			//�������� �������� �������
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			//�������� �������� ������� ��������, ������������ ��� ��������
			System.out.println("Slideshow name" + doc.getDocumentElement().getAttribute("name"));
			//�������� ������ �������
			NodeList slidesList = doc.getElementsByTagName("slides");
			//���������� ������ �� ������, � ������
			for (int i = 0; i < slidesList.getLength(); i++) {
				Slide slide;
				//��������� ����
				Node nNode = slidesList.item(i);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				//���� ��� ������ - ����..
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					//�� ������������ ��� � ��������������� ��� 
					Element eElement = (Element) nNode;
					//������ ���� � �������� ������
					String pic = eElement.getElementsByTagName("picture").item(0).getTextContent();
					//������ ����� ����� ������
					int lifetime = Integer.parseInt(eElement.getAttribute("lifetime"));
					if(pic == null && lifetime == 0)
						throw new Exception("����� "+nNode.getAttributes().getNamedItem("name")+" ��������"); 
					else slide = new Slide(new Image(pic), lifetime);
					//��������� ���������
					NodeList subtitles = eElement.getElementsByTagName("text");
					//��������� �������
					NodeList voiceover = eElement.getElementsByTagName("voice");
					
					for (int j = 0; j < subtitles.getLength(); j++) {
						// ���� ���������
						Node subtitle = subtitles.item(j);
						// �����
						String subtitleText = subtitle.getTextContent();
						// ������� ��������� �� ������
						int timing = Integer.parseInt(subtitle.getAttributes().item(0).getTextContent());
						// ��������� � ������
						slide.addText(timing, subtitleText);
					}
					
					
					//�� �� �������
					for (int j = 0; j < voiceover.getLength(); j++) {
						Node voice = voiceover.item(j);
						String replica = voice.getTextContent();
						int timing = Integer.parseInt(voice.getAttributes().item(0).getTextContent());
						Sound sound = new Sound(replica);
						slide.addVoiceover(timing, sound);
					}
					
					
					//��������� �����..
					slides.put(lifetime, slide);
					//..� ���������� ����� ������������ ����� ��������
				}
			}
	}
	
	
	private void clear() {
		current_slide = null;
		slides = null;
		//��� ������, �� ����� �������, ��� �� ������ �� ������
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
//TODO: ����������� ����� � ������, ����� �� �������� ������
		//�������� ������� ����� �� ����� � �������
		return current_slide;
	}	
	
	@Test
	public void indexesTest(){
		assertTrue(counter<=lifetime);
	}
	
	
	
}
