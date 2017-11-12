import java.util.*;

public class RandomOthello extends Othello {

    Random rnd;

    RandomOthello(){
	rnd = new Random();
    }
     
    public int next(int b[], int sta) {
	//printBan();
	this.ban = b;
	if(sta == WHITE) {
	    int size = checkWhite().size();
	    if ( size > 0 ) return checkWhite().get(rnd.nextInt(size));
	} else if(sta == BLACK) {
	    int size = checkBlack().size();
	    if (size > 0 ) return checkBlack().get(rnd.nextInt(size));
	}
	return 0;
    }
}
