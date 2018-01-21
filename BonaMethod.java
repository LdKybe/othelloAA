import java.util.*;

//ボナンザメソッドを実行するクラス
public class BonaMethod extends Evaluation {
    public double LearnRate = 10000;
    public static double gain = 0.0270;
    public double w = 0.001;

    BonaMethod(String str) {
	super(str);
    }
    
    public void learnEvaluation ( ArrayList<Othello> othello ) {
	ArrayList<double[][]> v = new ArrayList<double[][]>();
	ArrayList<double[][]> vIndex = new ArrayList<double[][]>();
	ArrayList<double[][]> vAnser = new ArrayList<double[][]>();
	for (int i = 0; i < othello.size(); i++ ) {
	    double[][] x = new double[8][12];
	    v.add(getQ(othello.get(i)));
	    vIndex.add(getIndex(othello.get(i)));
	    vAnser.add(x);
	}
	//System.out.println(othello.size());
	double evalDiff = 0;
	
	for (int i = 0; i < v.size(); i++) {
	    for (int j = 0; j < v.get(0).length; j++) {
		for (int k = 0; k < v.get(0)[0].length; k++){
		    if(v.get(i)[j][k] > 1000000) System.out.print("?");
		    evalDiff +=  v.get(i)[j][k] * 0.01;
		    evalDiff -=  v.get(0)[j][k] * 0.01;
		}
	    }
	}
	//if (sigmoid(evalDiff) > 0.02 ) System.out.print("*");
	//System.out.println(evalDiff);
	//System.out.println(dSigmoid(100));
	//}
	//System.out.println(evalDiff);
	for (int i2 = 0; i2 < v.size(); i2++) {
	    for (int j2 = 0; j2 < v.get(0).length; j2++) {
		for (int k2 = 0; k2 < v.get(0)[0].length; k2++){
		    for (int j = 0; j < v.get(0).length; j++) {
			for (int i = 0; i < v.size(); i++) {
			    for (int k = 0; k < v.get(0)[0].length; k++){
				if (vIndex.get(i2)[j2][k2] == vIndex.get(i)[j][k]) {
				    vAnser.get(i2)[j2][k2] += dSigmoid(evalDiff) * 1;
				}
				if (vIndex.get(i2)[j2][k2] == vIndex.get(0)[j][k]) {
				    vAnser.get(i2)[j2][k2] -= dSigmoid(evalDiff) * 1;
				}
			    }
			}
		    }
		}
	    }
	}
	int count = 0;
	for (int i = 0; i < v.size(); i++) {
	    //System.out.println("ok");
	    for (int j = 0; j < v.get(0).length; j++) {
		for (int k = 0; k < v.get(0)[0].length; k++){
		    //System.out.println(v.get(i)[j][k]);
		    v.get(i)[j][k] = v.get(i)[j][k] - (LearnRate * vAnser.get(i)[j][k] + w * v.get(i)[j][k]);
		    //System.out.println(anser.get(i)[j][k]);
		}
	    }
	}
	for (int i = 0; i < v.size(); i++) {
	    setEval(othello.get(i), v.get(i));
	}
	
    }
    

    public static double sigmoid(double x) {
	return 1/(1 + Math.exp(-1 * gain * x));
    }

    public static double dSigmoid(double x) {
	return gain * (1 - sigmoid(x)) * sigmoid(x);
    }

}
