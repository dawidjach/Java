package uebung_dateien;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;

public class Urkunde {
	static List<Person> list = new ArrayList<>();

	public static void readFromFile() {
		String path = "/home/jach/workspace/Java_Uebung/src/urkunde.csv";

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(path), Charset.forName("ISO-8859-15")))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				String lastName = fields[0];
				String firstName = fields[1];
				String club = fields[2];
				String organisation = fields[3];
				String taxNumber = fields[4];
				String aclass = fields[5];
				String distance = fields[6];
				String rank = fields[7];
				String runtime = fields[8];
				String fisCode = fields[9];
				String fis = fields[10];
				Person pers = new Person(lastName, firstName, club, organisation, taxNumber, aclass, distance, rank,
						runtime, fisCode, fis);
				list.add(pers);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Person> readFromFile2() {
		Collections.sort(list, Comparator.comparing(Person::getClub));
		String currentClub = "";
		for (Person person : list) {
			if (!person.getClub().equals(currentClub)) {
				System.out.println();
				System.out.println(person.getClub());
				currentClub = person.getClub();
			}
			System.out.println(person.getLastName() + "," + person.getFirstName());
		}
		return list;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		readFromFile();
		readFromFile2();
	}
}
