public class EvalPlay extends Othello {
    Evaluation eval;

    EvalPlay(){
	eval = new Evaluation();
    }

    public int setBanInfo(Othello othello) {
	for (int i = 0; i<this.ban.length; i++) {
	    this.ban[i] = othello.ban[i]
	}
	this.STATE = othello.STATE;
    }

    public int next(Othello othello) {
	setBanInfo(othello);
	
	ArrayList<Integer> array = new ArrayList<Integer>();
	if(this.STATE = BLACK){
	    array = checkBlack();
	}else if(this.STATE = WHITE){
	    array = checkWhite();
	}
	for (int i = 0; i < array.size(); i++) {
	    setBanInfo(othello);
	    this.nextHand(array.get(i));
	}
    }
    
}
