import java.util.*;

public class MonteCarlo extends Othello {

    Random rnd;
    RandomOthello ranOthello;//�����_���I�Z���v���C�N���X
    int COUNT = 0;//���s��

    MonteCarlo(){
	rnd = new Random();
	ranOthello = new RandomOthello();
    }
    MonteCarlo(int i){
	this();
	COUNT = i;
    }

    //�����e�J�����@���̎�����߂�
    public int next(int b[], int sta) {
	int count = 0;
	int blackWin = 0;
	int whiteWin = 0;
	Othello othello = new Othello(0);
	int[] winHand = new int[100];
	while (count < COUNT) {
	    //�Ֆʂ��R�s�[
	    for (int i = 0; i < othello.ban.length; i++) {
	    othello.ban[i] = b[i];
	    }
	    
	    othello.state = sta;
	    int nh = ranOthello.next(othello.ban, othello.state);//�ŏ���1����L�^
	    othello.nextHand(nh);
	    while(othello.getState() != othello.END) {
		othello.nextHand(ranOthello.next(othello.ban, othello.state));
	    }
	    
	    if(sta == BLACK && othello.blackNum() > othello.whiteNum() ) winHand[nh]++;
	    if(sta == WHITE && othello.blackNum() < othello.whiteNum() ) winHand[nh]++;
	    count++;
	}
	int max = 0;
	for (int i = 0; i < winHand.length; i++) {
	    if (winHand[max] < winHand[i]) {
		max = i;
	    }
	}
	if(max == 0) {
	    return ranOthello.next(b, sta);//���肪������Ȃ������ꍇ�A�����_��
	}
	return max;

    }

    public static void main(String args[]) {
	Othello othello = new Othello(0);
	MonteCarlo monte = new MonteCarlo();
	System.out.print(monte.next(othello.ban, othello.state));	
    }
}
    
