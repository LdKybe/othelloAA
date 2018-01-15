import java.io.*;
import java.util.*;

public class BonaKifuLearn {
    BufferedReader br;
    Othello othello = new Othello();
    int nextMove = 0;
    BonaMethod bm = new BonaMethod();

    public void setFile(String filename) {
	try {
	    br = new BufferedReader (new FileReader(filename));
	} catch (Exception e) {
	    System.out.println("エラー");
	    e.printStackTrace();
	}
    }

    public boolean getBanInfo () {
	try {
	    String banStr = br.readLine();
	    if (banStr == null) return false;
	    String[] kifuBan = banStr.split(" ", -1);
	    for (int i = 0; i < othello.ban.length; i++) {
		othello.ban[i] = Integer.parseInt(kifuBan[i]);
	    }
	    othello.moveCount = Integer.parseInt(br.readLine());
	    othello.STATE = Integer.parseInt(br.readLine());
	    nextMove = Integer.parseInt(br.readLine());
	} catch (Exception e ) {
	}
	return true;
    }

    public void learnEval() {
	getBanInfo();
	Othello kifuBan = new Othello();
	kifuBan.setBanInfo(othello);
	
	ArrayList<Integer> canMove = othello.checkMove();
	for (int i = 0; i < canMove.size(); i++) {
	    Othello nextOthello = new Othello();
	    nextOthello.setBanInfo(othello);
	    nextOthello.nextHand(canMove.get(i));
	    bm.learnEvaluation(nextOthello, kifuBan);
	}
    }

    public void learn (String filename) {
	setFile(filename);
	int count = 0;
	while (getBanInfo()) {
	    learnEval();
	    count++;
	    if (count % 10000 == 0) {
		System.out.print("*");
		if (count % 100000 == 0) {
		    System.out.println();
		    PatternEval.writePatternFile(bm.pe);
		}
	    }
	}
    }

    public static void main(String args[]) {
	BonaKifuLearn bkl = new BonaKifuLearn();
	while(true) bkl.learn("temp.txt");
    }
}
