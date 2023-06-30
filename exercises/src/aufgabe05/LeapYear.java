package aufgabe05;

public class LeapYear {
	
	
	public static boolean isLeapYear(int year) {
		if(year % 4 == 0 && year % 100 == 0 && year % 400 == 0) {
			return true;
		} else if(year % 4 == 0 && year % 100 != 0) {
			return false;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(isLeapYear(2000));	// true
		System.out.println(isLeapYear(2023));	// false
	}

}
