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
 * ��������, ����������� �� XML-�����
 * @author ����
 * @see core.Slide
 **/
public class Slideshow {
	
	//���������� �������
	private int counter = 0;
	//������������� ����������� ����� ����� ��������
	private int lifetime = 0;
	//������� �����
	private Slide current_slide = null;
	//��������� �������
	private HashMap<Integer, Slide> slides = new HashMap<Integer, Slide>();
	
	/**
	 * �������������� ��������
	 * @param path - ���� � XML-����� �� ��������� ��������
	 * */
	public Slideshow(String path) {
		parse(path);
	}

	/**
	 * ������ ������ ��-�������, ���� �������� �� ����������
	 * @param g - ����� ����������� ������������
	 * */
	public void draw(Graphics g) {
		if(counter<=1) Utils.setCharset_Russian(g);
		//�������� ����, ���� ������� ������ ������� �����, � ����� ����� ��������� ��������� �� ������
		if (counter < lifetime) {
				update();
				//������ ��������, ���� �����
				if (current_slide != null) {
				current_slide.draw(g);
			}
		//������� ���������� ��������������
		}else clear();
	}

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
	 * ������� XML
	 * TODO:�������� �������
	 * (��� ��� �� ��������!)
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
