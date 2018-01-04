import java.util.*;
import java.io.*;


public class OthelloPlay extends Othello {
    ArrayList<Integer> kifu = new ArrayList<Integer>();
    OthelloPlay() {
	this.init();
	this.STATE = BLACK;
	
    }
    
    public void init() {
	/*
	if( kifu.size() > 0 ){
	    outputKifu();
	    kifu.clear();
	    }*/
	super.init();
    }
    

    public int nextHand(int x) {
	int flipNum = super.nextHand(x);
	if (flipNum > 0) {
	    //recordKifu(x);
	}
	System.out.println(moveCount);
	//printKifu();
	return flipNum;
    }

    public void recordKifu(int x) {
	kifu.add(x);
    }

    public void printKifu() {
	for (int i = 0; i<kifu.size(); i++) {
	    System.out.print(kifu.get(i) + " ");
	}
    }

    public void outputKifu(){
	try {
	    File file = new File("./test.txt");
	    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
	    for (int i = 0; i<kifu.size(); i++) {
		pw.print(kifu.get(i) + " ");
	    }
	    pw.println();
	    pw.close();
	} catch (IOException e) {
	}
    }
    
}
