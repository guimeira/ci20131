package com.guimeira.ci.t2.perceptron;

/**
 * Interface to be implemented by a activation function.
 * It receives the sum of all inputs from the neuron and should return
 * its output.
 * @author Gui Meira
 *
 */
public interface ActivationFunction {
	double calculate(double input);
}
