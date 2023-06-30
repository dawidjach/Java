package textdatei;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NewFile {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("(c)reate oder (d)elete?");
		char output = scanner.nextLine().toLowerCase().charAt(0);
		if(output == 'c') {
			try {
			System.out.println("Path: ");
			String addFile = scanner.nextLine();	
			
			System.out.println("Text: ");
			String text = scanner.nextLine();
			FileWriter writer = new FileWriter(addFile);
			writer.write(text);
			writer.close();	
			System.out.println("--Success--");
			}catch(IOException e){
				System.out.println("Error" + e.getMessage());
			}
		} else if(output == 'd') {
				System.out.println("Path: ");
				String deleteFile = scanner.nextLine();
				File newFile = new File(deleteFile);
				if(newFile.delete()) {
					System.out.println("Success");
			} else {
				System.out.println("Error");
			}
		} else {
			System.out.println("Error");
		}
		scanner.close();
	}

}
