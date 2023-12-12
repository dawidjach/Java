package commoninterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class STL {
	String stl;

	public STL(String stl) {
		setSTL(stl);
	}

	public String getSTL() {
		return stl;
	}

	private void setSTL(String stl) {
		this.stl = stl;
	}

	public String toString() {
		return stl;
	}

	public static String filePath() {
		String filePath = "path\\stl.txt";
		return filePath;
	}

	public void readFile() {
		String filePath = "path\\stl.txt";

		try

		{
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
