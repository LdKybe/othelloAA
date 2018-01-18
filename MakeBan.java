public class MakeBan {
    public static void main(String args[]) {
	OthelloPlay othello = new OthelloPlay(1);
	EvalPlay oPlayer1 = new EvalPlay(1, 0.1);
	EvalPlay oPlayer2 = new EvalPlay(1, 0.1);
	othello.playOthello(oPlayer1, oPlayer2, 100000, 1);
    }
}
