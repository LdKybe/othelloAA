public class EvalUpdate {
    public static int learnNum = 10000;
    public static int writeNum = 1;
    
    public static void main (String[] args) {
	OthelloPlay othello = new OthelloPlay();
	//EvalPlay oPlayer1 = new EvalPlay(1);
	//EvalPlay oPlayer2 = new EvalPlay(1);
	TDLambda oPlayer1 = new TDLambda(0);
	TDLambda oPlayer2 = new TDLambda(0);
	EvalPlay oPlayer3 = new EvalPlay(0);
	RandomOthello oPlayer4 = new RandomOthello();
	oPlayer1.connectEval(oPlayer1);
	oPlayer2.connectEval(oPlayer1);
	oPlayer3.connectEval(oPlayer1);
	int count = 1;
	while (count <= learnNum){
	    othello.playOthello(oPlayer1, oPlayer2, 2000, 0);
	    //othello.playOthello(oPlayer1, oPlayer3, 500, 0);
	    System.out.print("*");
	    if (count % writeNum == 0) {
		System.out.println();
		othello.playOthello(oPlayer3, oPlayer4, 5000);
		oPlayer1.saveEval();
		System.out.print("save file ");
		System.out.println("(" + count + "/" + learnNum + ")");
	    }
	    count++;
	}
    }
}
