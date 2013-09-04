package com.guimeira.ci.t2.logicgates;

import java.util.Arrays;

import com.guimeira.ci.t2.perceptron.Perceptron;
import com.guimeira.ci.t2.perceptron.UnitStepActivationFunction;

/**
 * Implements a three-input AND gate using a perceptron. 
 * @author Gui Meira
 *
 */
public class AndGate extends LogicGate {
	private Perceptron perceptron;
	
	/**
	 * Constructor.
	 */
	public AndGate(int numInputs) {
		super(numInputs);
		perceptron = new Perceptron(numInputs, new UnitStepActivationFunction());
		
		int rows = (int)Math.pow(2,numInputs);
		double[][] table = new double[rows][];
		double[] outputs = new double[rows];
		double[] tmp = new double[numInputs];
		fillTable(table, outputs, tmp, 0, 0);
		
		//Trains the perceptron with the AND truth-table:
		perceptron.train(table, outputs, 0.2, 1e-5, -1);
	}
	
	/**
	 * Generates all the combinations of inputs and its results.
	 * @param table		vector of vectors, each position will contain a possible input
	 * @param outputs	vector of outputs
	 * @param tmp		temporary vector with one position per input
	 * @param pos		pass zero on the first call
	 * @param count		pass zero on the first call
	 * @return			not important, just used in recursion inside the function
	 */
	private int fillTable(double[][] table, double[] outputs, double[] tmp, int pos, int count) {
		if(pos == tmp.length) {
			table[count] = Arrays.copyOf(tmp, tmp.length);
			boolean o = true;
			for(int i = 0; i < table[count].length; i++) {
				o = o && table[count][i] == 1 ? true : false;
			}
			
			outputs[count] = o ? 1 : 0;
			return 1;
		}
		
		tmp[pos] = 0;
		int val = fillTable(table, outputs, tmp, pos+1, count);
		tmp[pos] = 1;
		val += fillTable(table, outputs, tmp, pos+1, count+val);
		return val;
	}
	
	@Override
	protected boolean generateOutput(boolean[] input) {
		double[] doubleInput = new double[input.length];
		for(int i = 0; i < input.length; i++) {
			doubleInput[i] = input[i] == true ? 1 : 0;
		}
		
		double process = perceptron.process(doubleInput);
		
		if(process == 1)
			return true;
		else
			return false;
	}

}
