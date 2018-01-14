import java.util.*;

public class BonaMethod extends Evaluation {
    public double LearnRate = 0.01;
    public double gain = 0.0017;

    public void learnEvaluation (Othello othello, Othello othello2) {
	double[][] v = new double[8][12];
	double[][] v0 = new double[8][12];
	double[][] newV = new double[8][12];
	int[] evalDiff = new int[8];
	
	for (int i = 0; i < v.length; i++) {
	    evalDiff[i] = 0;
	    for (int j = 0; j < v[0].length; j++) {
		evalDiff[i] += v[i][j] - v0[i][j];
	    }
	}
	    
	
	for (int i = 0; i < v.length; i++) {
	    for (int j = 0; j < v[0].length; j++) {
		newV[i][j] = dSigmoid(evalDiff[i]) * (v[i][j] - v0[i][j]) * v[i][j];
	    }
	}
	for (int i = 0; i < v.length; i++) {
	    for (int j = 0; j < v[0].length; j++) {
		v[i][j] = v[i][j] - LearnRate * newV[i][j];
	    }
	}
	setEval(othello, v);
	
    }

    public double dSigmoid(int x) {
	double x2 = x * gain;
	return (1 - x2) * (x2);
    }
}
