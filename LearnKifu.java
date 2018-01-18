import java.io.*;
import java.util.*;

public class LearnKifu extends SetKifu {

    BufferedReader br;
    EvalPlay tdLambda = new EvalPlay(1);
    int learnSide = Othello.BLACK;

    LearnKifu () {
	tdLambda = new TDLambda(1);
    }

    
    public int next (Othello othello) {
	return next (othello, playKifuNum);
    }
    
    public int next (Othello othello, int pkNum) {
	return kifu.get(pkNum)[othello.moveCount];
    }

    public void learn () {
	learn("file");
    }
    public void learn (String dir) {
	setKifuFile();
	setKifuData();
	while (kifuNum > playKifuNum ) {
	    kifuPlay();
	    playKifuNum++;
	    //System.out.println(playKifuNum);
	}
	tdLambda.saveEval(dir);
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
	for (int i = 0; i < 10; i++) {
	    String dir = String.format("eval%d", i);
	    for (int j = 0; j < 10; j++){
		lk.learn(dir);
		System.out.println("Learn" + i);
	    }
	}
    }
}
