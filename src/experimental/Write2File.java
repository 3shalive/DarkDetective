package experimental;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Write2File {

	public void Write(String text){
		try {
			FileWriter writer_stream = new FileWriter("data/Untitled.txt", true);
			BufferedWriter writer = new BufferedWriter(writer_stream);
			writer.append(text);
			writer.write(text);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
