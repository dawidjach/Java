package aufgabe11;

public class Turingmaschine {

	public static void main(String[] args) {
		char[] workingTape = new char[10];
		for (int i = 0; i < workingTape.length; i++) {
			workingTape[i] = 'a';
		}
		int headPos = 0;
		// hello_World -> ASCCI nach Dez
		char[] sourceCode = { 43, 43, 43, 43, 43, 43, 43, 35, 62, 43, 43, 43, 43, 35, 62, 43, 43, 43, 43, 43, 43, 43,
				43, 43, 43, 43, 35, 62, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 35, 62, 43, 43, 43, 43, 43, 43, 43,
				43, 43, 43, 43, 43, 43, 43, 35, 95, 62, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43,
				43, 43, 43, 43, 43, 43, 35, 62, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 35, 62, 43, 43,
				43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 35, 62, 43, 43, 43, 43, 43, 43, 43, 43, 43,
				43, 43, 35, 62, 43, 43, 43, 35 };
		for (int i = 0; i < sourceCode.length; i++) {
			char ch = sourceCode[i];
			switch (ch) {
			case 62:// '>':
				headPos = (headPos + 1) % workingTape.length;
				break;
			case 60:// '<':
				headPos = (headPos - 1 + workingTape.length) % workingTape.length;
				break;
			case 43:// '+':
				workingTape[headPos]++;
				break;
			case 45:// '-':
				workingTape[headPos]--;
				break;
			case 95:// '_':
				System.out.print("_");
				break;
			case 35:// '#':
				System.out.print(workingTape[headPos]);
				break;
			}
		}
	}

}
