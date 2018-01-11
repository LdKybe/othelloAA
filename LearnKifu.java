import java.io.*;
import java.util.*;

public class LearnKifu extends SetKifu {

    BufferedReader br;
    TDLambda tdLambda = new TDLambda();
    int learnSide = Othello.BLACK;

    LearnKifu () {
	tdLambda = new TDLambda(1);
    }

    
    public int next (Othello othello) {
	return next (othello, playKifuNum);
    }
    
    public int next (Othello othello, int pkNum) {
	//setBanInfo(othello);
	return kifu.get(pkNum)[othello.moveCount];
    }

    public void learn () {
	while (kifuNum > playKifuNum ) {
	    kifuPlay();
	    playKifuNum++;
	    System.out.println(playKifuNum);
	}
	tdLambda.saveEval();
    }

    public void kifuPlay () {
	Othello othello = new Othello();
	int count = 0;
	othello.init();
	while (othello.STATE != Othello.END) {
	    int nextMove = next(othello);
	    if (othello.STATE == learnSide ) {
		tdLambda.learnEval(othello, nextMove);
		othello.nextHand(nextMove);
	    } else {
		othello.nextHand(nextMove);
	    }
	}
    }
    
    public static void main(String[] args) {
	LearnKifu lk = new LearnKifu();
	lk.setKifuFile();
	lk.setKifuData();
	lk.learn();
    }
}
