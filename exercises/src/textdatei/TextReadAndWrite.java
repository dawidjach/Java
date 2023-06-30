package textdatei;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextReadAndWrite {

	public static void main(String[] args) {
		try {
			String fileName = "/home/jach/workspace/Java_Uebung/textreadandwrite.txt";
			File data = new File(fileName);
			
			String simpleText = "abcd123";
			FileWriter writer = new FileWriter(data);
			writer.write(simpleText);
			writer.close();
			System.out.println("Success!");
		}catch(IOException e) {
			System.out.println("Error");
		}
		
	}

}
