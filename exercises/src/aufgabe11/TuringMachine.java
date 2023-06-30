package aufgabe11;

public class TuringMachine {
    public static void main(String[] args) {
        char[] workingTape = new char[10];
        for (int i = 0; i < workingTape.length; i++) {
            workingTape[i] = 'a';
        }
        int headPos = 0;
        char[] sourceCode = {
        		'+', '+', '+', '+', '+', '+','+', '#',// h
        		'>','+','+','+','+','#',// e
        		'>','+','+','+','+','+','+','+','+','+','+','+','#',//l
        		'>','+','+','+','+','+','+','+','+','+','+','+','#',//l
        		'>','+','+','+','+','+','+','+','+','+','+','+','+','+','+','#',//o
        		'_',//_
        		'>','+','+','+','+','+','+','+','+','+','+','+','+','+','+','+','+','+','+','+','+','+','+','#',//w
        		'>','+','+','+','+','+','+','+','+','+','+','+','+','+','+','#',//o
        		'>','+','+','+','+','+','+','+','+','+','+','+','+','+','+','+','+','+','#',//r
        		'>','+','+','+','+','+','+','+','+','+','+','+','#',//l
        		'>','+','+','+','#'//d
        };
 
        for (int i = 0; i < sourceCode.length; i++) {
            char ch = sourceCode[i];
            switch (ch) {
                case '>':
                    headPos = (headPos + 1) % workingTape.length;
                    break;
                case '<':
                    headPos = (headPos - 1 + workingTape.length) % workingTape.length;
                    break;
                case '+':
                    workingTape[headPos]++;
                    break;
                case '-':
                    workingTape[headPos]--;
                    break;
                case '_':
                	System.out.print("_");
                	break;
                case '#':
                    System.out.print(workingTape[headPos]);
                    break;
            }
        }
    }
}
