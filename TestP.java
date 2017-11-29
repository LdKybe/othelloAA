import java.util.*;

public class TestP extends Othello{
    GameNode root;
    int COUNT=20000;

    public int next(Othello o) {
	createRootNode(o.ban, o.STATE);
	playOut();
	int max = 0;
	for (int i = 0; i<root.child.size();i++) {
	    if(root.child.get(i).playNum > root.child.get(max).playNum) {
		max = i;
	    }
	    root.child.get(i).printNodeInfo();
	}
	System.out.println("***********************");
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
	    //System.out.println(count);
	    root.play();
	    count++;
	}
    }

    public static void main(String args[]) {
	Othello othello = new OthelloPlay();
	TestP testp = new TestP();
    }
}
