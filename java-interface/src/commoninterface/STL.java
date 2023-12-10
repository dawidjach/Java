package commoninterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class STL {
	String stl;

	public STL(String stl) {
		this.stl = stl;
	}

	public String getSTL() {
		return stl;
	}

	public void setSTL(String stl) {
		this.stl = stl;
	}

	public String toString() {
		return stl;
	}

	public void readFile() {
		String filePath = "C:\\Users\\Willkommen\\eclipse-workspace\\java_interface\\Company\\src\\com\\stl.txt";

		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
