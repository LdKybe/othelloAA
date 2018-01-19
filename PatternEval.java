import java.io.*;
import java.util.Random;

public class PatternEval {
    int[] diag4, diag5, diag6, diag7, diag8;
    int[] hor1, hor2, hor3, hor4, edgex;
    int[] corner25, corner33;

    

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
	corner25 = new int[59049];
	corner33 = new int[19683];
	
    }

    static void readFromFile(BufferedReader br, int[] array) throws Exception{
	for (int i = 0; i < array.length; i++) {
	    array[i] = Integer.parseInt(br.readLine());}
    }

    static PatternEval readPatternFile(int moveCount) {
	return readPatternFile(moveCount, "file");
    }
    static PatternEval readPatternFile(int moveCount, String dirname) {
	PatternEval pe = new PatternEval();
	File newdir = new File("./" + dirname);
	newdir.mkdir();
	try {
	    String filename = String.format(dirname + "/patternFile%d.txt", moveCount);
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
	    readFromFile(br, pe.corner25);
	    readFromFile(br, pe.corner33);

	    br.close();
	    
	} catch (Exception e) {
	    System.out.println("エラーの発生");
	    e.printStackTrace();
	}
	
	
	return pe;
    }

    static void makeNewRandomFile() {
	try {
	    PrintWriter[] pw = new PrintWriter[61];
	    PatternEval pe = new PatternEval();
	    for (int i = 0; i < 61; i++) {
		String filename = String.format("file/patternFile%d.txt", i);
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
		writeRandom(pw[i], pe.corner25);
		writeRandom(pw[i], pe.corner33);
		pw[i].close();
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
    }

    static void writePatternFile(PatternEval[] pattern) {
	writePatternFile(pattern , "eval");
    }
    static void writePatternFile(PatternEval[] pattern, String dirname) {
	try {
	    PrintWriter[] pw = new PrintWriter[61];
	    PatternEval[] pe = pattern;
	    File newdir = new File("./" + dirname);
	    newdir.mkdir();
	    
	    for (int i = 0; i < 61; i++) {
		String filename = String.format(dirname + "/patternFile%d.txt", i);
		//System.out.println(filename);
		pw[i] = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename)))); 
		//System.out.println(pe[1].diag4[i]);
		writeAll(pw[i], pe[i].diag4);
		writeAll(pw[i], pe[i].diag5);
		writeAll(pw[i], pe[i].diag6);
		writeAll(pw[i], pe[i].diag7);
		writeAll(pw[i], pe[i].diag8);
		writeAll(pw[i], pe[i].hor1);
		writeAll(pw[i], pe[i].hor2);
		writeAll(pw[i], pe[i].hor3);
		writeAll(pw[i], pe[i].hor4);
		writeAll(pw[i], pe[i].edgex);
		writeAll(pw[i], pe[i].corner25);
		writeAll(pw[i], pe[i].corner33);
		pw[i].close();
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
    }

    static void writeAll(PrintWriter pw, int[] array) {
	for (int i = 0; i < array.length; i++) {
	    pw.println(array[i]);
	}
    
    }
    
    static void writeRandom(PrintWriter pw, int[] array) throws Exception {
	for (int i = 0; i < array.length; i++) {
	    Random rnd = new Random();
	    int x = rnd.nextInt(10000);
	    pw.println(0);
	}
    }

    public static void main(String args[]) {
	makeNewRandomFile();
	System.out.println("ファイルの作成");
    }
}
