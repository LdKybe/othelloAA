import java.util.*;

public class RandomOthello extends Othello {


    RandomOthello(){
    }

    RandomOthello (int[] b, int state) {
	this();
	this.banUpdate(b, state);
    }

    public void banUpdate (int[] b, int state){
	for (int i=0; i<b.length; i++) {
	    this.ban[i] = b[i];
	}
	this.STATE = state;
    }
     
    public int randomNext(int[] b, int state) {
	banUpdate(b,state);
	if(this.STATE == WHITE) {
	    int size = checkWhite().size();
	    if ( size > 0 ) return checkWhite().get(rnd.nextInt(size));
	} else if(this.STATE == BLACK) {
	    int size = checkBlack().size();
	    if (size > 0 ) return checkBlack().get(rnd.nextInt(size));
	}
	return 0;
    }

    public int randomNext() {
	return randomNext(this.ban, this.STATE);
    }

    public int next(int[] b, int state) {
	return randomNext(b ,state);
    }

    public static void main(String[] args) {
	OthelloPlay othello = new OthelloPlay();
	RandomOthello ran = new RandomOthello();
	while(othello.STATE != END) {
	    othello.nextHand(ran.next(othello.ban, othello.STATE));
	    othello.printBan();
	}
    }
}
