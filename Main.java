import java.util.*;

public class Main {
    static int COUNT = 5000; //試行回数の指定
    public static void main (String[] args) {
	System.out.println("オセロプレイ" + COUNT + "回");
	int count = 0;
	int count2 = 0;
	int blackWin = 0;
	int whiteWin = 0;
	OthelloPlay othello = new OthelloPlay();//オセロプレイ用のクラス
	MonteCarlo monteCarlo = new MonteCarlo(5);//モンテカルロプレイヤクラス
	MonteCarlo monte2 = new MonteCarlo(20000);//モンテカロプレイヤクラス
	EvalPlay eval = new EvalPlay(1);
	EvalPlay eval2 = new EvalPlay(1);
	RandomOthello rand = new RandomOthello();
	TestP test = new TestP();
	Random rnd = new Random();
	TDLambda tdLambda = new TDLambda(1);
	TDLambda tdLambda2 = new TDLambda(1);
	eval.eval.pe = eval2.eval.pe;
	tdLambda.eval.pe = tdLambda2.eval.pe;
	while (count2 < COUNT) {
	    int playside = rnd.nextInt(2);
	    if (playside%2==0 ) {
		while(othello.getState() != othello.END) {
		    //othello.printBan();
		    if(othello.getState() == othello.BLACK){
			//othello.nextHand(eval.next(othello));
			othello.nextHand(tdLambda.next(othello));
		    } else if (othello.getState() == othello.WHITE) {
			othello.nextHand(tdLambda2.next(othello));
			//othello.nextHand(eval2.next(othello));
			//othello.nextHand(rand.next(othello.ban, othello.STATE));
			//othello.nextHand(test.next(othello));
			
		    }
		}
	    } else if (playside%2!=0) {
		while(othello.getState() != othello.END) {
		    //othello.printBan();
		    if(othello.getState() == othello.WHITE){
			//othello.nextHand(eval.next(othello));
			othello.nextHand(tdLambda.next(othello));
		    } else if (othello.getState() == othello.BLACK) {
			othello.nextHand(tdLambda2.next(othello));
			//othello.nextHand(eval2.next(othello));
			//othello.nextHand(rand.next(othello.ban, othello.STATE));
			
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
	    if(count%1000 == 0 ){
		System.out.println("定期報告: " + count2 + " 回目 " + "evalWin: " + (double)blackWin/count + " randWin: " + (double)whiteWin/count + "差: " + (blackWin - whiteWin));
		count = 0;
		blackWin = 0;
		whiteWin = 0;
	    }

	    if (count2 % 100000 == 0) {
		//eval2.eval.init();
	    }
	    
	}
	//System.out.println("blackWin: " + (double)blackWin/count + " whiteWin: " + (double)whiteWin/count);
    }
}
