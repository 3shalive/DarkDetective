package core;

import java.awt.Font;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class Utils {
    //костыль, который загружает массив русских символов в поддерживаемые кодировки
	private static Font font = new Font("Courier New", Font.PLAIN, 16);
    private static TrueTypeFont slicFont = new TrueTypeFont(font, true,("йцукенгшщзхъфывапролджэячсмитьбюё".toUpperCase()+"йцукенгшщзхъфывапролджэячсмитьбюё•").toCharArray());
	
    //конструктор закрыт, чтобы запретить создание объектов
	private Utils() {}
	
	/**
	 * Позволяет установить русскую кодировку
	 * @param g набор графических инструментов
	 **/
	public static void setCharset_Russian(Graphics g) {
		if(!g.getFont().equals(slicFont)) {
			g.setFont(slicFont);
			System.out.println("Установлена русская кодировка");
			//TODO: Проверки на шрифты валятся, придумать, как не вызывать его каждый тик
			//TODO: Найти отладчик с проверкой объектов в хипе, на случай утечек памяти
		}
	}
	
	/**
	 * Позволяет установить русскую кодировку
	 * @param g набор графических инструментов
	 * @param new_font шрифт awt, который задаёт параметры набора графических инструментов 
	 **/
	public static void setCharset_Russian(Font new_font, Graphics g) {
		font = new_font;
		setCharset_Russian(g);
	}
}
