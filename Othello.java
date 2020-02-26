import java.io.*;
import java.util.*;

/**
 *a aaオセロの盤面情報の保存と基本的な機能を格納したクラス
 */
public class Othello {
    /**
     *@param COUNT;

     */
    static Random rnd = new Random(System.currentTimeMillis());
    
    public static final int BLANK = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;
    public static final int WALL = 3;
    public static final int END = 4;
    
    static int COUNT = 1000;
    
    int ban[] = new int[100];
    int STATE; //現在の状態を保持する。どちら手番であるか。終了しているのか。
    int moveCount = 0;
    int prePlayer = 0;


    /**
     * @deprecated
     * クラスを作成する場合に指定された盤面を作成する
     * @param b 盤面情報
     * @param state ゲームの状態
     */
    public Othello (int[] b, int state) {
	for (int i=0; i<ban.length; i++) {
	    this.ban[i] = b[i];
	}
	this.STATE = state;
    }

    /**
     * クラスを作成し、オセロの盤面を初期化する
     */
    public Othello(){
	//ban = new int[100];
	init();
    }

    
    public int next (int next) {
	return next;
    }

    /**
     * 他クラスのオセロの盤面情報をコピーして保持する。
     * @param othello オセロの情報を保持したOthelloクラス
     */
    public void setBanInfo (Othello othello) {
	for (int i = 0; i<this.ban.length; i++) {
	    this.ban[i] = othello.ban[i];
	}
	this.STATE = othello.STATE;
	this.moveCount = othello.moveCount;
	this.prePlayer = othello.prePlayer;
    }

    /**
     * @return 現在のゲームの状態
     */
    public int getState() {
	return STATE;
    }

    // 手番終了時に次の手番の人がパスか調べる。パスならば手番を変える
    //双方置く場所がない時はEND
    private void passEndCheck() {
	if(this.STATE == BLACK) {
	    if( checkBlack().size() == 0 ) {
	        if (checkWhite().size() == 0) {
		    this.STATE = END;
		    return ;
		}
		this.STATE = WHITE;
	    }
	} else if (this.STATE == WHITE){
	    if( checkWhite().size() == 0 ) {
	        if (checkBlack().size() == 0) {
		    this.STATE = END;
		    return ;
		}
		this.STATE = BLACK;
	    }
	}
	return ;
    }

    

    /**
     *次の手を指定すると、STATEに応じたプレイヤがプレイを行い、その手を反映する。
     * @param x 指定された次の手
     * @return 返した石の数
     */
    public int nextHand(int x) {
	int flipNum = 0;
	if (this.STATE == BLACK) {
	    flipNum = this.flip_black(x);
	    if( flipNum > 0) {
		moveCount++;
		this.prePlayer = BLACK;
		this.STATE = WHITE;
	    }
	} else if (this.STATE == WHITE) {
	    flipNum = this.flip_white(x);
	    if(flipNum > 0){
		this.prePlayer = WHITE;
		this.STATE = BLACK;
		moveCount++;
	    }
	}
	passEndCheck();
	return flipNum;
    }

    public int blackNum () {
	int count = 0;
	for (int i=0; i<this.ban.length;i++) {
	    if(this.ban[i] == BLACK) {
		count++;
	    }
	}
	return count;
    }
    public int whiteNum () {
	int count = 0;
	for (int i=0; i<this.ban.length;i++) {
	    if(this.ban[i] == WHITE) {
		count++;
	    }
	}
	return count;
    }
    public int checkNum(int player) {
	if (player == WHITE) {
	    return blackNum();
	} else if (player == BLACK) {
	    return whiteNum();
	}
	return 0;
    }
    /**
     * 現在の手番の人の合法手を返す
     * @return 合法手のリスト。合法手がないのであれば、からのリストを返す
     */
    public ArrayList<Integer> checkMove() {
	if (this.STATE == BLACK) {
 	    return checkBlack();
	} else if (this.STATE == WHITE) {
	    return checkWhite();
	}
	return new ArrayList<Integer>();
    }

    /**
     * @return 勝者を返す。引き分け時には0を返す。
     */
    public int getWinner() {
	if (whiteNum() > blackNum() ) {
	    return WHITE;
	} else if (blackNum() > whiteNum() ) {
	    return BLACK;
	} else {
	    return 0;
	}
    }

    /**
     * 盤面を初期化。クラス呼び出し時に自動的に呼び出される 
     */
    public void init() {
	for (int i=0; i<100; i++) {
	    ban[i] = WALL;
	}
	for (int i=1; i<9; i++) {
	    for (int j=1; j<9; j++) {
		ban[10*j+i] = BLANK;
	    }
	}
	ban[44] = WHITE;
	ban[54] = BLACK;
	ban[45] = BLACK;
	ban[55] = WHITE;
	this.STATE = BLACK;
	moveCount = 0;
    }
    
