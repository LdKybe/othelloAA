public class Main {
    static int COUNT = 1000; //試行回数の指定
    public static void main (String[] args) {
	System.out.println("オセロだよー" + COUNT + "回");
	int count = 0;
	int blackWin = 0;
	int whiteWin = 0;
	OthelloPlay othello = new OthelloPlay();//オセロプレイ用のクラス
	MonteCarlo monteCarlo = new MonteCarlo(5);//モンテカルロプレイヤクラス
	MonteCarlo monte2 = new MonteCarlo(2000);//モンテカルロプレイヤクラス
	TestP test = new TestP();
	while (count < COUNT) {
	    while(othello.getState() != othello.END) {
		if(othello.getState() == othello.BLACK){
		    othello.nextHand(test.next(othello));
		} else if (othello.getState() == othello.WHITE) {
		    othello.nextHand(monteCarlo.next(othello.getBan(), othello.getState()));
		}
	    }
	    String str = "";
	    if(othello.blackNum() > othello.whiteNum() ) {
		blackWin++;
		str = "black";
	    }else if(othello.blackNum() < othello.whiteNum() ) {
		whiteWin++;
		str = "white";
	    }
	    othello.init();
	    count++;
	    System.out.println(count + "回目 " + str + " win!");
	    if(count%10 == 0 ){
		System.out.println("定期報告 blackWin: " + (double)blackWin/count + " whiteWin: " + (double)whiteWin/count);
	    }
	}
	System.out.println("blackWin: " + (double)blackWin/count + " whiteWin: " + (double)whiteWin/count);
    }
}
