import java.util.*;
import java.io.*;


public class LearnBonaMethod extends Othello {
    ArrayList<Integer> kifu;
    BonaMethod bm = new BonaMethod("file");
    EvalPlay oPlayer1 = new EvalPlay(0, 0.1);
    EvalPlay oPlayer2 = new EvalPlay(0, 0.1);

    LearnBonaMethod() {
	kifu = new ArrayList<Integer>();
    }

    public int playOthello (OthelloPlayer oPlayer1, OthelloPlayer oPlayer2) {
	this.init();
	while(this.getState() != this.END) {
	    if(this.getState() == this.BLACK){
		this.nextHand(oPlayer1.next(this));
	    } else if (this.getState() == this.WHITE) {
		this.nextHand(oPlayer2.next(this));
	    }
	}
	int winner = 0;
	if(this.blackNum() > this.whiteNum() ) {
	    winner = BLACK;
	}else if(this.blackNum() < this.whiteNum() ) {
	    winner = WHITE;
	}
	return winner;
    }
    
    public int nextHand(int x) {
 	int flipNum = super.nextHand(x);
	if (flipNum > 0) {
	    recordKifu(x);
	}
	return flipNum;
    }

    public void recordKifu(int x) {
	kifu.add(x);
    }

    public void learnEval() {
	oPlayer1.eval.pe = bm.pe;
	oPlayer2.eval.pe = bm.pe;
	int winner = playOthello(oPlayer1, oPlayer2);
	init();
	for (int i = 0; i < kifu.size(); i++ ) {
	    if (winner == this.STATE && oPlayer1.next(this) != kifu.get(i)) {
		ArrayList<Othello> othello = new ArrayList<Othello>();
		Othello bestNextBan = new Othello();
		bestNextBan.setBanInfo(this);
		bestNextBan.nextHand(kifu.get(i));
		othello.add(bestNextBan);
		ArrayList<Integer> canMove = checkMove();
		
		for (int j = 0; j < canMove.size(); j++) {
		    Othello preOthello = new Othello();
		    preOthello.setBanInfo(this);
		    preOthello.nextHand(canMove.get(j));
		    othello.add(preOthello);
		}
		bm.learnEvaluation(othello);
	    }	    
	    this.nextHand(kifu.get(i));
	}
	kifu.clear();
    }

    public static void main (String args[]) {
	LearnBonaMethod lbm = new LearnBonaMethod();
	OthelloPlay othello = new OthelloPlay();
	int count = 0;
	while(count < 1000000) {
	    lbm.learnEval();
	    count++;
	    if (count % 100 == 0) {
		System.out.print("*");
	    }
	    if (count % 1000 == 0) {
		System.out.println();
		EvalPlay oPlayer1 = new EvalPlay(0);
		RandomOthello oPlayer2 = new RandomOthello();
		OthelloPlay op = new OthelloPlay();
		op.playOthello(oPlayer1, oPlayer2, 1000);
		PatternEval.writePatternFile(lbm.bm.pe, "file");
	    }
	}
    }
}
