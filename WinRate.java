public class WinRate {
    public static void main (String[] args) {
	OthelloPlay othello = new OthelloPlay();
	TestP oPlayer = new TestP(4000);
	for (int i = 0; i < 10; i++) {
	    String filename = String.format("resultFile/method1/file/eval%d", i * 20 + 19);
	    EvalPlay evalPlay = new EvalPlay(filename);
	    othello.playOthello(evalPlay, oPlayer, 100, 2);
	}
	for (int i = 0; i < 10; i++) {
	    String filename = String.format("resultFile/method1/file+kifu/eval%d", i * 20 + 19);
	    EvalPlay evalPlay = new EvalPlay(filename);
	    othello.playOthello(evalPlay, oPlayer, 100, 2);
	}
	for (int i = 0; i < 10; i++) {
	    String filename = String.format("resultFile/method2/file/eval%d", i * 20 + 19);
	    EvalPlay evalPlay = new EvalPlay(filename);
	    othello.playOthello(evalPlay, oPlayer, 100, 2);
	}
	for (int i = 0; i < 10; i++) {
	    String filename = String.format("resultFile/method2/file+kifu/eval%d", i * 20 + 19);
	    EvalPlay evalPlay = new EvalPlay(filename);
	    othello.playOthello(evalPlay, oPlayer, 100, 2);
	}
    }

}
