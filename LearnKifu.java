import java.io.*;
import java.util.*;

public class LearnKifu extends SetKifu {

    BufferedReader br;
    EvalPlay tdLambda = new EvalPlay(1,0);
    int learnSide = Othello.BLACK;

    LearnKifu () {
	//tdLambda = new TDLambda(1);
    }

    
    public int next (Othello othello) {
	return next (othello, playKifuNum);
    }
    
    public int next (Othello othello, int pkNum) {
	return kifu.get(pkNum)[othello.moveCount];
    }
    
    public void learn (String dir, String filename, int side) {
	learnSide = side;
	setKifuFile(filename);
	setKifuData();
	while (kifuNum > playKifuNum ) {
	    kifuPlay();
	    playKifuNum++;
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
		tdLambda.kifuLearnEval(othello, nextMove);
		othello.nextHand(nextMove);
	    } else {
		othello.nextHand(nextMove);
	    }
	}
    }
    
    public static void main(String[] args) {
	LearnKifu lk = new LearnKifu();
	for (int i = 0; i < 200; i++) {
	    String dir = String.format("resultFile/method2/file+kifu/eval%d", i);
	    for (int j = 0; j < 1; j++){
		lk.learn(dir, "kifu/learnData/senkou.txt", Othello.BLACK);
		lk.learn(dir, "kifu/learnData/koukou.txt", Othello.WHITE);
		if (j % 10 == 0) {
		    System.out.print("*");
		}
	    }
	}
    }
}
