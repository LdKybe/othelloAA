import java.io.*;
import java.util.*;

public class MatchRate extends LearnKifu {
    EvalPlay ePlay;
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
    public void checkMatchRate (String eval, String filename) {
	ePlay = new EvalPlay(eval);
	setKifuFile(filename);
	setKifuData();
	playKifuNum = 0;
	matchRate = new int[2][61];
	while (kifuNum > playKifuNum ) {
	    checkMatch();
	    playKifuNum++;
	}
	//outputMatchRate ("matchTest.txt");
    }

    public void outputMatchRate (String name) {
	try {
	    String filename = name;
	    PrintWriter pw = new PrintWriter (new BufferedWriter(new FileWriter(new File(filename), true)));
	    int matchNum = 0;
	    int moveNum = 0;
	    
	    for (int i = 0; i < matchRate[0].length; i++ ) {
		matchNum += matchRate[0][i];
		moveNum += matchRate[1][i];
	    }
	    pw.println(matchNum +  "," + moveNum + "," + ((double)matchNum/(double)moveNum));
	    
	    for (int i = 0; i < matchRate[0].length; i++ ) {
		//pw.println("match: " + matchRate[0][i] +  " move: " + matchRate[1][i] + " matchRate: " + ((double)matchRate[0][i]/(double)matchRate[1][i]));
	    }
	    pw.close();
	    
	} catch (Exception e) {
	    System.out.println("書き込みに失敗");
	}
    }
    
    public void checkMatch () {
	Othello othello = new Othello();
	othello.init();
	//ePlay = new ePlay(filename);
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
	mr.checkMatchRate("file", "./kifu/testData/senkou.txt");
	mr.checkMatchRate("file", "./kifu/testData/koukou.txt");
	mr.outputMatchRate ("resultFile/method/file+kifu/matchTest.csv");
	for (int i = 0; i < 200; i++) {
	    String filename = String.format("resultFile/method/file+kifu/eval%d", i);
	    mr.checkMatchRate(filename, "./kifu/testData/senkou.txt");
	    mr.checkMatchRate(filename, "./kifu/testData/koukou.txt");
	    mr.outputMatchRate ("resultFile/method/file+kifu/matchTest.csv");
	}
	mr.checkMatchRate("file2", "./kifu/testData/senkou.txt");
	mr.checkMatchRate("file2", "./kifu/testData/koukou.txt");
	mr.outputMatchRate ("resultFile/method/file/matchTest.csv");
	for (int i = 0; i < 200; i++) {
	    String filename = String.format("resultFile/method/file/eval%d", i);
	    mr.checkMatchRate(filename, "./kifu/testData/senkou.txt");
	    mr.checkMatchRate(filename, "./kifu/testData/koukou.txt");
	    mr.outputMatchRate ("resultFile/method/file/matchTest.csv");
	}
    }
}
