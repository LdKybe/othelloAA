public class Evaluation {
    static int BLANK = 0;
    static int BLACK = 1;
    static int WHITE = 2;
    static int WALL = 3;
    static int END = 4;
    static int ban[] = new int[100];
    static int tPlayer;
    static PatternEval[] pe = new PatternEval[61];
    static int moveConut = 0;
    static int ban8[][] = new int[8][100];

    public static void init() {
	for (int i = 0; i < 61; i++ ) {
	    pe[i] = PatternEval.readPatternFile(i);
	}
    }

    public static void setGameData(Othello othello) {
	for (int i = 0; i < ban.length; i++) {
	    ban[i] = othello.ban[i];
	}
	tPlayer = othello.STATE;
	moveCount = othello.moveCount;
    }

    public static int patternEvalution(Othello othello) {
	setGameData(othello);
	if (tPlayer == WHITE) reverseAll();
	int pattern;
	return 0;
    }

    public static void gen8ban () {
	for (int i = 0; i < ban.length; i++) {
	    int x = i/10;
	    int y = i%10;
	    ban8[0][y*10+(9-x)] = ban[i];
	    ban8[1][y*10+(9-x)] = ban[i];
	    ban8[2][(9-y)*10+(9-x)] = ban[i];
	    ban8[3][(9-y)*10+x] = ban[i];
	    ban8[4][i] = ban8[0][99-i];
	    ban8[5][i] = ban8[1][99-i];
	    ban8[6][i] = ban8[2][99-i];
	    ban8[7][i] = ban8[3][99-i];
	    
	}
    }

    public static int diag4Eval() {
	int pattern = ban[14];
	pattern = 3 * pattern + ban[23];
	pattern = 3 * pattern + ban[32];
	pattern = 3 * pattern + ban[41];
	return pe[moveCount].diag[pattern];
    }

    public static void main(String[] args) {
	for (int i=0; i<ban.length; i++) {
	    ban[i] = i;
	}
	gen8ban();
	for(int x = 0; x<8;x++) {
	    for (int i=0; i<10; i++) {
		for (int j=0; j<10; j++) {
		    System.out.print(ban8[x][i*10 + j] + " ");
		}
		System.out.println();
	    }
	    System.out.println();
	}
	
	
    }
    
}
