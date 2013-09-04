package com.guimeira.ci.t2.perceptron;

/**
 * Single layer perceptron implementation.
 * @author Gui Meira
 *
 */
public class Perceptron {
	private double[] weights;
	private ActivationFunction function;
	private int numInputs;
	
	/**
	 * Constructor.
	 * @param numInputs		number of inputs
	 * @param function		activation function
	 */
	public Perceptron(int numInputs, ActivationFunction function) {
		weights = new double[numInputs+1];
		this.function = function;
		this.numInputs = numInputs;
	}
	
	/**
	 * Trains the perceptron with the provided dataset.
	 * dataSetInputs is an array with one piece of data per row, each column
	 * representing a value for an input.
	 * The corresponding position on dataSetOutput is the desired output from the perceptron.
	 * @param dataSetInputs			inputs for the training
	 * @param dataSetOutputs		desired outputs
	 * @param learningRate			between 0 and 1, see explanation here: http://en.wikipedia.org/wiki/Perceptron
	 * @param tolerance				maximum allowed error
	 * @param maxEpochs				maximum iterations to finish the training, -1 for unlimited (may create an infinite loop)
	 * @return						number of epochs to train
	 */
	public int train(double[][] dataSetInputs, double[] dataSetOutputs, double learningRate, double tolerance, int maxEpochs) {
		int epochNum = 0;
		double error = Double.MAX_VALUE;
		
		while(error > tolerance && (epochNum < maxEpochs || maxEpochs == -1)) {
			double errorSum = 0;
			
			for(int i = 0; i < dataSetInputs.length; i++) {
				double f = process(dataSetInputs[i]);
				
				for(int j = 0; j < numInputs; j++) {
					weights[j] = weights[j] + learningRate*(dataSetOutputs[i]-f)*dataSetInputs[i][j];
				}
				//Updates the bias weight:
				weights[numInputs] = weights[numInputs] + learningRate*(dataSetOutputs[i]-f)*(-1);
				
				//Accumulates the error:
				errorSum += Math.pow(dataSetOutputs[i]-f,2);
			}
			
			error = errorSum/2;
			epochNum++;
		}
		
		return epochNum;
	}
	
	/**
	 * Processes an input.
	 * @param inputs		vector containing the inputs
	 * @return				the perceptron output
	 */
	public double process(double[] inputs) {
		double sum = 0;
		for(int i = 0; i < numInputs; i++) {
			sum += weights[i]*inputs[i];
		}
		sum += weights[numInputs]*(-1);
		
		return function.calculate(sum);
	}
}
