import java.io.*;
import java.util.*;

public class KifuBonaMethod extends SetKifu {

    BufferedReader br;
    BonaMethod bm = new BonaMethod("file");
    EvalPlay evalPlay = new EvalPlay("file");
    int learnSide = Othello.BLACK;

    KifuBonaMethod () {
	evalPlay.eval = bm;
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
	bm.saveEval(dir);
    }

    public void kifuPlay () {
	Othello othello = new Othello();
	int count = 0;
	othello.init();
	while (othello.STATE != Othello.END) {
	    int nextMove = next(othello);
	    if (othello.STATE == learnSide && evalPlay.next(othello) != nextMove) {
		ArrayList<Othello> othelloList = new ArrayList<Othello>();
		Othello bestNextBan = new Othello();
		bestNextBan.setBanInfo(othello);
		bestNextBan.nextHand(nextMove);
		othelloList.add(bestNextBan);
		ArrayList<Integer> canMove = othello.checkMove();
		for (int j = 0; j < canMove.size(); j++) {
		    Othello preOthello = new Othello();
		    preOthello.setBanInfo(othello);
		    preOthello.nextHand(canMove.get(j));
		    othelloList.add(preOthello);
		}
		bm.learnEvaluation(othelloList);
		othello.nextHand(nextMove);
	    } else {
		othello.nextHand(nextMove);
	    }
	}
    }
    
    public static void main(String[] args) {
	KifuBonaMethod lk = new KifuBonaMethod();
	for (int i = 0; i < 200; i++) {
	    String dir = String.format("resultFile/method/file/eval%d", i);
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
