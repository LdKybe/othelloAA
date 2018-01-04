import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.*;

class Gamen extends JFrame{
    
    OthelloPanel[] panel = new OthelloPanel[64];
    Othello othello;
    MonteCarlo monte;
    RandomOthello rand;
    TestP testp;
    TestP testp2;
    int MONTE_STR = 20000;
    Evaluation eval;
    
    Gamen(){
	eval = new Evaluation();
	eval.init();
	othello = new OthelloPlay();
	testp = new TestP();
	testp2 = new TestP();
	monte = new MonteCarlo(MONTE_STR);
	rand = new RandomOthello();
	this.setLayout(new GridLayout(8,8));
	for (int i=0; i<panel.length; i++) {
	    this.panel[i] = new OthelloPanel(i, this);
	    this.add(this.panel[i]);
	}
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setBounds(10, 10, 650, 650);
	this.setVisible(true);
    }

    public void newGame() {
    }

    public void clicked (int num) {
	System.out.println(eval.patternEvalution(othello));
	int y = num % 8 + 1;
	int x = num / 8 + 1;
	if(othello.getState() == othello.BLACK) {
	    othello.nextHand(testp.next(othello));
	} else {
	    othello.nextHand(y * 10 + x);
	}
	banUpdate();
	if(othello.STATE == othello.END) {
	    System.out.println(othello.blackNum());
	    othello.init();
	    
	}
	
    }

    public void banUpdate() {
	int[] ban = othello.getBan();
	for (int i=1; i<9; i++) {
	    for (int j=1; j<9; j++) {
		this.panel[(j+ i*8) -9].toBlank();
		if(ban[i + j*10] == othello.BLACK) {
		    this.panel[(j+ i*8) -9].toBlack();
		}else if (ban[i + j*10] == othello.WHITE) {
		    this.panel[(j+ i*8) -9].toWhite();
		}
	    }
	}
    }
    
    public static void main(String args[]){
	 new Gamen();
    }
    
}



	
