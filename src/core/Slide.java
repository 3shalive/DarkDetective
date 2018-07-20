package core;

import java.awt.Dimension;
import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

/** 
 * Slide является элементом для Slideshow 
 * @author Сова
 * @see core.Slideshow
 * */
public class Slide {

	//время, которое слайд отрисовывается на экране, в тиках FPS
	private int duration;
	//внутренний счётчик, управляющий слайдом, изображение слайда
	private int counter = 0;
	private Image image;
    //текущий отрисовываемый текст, и его позиция на экране
	private String current_text = "";
	private Dimension textPosition = new Dimension(50, 600);
	//коллекция записей озвучки с таймингами 
	private HashMap<Integer, Sound> voiceover = new HashMap<Integer, Sound>();
	//коллекция субтитров с таймингами
	private HashMap<Integer, String> replicas = new HashMap<Integer, String>();
	//флаг завершения отрисовки слайда
	public boolean isFinished = false;
	
	private Slide() {}
	
	/**
	 * Инициализирует слайд
	 * @param image - изображение слайда
	 * @param duration - время "жизни" слайда
	 * */
	public Slide(Image image, int duration) {
		this.image = image;
		this.duration = duration;
	}
	
	/**
	 * Отвечает за отрисовку слайда, субтитров, и своевременное воспроизведение звука
	 * @param g - набор графических инструментов для отрисовки
	 * */
	public void draw(Graphics g) {
		if (counter < duration) {
			update();
			g.drawImage(image, 0, 0);
			g.drawString(current_text, textPosition.width, textPosition.height);
		}else isFinished = true;
	}
	
	//перебор колекций таймингов на предмет совпадений с текущим тиком
	private void update() {
		//озвучка
		for (int time : voiceover.keySet()) {
			if(counter == time) voiceover.get(time).play();
		}
		//субитры
		for (int time : replicas.keySet()) {
			if(counter == time) current_text = replicas.get(time);
		}
		counter++;
	}

	/**
	 * Позволяет добавить на слайд реплику озвучки
	 * @param time - время начала дорожки в тиках
	 * @param sound - объект Sound, собственно - дорожка со звуком
	 * */
	public void addVoiceover(int time, Sound sound) {
		voiceover.put(time, sound);	
	}
	
	/**
	 * Позволяет добавить на слайд реплику субтитров
	 * @param time - время начала отрисовки текста в тиках 
	 * <br>Будет показываться, пока текст не сменится, или время отрисовки слайда не истечёт
	 * @param text - многострочный текст, который следует отобразить
	 * */
	public void addText(int time, String text) {
		replicas.put(time, text);
	}
	
	/**
	 * Позволяет добавить на слайд реплику субтитров
	 * @param time - время начала отрисовки текста в тиках 
	 * <br>Будет показываться, пока текст не сменится, или время отрисовки слайда не истечёт
	 * @param text - многострочный текст, который следует отобразить
	 * @param position - место текста на экране
	 * */
	public void addText(int time, String text, Dimension position) {
		replicas.put(time, text);
		textPosition = position;
	}
}
