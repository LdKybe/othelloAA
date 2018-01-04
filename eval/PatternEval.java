import java.io.*;
import java.util.Random;

public class PatternEval {
    int[] diag4, diag5, diag6, diag7, diag8;
    int[] hor1, hor2, hor3, hor4, edgex;

    

    PatternEval() {
	diag4 = new int[81];
	diag5 = new int[243];
	diag6 = new int[729];
	diag7 = new int[2187];
	diag8 = new int[6561];
	hor1 = new int[6561];
	hor2 = new int[6561];
	hor3 = new int[6561];
	hor4 = new int[6561];
	edgex = new int[59049];
    }

    static void readFromFile(BufferedReader br, int[] array) throws Exception{
	for (int i = 0; i < array.length; i++) {
	    array[i] = Integer.parseInt(br.readLine());}
    }

    static PatternEval readPatternFile(int moveCount) {
	PatternEval pe = new PatternEval();
	try {
	    String filename = String.format("file/patternFile%2d.txt", moveCount);
	    BufferedReader br = new BufferedReader(new FileReader(filename));

	    readFromFile(br, pe.diag4);
	    readFromFile(br, pe.diag5);
	    readFromFile(br, pe.diag6);
	    readFromFile(br, pe.diag7);
	    readFromFile(br, pe.diag8);
	    readFromFile(br, pe.hor1);
	    readFromFile(br, pe.hor2);
	    readFromFile(br, pe.hor3);
	    readFromFile(br, pe.hor4);
	    readFromFile(br, pe.edgex);

	    br.close();
	    
	} catch (Exception e) {
	    System.out.println("エラーの発生");
	}
	
	
	return pe;
    }

    static void makeNewRandomFile() {
	try {
	    PrintWriter[] pw = new PrintWriter[61];
	    PatternEval pe = new PatternEval();
	    for (int i = 0; i < 61; i++) {
		String filename = String.format("file/patternFile%2d.txt", i);
		pw[i] = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename))));
		writeRandom(pw[i], pe.diag4);
		writeRandom(pw[i], pe.diag5);
		writeRandom(pw[i], pe.diag6);
		writeRandom(pw[i], pe.diag7);
		writeRandom(pw[i], pe.diag8);
		writeRandom(pw[i], pe.hor1);
		writeRandom(pw[i], pe.hor2);
		writeRandom(pw[i], pe.hor3);
		writeRandom(pw[i], pe.hor4);
		writeRandom(pw[i], pe.edgex);
		pw[i].close();
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
    }
    
    static void writeRandom(PrintWriter pw, int[] array) throws Exception {
	for (int i = 0; i < array.length; i++) {
	    Random rnd = new Random();
	    pw.println(rnd.nextInt(30000));
	}
    }

    public static void main(String args[]) {
	PatternEval pe = readPatternFile(0);
	for (int i=0; i<pe.diag4.length;i++) {
	    System.out.println(pe.diag4[i]);
	}
    }
}
