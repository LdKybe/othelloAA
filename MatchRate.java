import java.io.*;
import java.util.*;

public class MatchRate extends LearnKifu {
    EvalPlay ePlay = new EvalPlay(0);
    int playSide = Othello.BLACK;
    int[][] matchRate = new int[2][61];
    
    
    public int getMatchCount () {
	
	int count = 0;
	
	Othello othello = new Othello();
	othello.init();
	while (othello.STATE != Othello.END) {
	    int nextMove = next(othello);
	    int evalMove = 0;
	    if (othello.STATE == playSide ) {
		evalMove = ePlay.maxEvalMove(othello);
		othello.nextHand(nextMove);
	    } else {
		othello.nextHand(nextMove);
	    }
	    if (nextMove == evalMove) {
		count++;
	    }
	}
	return count;
    }
    
    public void checkMatchRate () {
	while (kifuNum > playKifuNum ) {
	    checkMatch();
	    playKifuNum++;
	}
	for (int i = 0; i < matchRate[1].length; i++) {
	    System.out.print(matchRate[0][i] + " ");
	}
	System.out.println();
	for (int i = 0; i < matchRate[1].length; i++) {
	    System.out.print(matchRate[1][i] + " ");
	}
	System.out.println();
    }
    
    public void checkMatch () {
	Othello othello = new Othello();
	othello.init();
	while (othello.STATE != Othello.END) {
	    int nextMove = next(othello);
	    int evalMove = 0; 
	    if (othello.STATE == playSide ) {
		evalMove = ePlay.maxEvalMove(othello);
		matchRate[1][othello.moveCount]++;
	    
		if (nextMove == evalMove) {
		    matchRate[0][othello.moveCount]++;
		}
	    }
	    othello.nextHand(nextMove);
	}
    }
    

    public boolean isMatch(int kNum, int step) {
	Othello othello = new Othello();
	othello.init();
	while ( othello.moveCount == step-1 ) {
	    othello.nextHand (next(othello, kNum));
	}
	int evalMove = 0;
	if (othello.STATE == playSide) {
	    evalMove = ePlay.maxEvalMove(othello);
	}
	
	int kifuMove = next(othello, kNum);
	if (evalMove == kifuMove) {
	    return true;
	} else {
	    return false;
	}
	
    }
    
    public static void main(String[] args) {
	MatchRate mr  = new MatchRate();
	mr.setKifuData();
	mr.checkMatchRate();
	
    }

    
}
