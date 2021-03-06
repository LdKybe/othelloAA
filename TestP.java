import java.util.*;

public class TestP extends Othello implements OthelloPlayer{
    GameNode root;
    int COUNT=4000;

    TestP() {}
    TestP(int count) {
	COUNT = count;
    }

    public int next(Othello o) {
	createRootNode(o.ban, o.STATE);
	playOut();
	int max = 0;
	for (int i = 0; i<root.child.size();i++) {
	    if(root.child.get(i).playNum > root.child.get(max).playNum) {
		max = i;
	    }
	    //root.child.get(i).printNodeInfo();
	}
	//System.out.println("***********************");
	return root.child.get(max).nHand;
    }
    
    public void createRootNode(int b[], int sta) {
	root = new GameNode();
	root.banUpdate(b, sta);
	root.expNode();
    }

    public void playOut(){
	
	int count = 0;
	while(COUNT > count) {
	    root.play();
	    count++;
	}
    }

    public static void main(String args[]) {
	Othello othello = new OthelloPlay();
	TestP testp = new TestP();
    }
}
