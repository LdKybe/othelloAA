public class MakeKifuBan extends LearnKifu {

    OthelloPlay othello = new OthelloPlay(1);
    
    public void kifuPlay () {
	//OthelloPlay othello = new OthelloPlay(1);
	int count = 0;
	othello.init();
	while (othello.STATE != Othello.END) {
	    int nextMove = next(othello);
	    if (othello.STATE == learnSide ) {
		othello.nextHand(nextMove);
	    } else {
		othello.nextHand(nextMove);
	    }
	}
	othello.init();
    }
    public static void main(String[] args) {
	MakeKifuBan lk = new  MakeKifuBan();
	lk.setKifuFile();
	lk.setKifuData();
	lk.learn();
    }
}