    public void printBan() {
	for (int i=0; i<10; i++) {
	    for (int j=0; j<10; j++) {
		System.out.print(ban[10*j+i] + " ");
	    }
	    System.out.println();
	}
    }

    /**
     * 指定された場所が合法手なら石をおく
     * @param x 石の場所
     * @return 石を返した数
     */
    public int flip_black (int x ) {
	int count = 0;
	if(this.ban[x] != 0)return 0;

	count += flip_line_black(x, -11);
	count += flip_line_black(x, -10);
	count += flip_line_black(x,  -9);
	count += flip_line_black(x,  -1);
	count += flip_line_black(x,   1);
	count += flip_line_black(x,   9);
	count += flip_line_black(x,  10);
	count += flip_line_black(x,  11);
	if(count > 0) {
	    this.ban[x] = BLACK;
	}
	return count;
	
    }

    private int flip_line_black(int x, int dir) {

	int count = 0, n = x + dir;
	
	while(this.ban[n]==WHITE) {
	    n += dir;
	}

	if(this.ban[n] != BLACK) {
	    return 0;
	}

	while(n!=x) {
	    this.ban[n] = BLACK;
	    n -= dir;
	    count++;
	}
	return count-1;
    }
    
    /**
     * 指定された場所が合法手なら石をおく
     * @param x 石の場所
     * @return 石を返した数
     */
    public int flip_white (int x ) {
	
	if(this.ban[x] != 0)return 0;
	int count = 0;

	count += flip_line_white(x, -11);
	count += flip_line_white(x, -10);
	count += flip_line_white(x,  -9);
	count += flip_line_white(x,  -1);
	count += flip_line_white(x,   1);
	count += flip_line_white(x,   9);
	count += flip_line_white(x,  10);
	count += flip_line_white(x,  11);
	if (count > 0) {
	    this.ban[x] = WHITE;
	}
	//printBan();
	return count;
	
    }

    private int flip_line_white(int x, int dir) {

	int count = 0, n = x + dir;
	
	while(this.ban[n]==BLACK) {
	    n += dir;
	}

	if(this.ban[n] != WHITE) {
	    return 0;
	}

	while(n!=x) {
	    this.ban[n] = WHITE;
	    n -= dir;
	    count++;
	}

	return count-1;
    }
    /**
     * 盤面を取得する。取得した盤面は自由に操作可能。
     * @return 盤面
     */
    public int[] getBan() {
	int[] ban2 = new int[100];
	for (int i = 0; i < ban.length; i++) {
	    ban2[i] = ban[i];
	    
	}
	return ban2;
    }

    /**
     * 現在の盤面状況における、黒の合法手を調べる
     * @return 合法手のリスト。ない場合は空のリストを返す
     */
    public ArrayList<Integer> checkBlack () {
	ArrayList<Integer> array = new ArrayList<Integer>();
        for (int i = 0; i < ban.length; i++) {
	    if (ban[i] == BLACK ) {
		int x = checkBlackLine(i, -11);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
		x = checkBlackLine(i, -10);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
		x = checkBlackLine(i, -9);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
	        x = checkBlackLine(i, -1);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
		x = checkBlackLine(i, 1);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
		x = checkBlackLine(i, 9);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
		x = checkBlackLine(i, 10);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
		x = checkBlackLine(i, 11);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
	    }
	}

	return array;
    }

    private int checkBlackLine(int x, int dir) {
	int count = 0;
	int n = x + dir;
	while(ban[n] == WHITE) {
	    n += dir;
	    count++;
	}
	if (ban[n] == BLANK && count > 0 ) {
	    return n;
	}
	return -1;
    }

    /**
     * 現在の盤面状況における、黒の合法手を調べる
     * @return 合法手のリスト。ない場合は空のリストを返す
     */
    public ArrayList<Integer> checkWhite() {
	ArrayList<Integer> array = new ArrayList<Integer>();
        for (int i = 0; i < ban.length; i++) {
	    if (ban[i] == WHITE ) {
		int x = checkWhiteLine(i, -11);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
		x = checkWhiteLine(i, -10);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
		x = checkWhiteLine(i, -9);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
	        x = checkWhiteLine(i, -1);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
		x = checkWhiteLine(i, 1);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
		x = checkWhiteLine(i, 9);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
		x = checkWhiteLine(i, 10);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
		x = checkWhiteLine(i, 11);
		if (x != -1 && array.indexOf(x) == -1) array.add(x);
	    }
	}

	return array;
    }

    private int checkWhiteLine(int x, int dir) {
	int count = 0;
	int n = x + dir;
	while(ban[n] == BLACK) {
	    n += dir;
	    count++;
	}
	if (ban[n] == BLANK && count > 0 ) {
	    return n;
	}
	return -1;
    }
}
