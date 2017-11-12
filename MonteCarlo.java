import java.util.*;

public class MonteCarlo extends Othello {

    Random rnd;
    RandomOthello ranOthello;//ランダムオセロプレイクラス
    int COUNT = 0;//試行回数

    MonteCarlo(){
	rnd = new Random();
	ranOthello = new RandomOthello();
    }
    MonteCarlo(int i){
	this();
	COUNT = i;
    }

    //モンテカルロ法次の手を決める
    public int next(int b[], int sta) {
	int count = 0;
	int blackWin = 0;
	int whiteWin = 0;
	Othello othello = new Othello(0);
	int[] winHand = new int[100];
	while (count < COUNT) {
	    //盤面をコピー
	    for (int i = 0; i < othello.ban.length; i++) {
	    othello.ban[i] = b[i];
	    }
	    
	    othello.state = sta;
	    int nh = ranOthello.next(othello.ban, othello.state);//最初の1手を記録
	    othello.nextHand(nh);
	    while(othello.getState() != othello.END) {
		othello.nextHand(ranOthello.next(othello.ban, othello.state));
	    }
	    
	    if(sta == BLACK && othello.blackNum() > othello.whiteNum() ) winHand[nh]++;
	    if(sta == WHITE && othello.blackNum() < othello.whiteNum() ) winHand[nh]++;
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

    public static void main(String args[]) {
	Othello othello = new Othello(0);
	MonteCarlo monte = new MonteCarlo();
	System.out.print(monte.next(othello.ban, othello.state));	
    }
}
    
