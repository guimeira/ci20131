package com.guimeira.ci.t2.logicgates;

import com.guimeira.ci.t2.perceptron.Perceptron;
import com.guimeira.ci.t2.perceptron.UnitStepActivationFunction;

/**
 * Implements a NOT gate using a perceptron. 
 * @author Gui Meira
 *
 */
public class NotGate extends LogicGate {
	private Perceptron perceptron;
	
	/**
	 * Constructor.
	 */
	public NotGate() {
		super(1);
		perceptron = new Perceptron(1, new UnitStepActivationFunction());
		
		//Trains the perceptron with the NOT truth-table:
		perceptron.train(new double[][] {{0},{1}}, 
				new double[]{1,0},
				0.2, 
				1e-5, 
				-1);
	}
	
	@Override
	protected boolean generateOutput(boolean[] input) {
		double process = perceptron.process(new double[]{input[0] == true ? 1 : 0});
		
		if(process == 1)
			return true;
		else
			return false;
	}

}
