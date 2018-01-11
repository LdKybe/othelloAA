import java.util.*;

public class TDLambda extends EvalPlay implements OthelloPlayer{
    ArrayList<Othello> preOthelloBan = new ArrayList<Othello>();
    double E = 0.1;

    TDLambda (){}
    TDLambda (int learn) {
	LEARN = learn;
    }
    
    
    //盤面の保存
    public void savePreOthello (int nextMove){
	this.nextHand(nextMove);
	Othello othello = new Othello();
	othello.setBanInfo(this);
	if (othello.moveCount < 3 ) {
	    preOthelloBan.clear();
	}
	preOthelloBan.add(othello);
    }
    
    public void learnEval(Othello othello) {
	for (int i = 0; i<preOthelloBan.size();i++) {
	    this.learnEval(preOthelloBan.get(i), othello , preOthelloBan.size() - i);
	}
    }
}
