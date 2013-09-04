package com.guimeira.ci.t2.perceptron;

/**
 * Activation function that outputs 1 if the input is greater than a specified threshould
 * and 0 otherwise. 
 * @author Gui Meira
 *
 */
public class UnitStepActivationFunction implements ActivationFunction {
	private double threshould;
	
	/**
	 * Constructor.
	 * @param threshould	desired threshould
	 */
	public UnitStepActivationFunction(double threshould) {
		this.threshould = threshould;
	}
	
	/**
	 * Constructor using zero as threshould.
	 */
	public UnitStepActivationFunction() {
		this(0);
	}

	@Override
	public double calculate(double input) {
		if(input >= threshould)
			return 1;
		else
			return 0;
	}
}
