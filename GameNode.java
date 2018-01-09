import java.util.*;

public class GameNode extends MonteCarlo {
    GameNode parent = null;//親ノード
    ArrayList<GameNode> child = new ArrayList<GameNode>();//子ノードのリストで
    ArrayList<GameNode> all = new ArrayList<GameNode>();
    int playNum = 0;
    int winNum = 0;
    double UCB = 100;
    double win = 0;
    int nodeNo = 0;
    static int NodeNum = 0;
    int nHand = 0;
    GameNode () {
    }
		 
    GameNode (GameNode p, int[] b) {
	this.parent = p;
	nodeNo = p.nHand;
	NodeNum++;
	for (int i=0; i < this.ban.length; i++) {
	    this.ban[i] = b[i];
	}
    }

    public void addNode(int nexth) {
	GameNode c = new GameNode(this, this.ban);
	child.add(c);
	c.STATE = this.STATE;
	c.nextHand(nexth);
	c.nHand = nexth;
    }

    public void expNode() {
	ArrayList<Integer> nexth = new ArrayList<Integer>();
	if (this.STATE == WHITE) {
	    nexth = checkWhite();
	} else if (this.STATE == BLACK) {
	    nexth = checkBlack();
	}
	for (int i=0; i<nexth.size();i++) {
	    addNode(nexth.get(i));
	}
    }

    public int getWinNum() {
	return this.winNum;
    }

    public GameNode getRoot() {
	if(this.parent == null) {
	    return this;
	}
	return this.parent.getRoot();
    }

    public void getAllNode() {
	for (int i=0; i<this.child.size(); i++) {
	    child.get(i).getAllNode();
	}
	if (this.child.size() == 0) {
	    this.getRoot().all.add(this);
	}
    }
    public GameNode nextRoot(){
	if(parent.parent == null) {
	    return this;
	}
	return this.parent.nextRoot();
    }

    public void play() {
	int max = 0;
	for (int i=0; i<this.child.size();i++) {
	    if( this.child.get(i).UCB >this.child.get(max).UCB ) {
		max = i;
	    }
	}
	if(this.child.get(max).child.size() > 0) {
	    child.get(max).play();
	} else {
	    child.get(max).playOut();
	}
    }
    
    public void playOut() {
	
	int winner = super.randomPlay();
	sendResult(winner);
	
	if(playNum > 15) {
	    expNode();
	}
	getRoot().allUpdateUCB();
    }

    public void sendResult(int winner) {
	if(this.STATE != winner) {
	    this.winNum++;
	}
	this.playNum++;
        
	if (this.parent != null){
	    this.parent.sendResult(winner);
	}
    }

    public void allUpdateUCB() {
	updateUCB();
	for (int i=0; i<this.child.size();i++) {
	    child.get(i).allUpdateUCB();
	}
    }
    
    public void updateUCB() {
	win = (double)winNum/playNum;
	UCB = win + 0.25 * Math.sqrt(2*Math.log(getRoot().playNum)/this.playNum);
	if (this.playNum == 0) {
	    UCB = 100;
	}
    }
    
    
	
    

    public void printNodeInfo() {
	    
	System.out.println("No." + nodeNo + " nextHand :" + nHand +" UCB: " + UCB + " playOut: " + playNum + " winNum :" + winNum + " win: " + (double)winNum/playNum);
    }

    public static void main(String args[]) {
	GameNode gn = new GameNode();
	gn.init();
	gn.expNode();
	for (int i=0; i<gn.child.size();i++) {
	    gn.child.get(i).expNode();
	}
	gn.getAllNode();
	for (int i=0; i<gn.all.size();i++) {
	    gn.all.get(i).printBan();
	}
	
    }
}
