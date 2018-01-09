import java.io.*;
import java.util.*;

public class Concordance extends LearnKifu {

    
    public void concordanceRate () {

	int conCount = 0;
	int count = 0;
	
	Othello othello = new Othello();
	othello.init();
	while (othello.STATE != END) {
	    int nextMove = next(othello);
	    int evalMove = maxEvalMove(othello);
	    if (othello.STATE == learnSide ) {
		othello.nextHand(nextMove);
	    } else {
		othello.nextHand(nextMove);
	    }
	    
	    if (nextMove == evalMove) {
		conCount++;
	    }
	    count++;
	}
	System.out.println(conCount);

    }
    public static void main(String[] args) {
	Concordance c  = new Concordance();
	c.setKifuFile();
	c.setKifuData();
	c.concordanceRate();
    }

    
}
