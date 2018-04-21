package core;

import java.awt.Font;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class Utils {
    //�������, ������� ��������� ������ ������� �������� � �������������� ���������
	private static Font font = new Font("Courier New", Font.PLAIN, 16);
    private static TrueTypeFont slicFont = new TrueTypeFont(font, true,("���������������������������������".toUpperCase()+"����������������������������������").toCharArray());
	
    //����������� ������, ����� ��������� �������� ��������
	private Utils() {}
	
	/**
	 * ��������� ���������� ������� ���������
	 * @param g ����� ����������� ������������
	 **/
	public static void setCharset_Russian(Graphics g) {
		if(!g.getFont().equals(slicFont)) {
			g.setFont(slicFont);
			System.out.println("����������� ������� ���������");
			//TODO: �������� �� ������ �������, ���������, ��� �� �������� ��� ������ ���
			//TODO: ����� �������� � ��������� �������� � ����, �� ������ ������ ������
		}
	}
	
	/**
	 * ��������� ���������� ������� ���������
	 * @param g ����� ����������� ������������
	 * @param new_font ����� awt, ������� ����� ��������� ������ ����������� ������������ 
	 **/
	public static void setCharset_Russian(Font new_font, Graphics g) {
		font = new_font;
		setCharset_Russian(g);
	}
}
