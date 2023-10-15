package src.abw.client;

public class Abwesenheitsgrund {
	private String name, abwtxt;
	
	public Abwesenheitsgrund(String name)  {
		this.name = name;
	}
	
	public Abwesenheitsgrund() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbwtxt() {
		return abwtxt;
	}

	public void setAbwtxt(String abwtxt) {
		this.abwtxt = abwtxt;
	}
	
	
}
