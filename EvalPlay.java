import java.util.*;

public class EvalPlay extends Othello implements OthelloPlayer{
    Evaluation eval; //評価値に関するクラス
    Othello preOthello = new Othello();//1手前のゲーム状況を格納するクラス
    int LEARN = 0;//プレイ方法に関するパラメータ
    int learnCount = 0;//どのくらいの盤面数学習したか格納するパラメータ
    double Epsilon = 0.25; //sarsaにてどの程度の確率でランダム行動するか

    //コンストラクタ、int型にてプレイ方法を指定する。
    EvalPlay(){
	eval = new Evaluation();
    }

    EvalPlay(int learn){
	eval = new Evaluation();
	LEARN = learn;
    }
    EvalPlay(int learn, double epsilon){
	eval = new Evaluation();
	LEARN = learn;
	Epsilon = epsilon;
    }

    public int next (Othello othello) {
	if (LEARN == 1) {
	    return nextSarsaE(othello, Epsilon );
	}
	return maxEvalMove(othello);
    }

    public void connectEval (EvalPlay ep) {
	this.eval = ep.eval;
    }

    //sarsa法の方策ε-greedyを行う
    public int nextSarsaE (Othello othello, double e) {
	int nextMove = 0;
	setBanInfo(othello);
	if(rnd.nextInt(1000) < e*1000) {
	    nextMove = this.checkMove().get(rnd.nextInt(this.checkMove().size()));
	} else {
	    nextMove = maxEvalMove(othello);
	}
	learnEval(othello, nextMove);
	return nextMove;
    }

    //評価値最大の行動を返すメソッド
    public int maxEvalMove(Othello othello ) {
	setBanInfo(othello);
	ArrayList<Integer> array = new ArrayList<Integer>();
	ArrayList<Integer> nextEval = new ArrayList<Integer>();

	array = this.checkMove();//合法手の取得

	//各合法手の評価を取得
	for (int i = 0; i < array.size(); i++) {
	    setBanInfo(othello);
	    this.nextHand(array.get(i));
	    nextEval.add(eval.patternEvaluation(this));
	}

	//評価の高い手を選択
	int max = 0;
	for (int i = 1; i < nextEval.size(); i++) {
	    if (nextEval.get(max) < nextEval.get(i)){
		max = i;
	    }
	}
	return array.get(max);
    }
    
    //盤面を保存するメソッド
    public void savePreOthello (int nextMove){
	this.nextHand(nextMove);
	preOthello.setBanInfo(this);
    }
    public void savePreOthello (Othello othello, int nextMove) {
	setBanInfo(othello);
	savePreOthello(nextMove);
    }

    //学習に関する操作をするクラス。
    //10万盤面学習すると、ファイルに書き込む。
    public void learnEval (Othello othello, int nextMove) {
	setBanInfo(othello);
	this.nextHand(nextMove);
	if (moveCount > 2) learnEval(this);//学習
	this.savePreOthello(othello, nextMove);//盤面記録
    }
    public void learnEval(Othello othello) {
	learnEval(preOthello, othello);
    }
    public void learnEval(Othello othello, Othello othello2) {
	learnEval(othello, othello2, 1);
    }
    public void learnEval(Othello othello, Othello othello2, double e) {
	eval.learnEvaluation(othello, othello2, e);
    }
    
    //現在の評価値をファイルに保存するメソッド
    public void saveEval () {
	PatternEval.writePatternFile(eval.pe);
    }
}
