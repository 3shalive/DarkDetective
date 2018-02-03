package experimental;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import core.MyWorld;

public class ReadFromFile {

	public String read() {
		
		String text = "";
		
		try {
			FileReader reader_stream = new FileReader("data/Untitled.txt");
			FileReader test_stream = new FileReader("data/Untitled.txt");
			BufferedReader reader = new BufferedReader(reader_stream);
			BufferedReader test_reader = new BufferedReader(test_stream);
			while (test_reader.readLine() != null) {
				text += reader.readLine() + "\n";
			}
			reader.close();
			test_reader.close();
			
			String[] data = text.split("|");
			MyWorld.sasha.x = Integer.parseInt(data[0]);
			MyWorld.sasha.x = Integer.parseInt(data[1]);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}
}
