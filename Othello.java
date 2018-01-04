import java.io.*;
import java.util.*;

public class Othello {
    static Random rnd = new Random(System.currentTimeMillis());
    static int COUNT = 1000;
    static int BLANK = 0;
    static int BLACK = 1;
    static int WHITE = 2;
    static int WALL = 3;
    static int END = 4;
    int ban[] = new int[100];
    int STATE;
    int moveCount = 0;

    Othello (int[] b, int state) {
	for (int i=0; i<ban.length; i++) {
	    this.ban[i] = b[i];
	}
	this.STATE = state;
    }

    Othello(){
	ban = new int[100];
    }


    public int getState() {
	return STATE;
    }

    public void passEndCheck() {
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

    //次の手を指定すると、stateに応じたプレイヤがプレイを行う。
    public int nextHand(int x) {
	int flipNum = 0;
	if (this.STATE == BLACK) {
	    flipNum = this.flip_black(x);
	    if( flipNum > 0) {
		moveCount++;
		this.STATE = WHITE;
	    }
	} else if (this.STATE == WHITE) {
	    flipNum = this.flip_white(x);
	    if(flipNum > 0){
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

    public int getWinner() {
	if (whiteNum() > blackNum() ) {
	    return WHITE;
	} else if (blackNum() > whiteNum() ) {
	    return BLACK;
	} else {
	    return 0;
	}
    }

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
	//System.out.println(count);
	return count;
	
    }

    public int flip_line_black(int x, int dir) {

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

    public int flip_line_white(int x, int dir) {

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

    public int[] getBan() {
	return ban;
    }

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

    public int checkBlackLine(int x, int dir) {
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

    public int checkWhiteLine(int x, int dir) {
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
