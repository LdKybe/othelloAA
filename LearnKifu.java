import java.io.*;
import java.util.*;

public class LearnKifu extends TDLambda {

    BufferedReader br;
    int learnSide = WHITE;
    ArrayList<int[]> kifu = new ArrayList<int[]>();
    int playKifuNum = 0;
    int kifuNum = 0;

    
    public int next (Othello othello) {
	return next (othello, playKifuNum);
    }
    
    public int next (Othello othello, int pkNum) {
	setBanInfo(othello);
	return kifu.get(1)[moveCount];
    }

    public void learn () {
	System.out.println(kifuNum);
	while (kifuNum > playKifuNum ) {
	    kifuPlay();
	    playKifuNum++;
	    System.out.println(playKifuNum);
	}
	saveEval();
    }

    public void kifuPlay () {
	Othello othello = new Othello();
	int count = 0;
	othello.init();
	while (othello.STATE != END) {
	    int nextMove = next(othello);
	    if (othello.STATE == learnSide ) {
		learnEval(othello, nextMove);
		othello.nextHand(nextMove);
	    } else {
		othello.nextHand(nextMove);
	    }
	}
    }

    
    
    public void setKifuFile() {
	setKifuFile("sen/senkou.txt");
    }
    public void setKifuFile(String filename) {
	try {br = new BufferedReader(new FileReader(filename));
	} catch (Exception e) {
	}	
    }

    public void setKifuData() {
	try {
	    String[] str;
	    String brStr = br.readLine();
	    int count = 0;
	    while (brStr != null) {
		int[] k = new int[60];
		str = brStr.split(" ", -1);
		for (int i = 0; !str[i].equals("") ; i++) {
		    k[i] = Integer.parseInt(str[i]);
		}
		kifu.add(k);
		brStr = br.readLine();
		count++;
	    }

	    this.kifuNum = count;
	    this.playKifuNum = 0;
	} catch (Exception e) {
	    System.out.println("error");
	    e.printStackTrace();
	}
    } 
    
    public static void main(String[] args) {
	LearnKifu lk = new LearnKifu();
	lk.setKifuFile();
	lk.setKifuData();
	lk.learn();
    }
}
