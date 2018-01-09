public class TDLEvalUpdate {
    public static int learnNum = 100;
    public static int writeNum = 10;
    
    public static void main (String[] args) {
	OthelloPlay othello = new OthelloPlay();
	TDLambda oPlayer1 = new TDLambda(1);
	TDLambda oPlayer2 = new TDLambda(1);
	EvalPlay oPlayer3 = new EvalPlay(0);
	RandomOthello oPlayer4 = new RandomOthello();
	oPlayer1.connectEval(oPlayer2);
	oPlayer1.connectEval(oPlayer3);
	int count = 1;
	while (count <= learnNum){
	    othello.playOthello(oPlayer1, oPlayer2, 200, 0);
	    othello.playOthello(oPlayer3, oPlayer4, 2000);
	    //System.out.print("*");
	    if (count % writeNum == 0) {
		System.out.println();
		//othello.playOthello(oPlayer3, oPlayer4, 2000);
		oPlayer1.saveEval();
		System.out.println("save file");
	    }
	    count++;
	}
    }
}
