import java.util.*;
import java.io.*;


public class OthelloPlay extends Othello {
    ArrayList<Integer> kifu;
    ArrayList<Othello> recoredBan = new ArrayList<Othello>();
    int KIFU = 0;
    
    OthelloPlay() {
	kifu = new ArrayList<Integer>();
	this.init();
	this.STATE = BLACK;
	
    }
    
    OthelloPlay(int kifuNum) {
	kifu = new ArrayList<Integer>();
	this.init();
	KIFU = kifuNum;
    }
    
    public void init() {
	if(this.STATE == END &&  KIFU == 1){
	    if (KIFU == 1) {
		outputBan();
		kifu.clear();
		recoredBan.clear();
	    } else if (KIFU == 2) {
		outputKifu();
		kifu.clear();
		recoredBan.clear();
	    }
	}
	super.init();
    }

    
    public void playOthello (OthelloPlayer oPlayer1, OthelloPlayer oPlayer2, int playNum){
	playOthello (oPlayer1, oPlayer2, playNum, 1);
    }
    public void playOthello (OthelloPlayer oPlayer1, OthelloPlayer oPlayer2, int playNum, int print){
	int player1 = 0;
	int player2 = 0;
	Random rnd = new Random();
	int result = 0;
	int count = 0;
	while (count < playNum) {
	    int playside = rnd.nextInt(2);
	    if (playside == 0) {
		result = playOthello(oPlayer1, oPlayer2);
		if (result == BLACK) player1++;
		if (result == WHITE) player2++;
	    } else {
		result = playOthello(oPlayer2, oPlayer1);
		if (result == BLACK) player2++;
		if (result == WHITE) player1++;
	    }
	    count++;
	}
	if (print == 1) {
	    System.out.println("playNum: " + count + " : " + "player1 win: " + (double)player1/count + " player2 win: " + (double)player2/count);
	}
    }
    
    public int playOthello (OthelloPlayer oPlayer1, OthelloPlayer oPlayer2){
	this.init();
	while(this.getState() != this.END) {
	    if(this.getState() == this.BLACK){
		this.nextHand(oPlayer1.next(this));
	    } else if (this.getState() == this.WHITE) {
		this.nextHand(oPlayer2.next(this));
	    }
	}
	int winner = 0;
	if(this.blackNum() > this.whiteNum() ) {
	    winner = BLACK;
	}else if(this.blackNum() < this.whiteNum() ) {
	    winner = WHITE;
	}
	this.init();
	return winner;
    }
   

    public int nextHand(int x) {
	Othello othello = new Othello();
	othello.setBanInfo(this);
 	int flipNum = super.nextHand(x);
	if (flipNum > 0 && KIFU == 1) {
	    recordKifu(x);
	    recoredBan.add(othello);
	}
	
	return flipNum;
    }

    public void recordKifu(int x) {
	kifu.add(x);
    }

    public void printKifu() {
	for (int i = 0; i<kifu.size(); i++) {
	    System.out.print(kifu.get(i) + " ");
	}
    }
    
    public void outputKifu(){
	outputKifu("temp.txt");
    }
    public void outputKifu(String str){
	PrintWriter pw;
	try {
	    File file = new File(str);
	    pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
	    for (int i = 0; i<kifu.size(); i++) {
		pw.print(kifu.get(i) + " ");
	    }
	    pw.println();
	    pw.close();
	} catch (IOException e) {
	    System.out.println("棋譜ファイルの書き込み失敗");
	} finally {
	    //pw.close();
	}
    }

    public void outputBan() {
	outputBan("temp.txt");
    }
    public void outputBan(String str) {
	PrintWriter pw;
	try {
	    File file = new File(str);
	    pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
	    for (int i = 0; i<kifu.size(); i++) {
		Othello othello = recoredBan.get(i);
		for (int j = 0; j < othello.ban.length; j++ ) {
		    pw.print(othello.ban[j] + " ");
		}
		pw.println();
		pw.println(othello.moveCount);
		pw.println(othello.STATE);
		pw.println(kifu.get(i));
	    }
	    pw.println();
	    pw.close();
	} catch (IOException e) {
	    System.out.println("棋譜ファイルの書き込み失敗");
	} finally {
	    //pw.close();
	}
    }
    
}
