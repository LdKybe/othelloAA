import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.*;

class Gamen extends JFrame{
    
    OthelloPanel[] panel = new OthelloPanel[64];
    Othello othello;
    
    Gamen(){
	othello = new Othello();
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
	int y = num % 8 + 1;
	int x = num / 8 + 1;
	othello.nextHand(y * 10 + x);
	banUpdate();
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



	
