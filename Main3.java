import java.util.*;

public class Main3 {
    static int COUNT = 1000000; //試行回数の指定
    public static void main (String[] args) {
	System.out.println("オセロだよー" + COUNT + "回");
	int count = 0;
	int count2 = 0;
	int blackWin = 0;
	int whiteWin = 0;
	OthelloPlay othello = new OthelloPlay();//オセロプレイ用のクラス
	MonteCarlo monteCarlo = new MonteCarlo(5);//モンテカルロプレイヤクラス
	MonteCarlo monte2 = new MonteCarlo(20000);//モンテカルロプレイヤクラス
	EvalPlay eval = new EvalPlay(0);
	EvalPlay eval2 = new EvalPlay(0);
	RandomOthello rand = new RandomOthello();
	TestP test = new TestP();
	Random rnd = new Random();
	//eval.eval.pe = eval2.eval.pe;
	while (count2 < COUNT) {
	    int playside = rnd.nextInt(2);
	    if (playside%2==0 ) {
		while(othello.getState() != othello.END) {
		    //othello.printBan();
		    if(othello.getState() == othello.BLACK){
			othello.nextHand(eval.next(othello));
		    } else if (othello.getState() == othello.WHITE) {
			//othello.nextHand(eval2.next(othello));
			othello.nextHand(rand.next(othello.ban, othello.STATE));
			
			//othello.nextHand(test.next(othello));
			
		    }
		}
	    } else if (playside%2!=0) {
		while(othello.getState() != othello.END) {
		    //othello.printBan();
		    if(othello.getState() == othello.WHITE){
			othello.nextHand(eval.next(othello));
		    } else if (othello.getState() == othello.BLACK) {
			//othello.nextHand(eval2.next(othello));
			othello.nextHand(rand.next(othello.ban, othello.STATE));
			
			//othello.nextHand(test.next(othello));
		    }
		}
	    }
	    String str = "";
	    if (playside%2==0) {
		if(othello.blackNum() > othello.whiteNum() ) {
		    blackWin++;
		    //str = "black";
		}else if(othello.blackNum() < othello.whiteNum() ) {
		    whiteWin++;
		    //str = "white";
		}
	    } else if (playside%2!=0) {
		if(othello.blackNum() < othello.whiteNum() ) {
		    blackWin++;
		    //str = "black";
		}else if(othello.blackNum() > othello.whiteNum() ) {
		    whiteWin++;
		    //str = "white";
		}
	    }
	    othello.init();
	    count++;
	    count2++;
	    //System.out.println(count + "回目 " + str + " win!");
	    if(count%2000 == 0 ){
		System.out.println("定期報告: " + count2 + " 回目 " + "evalWin: " + (double)blackWin/count + " randWin: " + (double)whiteWin/count + "差: " + (blackWin - whiteWin));
		count = 0;
		blackWin = 0;
		whiteWin = 0;
	    }

	    if (count2 % 10000 == 0) {
		eval.eval.init();
	    }
	    
	}
	//System.out.println("blackWin: " + (double)blackWin/count + " whiteWin: " + (double)whiteWin/count);
    }
}
