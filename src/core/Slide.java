package core;

import java.awt.Dimension;
import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

/** 
 * Slide �������� ��������� ��� Slideshow 
 * @author ����
 * @see core.Slideshow
 * */
public class Slide {

	//�����, ������� ����� �������������� �� ������, � ����� FPS
	private int duration;
	//���������� �������, ����������� �������, ����������� ������
	private int counter = 0;
	private Image image;
    //������� �������������� �����, � ��� ������� �� ������
	private String current_text = "";
	private Dimension textPosition = new Dimension(50, 600);
	//��������� ������� ������� � ���������� 
	private HashMap<Integer, Sound> voiceover = new HashMap<Integer, Sound>();
	//��������� ��������� � ����������
	private HashMap<Integer, String> replicas = new HashMap<Integer, String>();
	//���� ���������� ��������� ������
	public boolean isFinished = false;
	
	private Slide() {}
	
	/**
	 * �������������� �����
	 * @param image - ����������� ������
	 * @param duration - ����� "�����" ������
	 * */
	public Slide(Image image, int duration) {
		this.image = image;
		this.duration = duration;
	}
	
	/**
	 * �������� �� ��������� ������, ���������, � ������������� ��������������� �����
	 * @param g - ����� ����������� ������������ ��� ���������
	 * */
	public void draw(Graphics g) {
		if (counter < duration) {
			update();
			g.drawImage(image, 0, 0);
			g.drawString(current_text, textPosition.width, textPosition.height);
		}else isFinished = true;
	}
	
	//������� �������� ��������� �� ������� ���������� � ������� �����
	private void update() {
		//�������
		for (int time : voiceover.keySet()) {
			if(counter == time) voiceover.get(time).play();
		}
		//�������
		for (int time : replicas.keySet()) {
			if(counter == time) current_text = replicas.get(time);
		}
		counter++;
	}

	/**
	 * ��������� �������� �� ����� ������� �������
	 * @param time - ����� ������ ������� � �����
	 * @param sound - ������ Sound, ���������� - ������� �� ������
	 * */
	public void addVoiceover(int time, Sound sound) {
		voiceover.put(time, sound);	
	}
	
	/**
	 * ��������� �������� �� ����� ������� ���������
	 * @param time - ����� ������ ��������� ������ � ����� 
	 * <br>����� ������������, ���� ����� �� ��������, ��� ����� ��������� ������ �� �������
	 * @param text - ������������� �����, ������� ������� ����������
	 * */
	public void addText(int time, String text) {
		replicas.put(time, text);
	}
	
	/**
	 * ��������� �������� �� ����� ������� ���������
	 * @param time - ����� ������ ��������� ������ � ����� 
	 * <br>����� ������������, ���� ����� �� ��������, ��� ����� ��������� ������ �� �������
	 * @param text - ������������� �����, ������� ������� ����������
	 * @param position - ����� ������ �� ������
	 * */
	public void addText(int time, String text, Dimension position) {
		replicas.put(time, text);
		textPosition = position;
	}
}
