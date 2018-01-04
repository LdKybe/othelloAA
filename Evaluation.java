public class Evaluation {
    static int BLANK = 0;
    static int BLACK = 1;
    static int WHITE = 2;
    static int WALL = 3;
    static int END = 4;
    int ban[] = new int[100];
    int tPlayer;
    PatternEval[] pe = new PatternEval[61];
    int moveCount = 0;
    int ban8[][] = new int[8][100];

    Evaluation() {
	init();
    }

    public void init() {
	for (int i = 0; i < 61; i++ ) {
	    pe[i] = PatternEval.readPatternFile(i);
	}
    }

    public void setGameData(Othello othello) {
	for (int i = 0; i < ban.length; i++) {
	    ban[i] = othello.ban[i];
	}
	tPlayer = othello.STATE;
	moveCount = othello.moveCount;
    }

    public int patternEvalution(Othello othello) {
	setGameData(othello);
	if (tPlayer == WHITE) reverseAll();
	gen8ban();
	int score = 0;
	for (int i = 0; i<4; i++) {
	    score += diag4Eval(ban8[i]);
	    score += diag5Eval(ban8[i]);
	    score += diag6Eval(ban8[i]);
	    score += diag7Eval(ban8[i]);
	    score += diag8Eval(ban8[i]);
	    score += hor1Eval(ban8[i]);
	    score += hor2Eval(ban8[i]);
	    score += hor3Eval(ban8[i]);
	    score += hor4Eval(ban8[i]);
	    score += edgexEval(ban8[i]);
	}
	return score;
	
    }
    public void reverseAll() {
    }

    public void gen8ban () {
	for (int i = 0; i < ban.length; i++) {
	    int x = i/10;
	    int y = i%10;
	    ban8[0][i] = ban[i];
	    ban8[1][y*10+(9-x)] = ban[i];
	    ban8[2][(9-x)*10+(9-y)] = ban[i];
	    ban8[3][(9-y)*10+x] = ban[i];
	}
	for (int i=0; i<ban.length; i++) {
	    ban8[4][99-i] = ban8[0][i];
	    ban8[5][99-i] = ban8[1][i];
	    ban8[6][99-i] = ban8[2][i];
	    ban8[7][99-i] = ban8[3][i];
	}
    }

    public int diag4Eval(int[] b) {
	int pattern = b[14];
	pattern = 3 * pattern + b[23];
	pattern = 3 * pattern + b[32];
	pattern = 3 * pattern + b[41];
	return pe[moveCount].diag4[pattern];
    }
    public int diag5Eval(int[] b) {
	int pattern = b[15];
	pattern = 3 * pattern + b[24];
	pattern = 3 * pattern + b[33];
	pattern = 3 * pattern + b[42];
	pattern = 3 * pattern + b[51];
	return pe[moveCount].diag5[pattern];
    }
    public int diag6Eval(int[] b) {
	int pattern = b[16];
	pattern = 3 * pattern + b[25];
	pattern = 3 * pattern + b[34];
	pattern = 3 * pattern + b[43];
	pattern = 3 * pattern + b[52];
	pattern = 3 * pattern + b[61];
	return pe[moveCount].diag6[pattern];
    }
    public int diag7Eval(int[] b) {
	int pattern = b[17];
	pattern = 3 * pattern + b[26];
	pattern = 3 * pattern + b[35];
	pattern = 3 * pattern + b[44];
	pattern = 3 * pattern + b[53];
	pattern = 3 * pattern + b[62];
	pattern = 3 * pattern + b[71];
	return pe[moveCount].diag7[pattern];
    }
    public int diag8Eval(int[] b) {
	int pattern = b[18];
	pattern = 3 * pattern + b[27];
	pattern = 3 * pattern + b[36];
	pattern = 3 * pattern + b[45];
	pattern = 3 * pattern + b[54];
	pattern = 3 * pattern + b[63];
	pattern = 3 * pattern + b[72];
	pattern = 3 * pattern + b[81];
	return pe[moveCount].diag8[pattern];
    }
    public int hor1Eval(int[] b) {
	int pattern = b[11];
	pattern = 3 * pattern + b[12];
	pattern = 3 * pattern + b[13];
	pattern = 3 * pattern + b[14];
	pattern = 3 * pattern + b[15];
	pattern = 3 * pattern + b[16];
	pattern = 3 * pattern + b[17];
	pattern = 3 * pattern + b[18];
	return pe[moveCount].hor1[pattern];
    }
    public int hor2Eval(int[] b) {
	int pattern = b[21];
	pattern = 3 * pattern + b[22];
	pattern = 3 * pattern + b[23];
	pattern = 3 * pattern + b[24];
	pattern = 3 * pattern + b[25];
	pattern = 3 * pattern + b[26];
	pattern = 3 * pattern + b[27];
	pattern = 3 * pattern + b[28];
	return pe[moveCount].hor2[pattern];
    }
    public int hor3Eval(int[] b) {
	int pattern = b[11];
	pattern = 3 * pattern + b[32];
	pattern = 3 * pattern + b[33];
	pattern = 3 * pattern + b[34];
	pattern = 3 * pattern + b[35];
	pattern = 3 * pattern + b[36];
	pattern = 3 * pattern + b[37];
	pattern = 3 * pattern + b[38];
	return pe[moveCount].hor3[pattern];
    }
    public int hor4Eval(int[] b) {
	int pattern = b[41];
	pattern = 3 * pattern + b[42];
	pattern = 3 * pattern + b[43];
	pattern = 3 * pattern + b[44];
	pattern = 3 * pattern + b[45];
	pattern = 3 * pattern + b[46];
	pattern = 3 * pattern + b[47];
	pattern = 3 * pattern + b[48];
	return pe[moveCount].hor4[pattern];
    }
    public int edgexEval(int[] b) {
	int pattern = b[11];
	pattern = 3 * pattern + b[12];
	pattern = 3 * pattern + b[13];
	pattern = 3 * pattern + b[14];
	pattern = 3 * pattern + b[15];
	pattern = 3 * pattern + b[16];
	pattern = 3 * pattern + b[17];
	pattern = 3 * pattern + b[18];
	pattern = 3 * pattern + b[22];
	pattern = 3 * pattern + b[27];
	return pe[moveCount].edgex[pattern];
	
    }

    public static void main(String[] args) {
	Evaluation eval = new Evaluation();
	for (int i=0; i<eval.ban.length; i++) {
	    eval.ban[i] = i;
	}
	eval.gen8ban();
	for(int x = 0; x<8;x++) {
	    for (int i=0; i<10; i++) {
		for (int j=0; j<10; j++) {
		    System.out.print(eval.ban8[x][i*10 + j] + " ");
		}
		System.out.println();
	    }
	    System.out.println();
	}
	
	
    }
    
}
