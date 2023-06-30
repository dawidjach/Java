package textdatei;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NewDatabase {
	private String firstname, lastname, city;
	private int id, age;
	
	public NewDatabase(int id, String lastname, String firstname, String city, int age) {
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.city = city;
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public String getLastname() {
		return lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getCity() {
		return city;
	}
	public int getAge() {
		return age;
	}

	public static void main(String[] args) {
		//NewDatabase[] newBase = {new NewDatabase(001,"Smith","Patrick","Los Angeles",50)};
		NewDatabase[] newBase = {
				new NewDatabase(111,"Abc","Def","Xyz",64),
				new NewDatabase(112,"Ccc","Bbb","Zyx",45),
				new NewDatabase(113,"XXy","Yyy","Zzz",50),
		};
		
		try {
			String db= "/home/jach/workspace/Java_Uebung/src/newDatabase.txt"; 
			File file = new File(db);
			FileWriter newWrite = new FileWriter(file);	
			newWrite.write("Id  " + "Lastname " + "Firstname " + "City " + "Age\n");
	
			for(NewDatabase sB : newBase) {
				newWrite.write(sB.getId() + " " + sB.getLastname() + " \t\t"
				+ sB.getFirstname() + "\t\t" + sB.getCity() + "\t"
				+ sB.getAge() + "\n");
			}
			newWrite.close();
			System.out.println("Database created successfull!");
		} catch(IOException e) {
			System.out.println("Error");
		}
	}

}
