package uebung_dateien;

public class Person {
	private int pid;
	private String firstName;
	private String lastName;
	private int age;
	private String city;
	
	private String club;
	private String organisation;
	private String taxNumber;
	private String aclass;
	private String distance;
	private String rank;
	private String runtime;
	private String fisCode;
	private String fis;

	public Person(int pid, String firstName, String lastName, int age, String city) {
		this.pid = pid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.city = city;
	}
	
	public Person(String lastName, String firstName, String club, String organisation, String taxNumber,
			String aclass, String distance, String rank, String runtime, String fisCode,
			String fis) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.club = club;
		this.organisation = organisation;
		this.taxNumber = taxNumber;
		this.aclass = aclass;
		this.distance = distance;
		this.rank = rank;
		this.runtime = runtime;
		this.fisCode = fisCode;
		this.fis = fis;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void ausgeben() {
		System.out.println("Vorname: " + firstName + ", Nachname: " + lastName + ", Alter: " + age + ", Wohnort: " + city);
	}

	@Override
	public String toString() {
		return pid + "," + firstName + "," + lastName + "," + age + "," + city;
	}

}
