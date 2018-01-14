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
    int MONTE_STR = 2000;
    EvalPlay eval, eval2;
    
    Gamen(){
	eval = new EvalPlay();
	eval2 = new EvalPlay();
	othello = new OthelloPlay(1);
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
	System.out.println(othello.prePlayer);
	int y = num % 8 + 1;
	int x = num / 8 + 1;
	if(othello.getState() == othello.WHITE) {
	    othello.nextHand(eval.next(othello));
	    //othello.nextHand(testp.next(othello));
	} else {
	    //System.out.println(eval.eval.patternEvaluation(othello));
	    othello.nextHand(y * 10 + x);
	    //othello.nextHand(testp.next(othello.ban, othello.STATE));
	    
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



	
