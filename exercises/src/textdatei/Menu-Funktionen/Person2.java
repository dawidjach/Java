package uebung_dateien;

import java.io.*;
import java.util.*;

public class Person2 {
	public static List<Person> person() {
		List<Person> personen = new ArrayList<>();
		personen.add(new Person(1, "Max", "Möstermann", 190, "Musterstadt"));
		personen.add(new Person(2, "Anna", "Mästerfrau", 25, "Musterdorf"));
		personen.add(new Person(3, "Hanah", "Montana", 18, "Musterstadt"));
		personen.add(new Person(4, "Hans", "Müller", 98, "Musterdorf"));
		personen.add(new Person(5, "Angela", "Schmidt", 75, "Straße"));
		return personen;
	}
	
	public static void writeToFile(List<Person> person) {
		String path = "/home/jach/workspace/Java_Uebung/src/person.txt";
		
		try {
			File file = new File(path);
			Writer writer = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream(file), "UTF-8"));
			BufferedWriter buffWriter = new BufferedWriter(writer);

			for (Person p : person) {
				buffWriter.write(p.toString());
				buffWriter.newLine();
			}
			System.out.println("Data is successively written to file.");
			buffWriter.close();
		} catch (IOException e) {
			System.out.println("Error while written to file! " + e.getMessage());
		}
	}
	
	public static List<Person>readFromFile() {
		String path = "/home/jach/workspace/Java_Uebung/src/person.txt";
	    List<Person> list = new ArrayList<>();
	    
	    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	        	String[] fields = line.split(",");
	            int pid = Integer.parseInt(fields[0]);
	            String firstName = fields[1];
	            String lastName = fields[2];
	            int age = Integer.parseInt(fields[3]);
	            String city = fields[4];
	            Person pers = new Person(pid, firstName, lastName, age, city);
	            list.add(pers);
	            pers.ausgeben();
	        }
	        System.out.println(list);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	public static void main(String[] args) {
		person();
		writeToFile(person());
		readFromFile();
	}

}
