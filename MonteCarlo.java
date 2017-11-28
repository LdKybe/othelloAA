import java.util.*;

public class MonteCarlo extends RandomOthello {

    RandomOthello ranOthello;//ランダムオセロプレイクラス
    int COUNT = 0;//試行回数

    MonteCarlo(){
	ranOthello = new RandomOthello();
    }
    MonteCarlo(int i){
	this();
	COUNT = i;
    }

    //モンテカルロ法次の手を決める
    public int MonteCarloNext(int b[], int sta) {
	int count = 0;
	int blackWin = 0;
	int whiteWin = 0;
	int[] winHand = new int[100];
	while (count < COUNT) {
	     banUpdate(b, sta);
	    int nextHand = randomNext();
	    this.nextHand(nextHand);
	    int win = this.randomPlay();
	    
	    if(sta == BLACK && win == BLACK) winHand[nextHand]++;
	    if(sta == WHITE && win == WHITE ) winHand[nextHand]++;
	    count++;
	}
	int max = 0;
	for (int i = 0; i < winHand.length; i++) {
	    if (winHand[max] < winHand[i]) {
		max = i;
	    }
	}
	if(max == 0) {
	    return ranOthello.next(b, sta);//勝つ手が見つからなかった場合、ランダム
	}
	return max;

    }

    public int next(int b[], int sta) {
	return MonteCarloNext(b,sta);
    }

    public int randomPlay() {
	RandomOthello othello = new RandomOthello(this.ban, this.STATE);
	while(othello.getState() != othello.END) {
	    othello.nextHand(othello.randomNext(othello.ban, othello.STATE));
	    // othello.printBan();
	}
	if(othello.blackNum() > othello.whiteNum() ) return othello.BLACK;
	if(othello.blackNum() < othello.whiteNum() ) return othello.WHITE;
	return 0;
    }

    

    
}
    
