package com.guimeira.ci.t2.logicgates;

/**
 * A VERY simplified model for a logic gate.
 * Nobody should use it in a serious project.
 * @author Gui Meira
 *
 */
public abstract class LogicGate {
	private LogicGate[] inputGates;
	private int numInputs;
	private boolean output;
	
	/**
	 * Constructor.
	 * @param numInputs		number of inputs in this gate
	 */
	public LogicGate(int numInputs) {
		this.numInputs = numInputs;
		inputGates = new LogicGate[numInputs];
	}
	
	/**
	 * Connects a gate to this input.
	 * @param gate			the gate to be connected
	 * @param inputNum		the number of the input to connect
	 */
	public void connectInput(LogicGate gate, int inputNum) {
		inputGates[inputNum] = gate;
	}
	
	/**
	 * Asks all the inputs for their current output and generates the
	 * output for this gate.
	 */
	public void process() {
		boolean[] outputs = new boolean[numInputs];
		
		for(int i = 0; i < inputGates.length; i++) {
			outputs[i] = inputGates[i].getOutput();
		}
		
		output = generateOutput(outputs);
	}
	
	/**
	 * Returns the last generated output.
	 * @return		the last generated output
	 */
	public boolean getOutput() {
		return output;
	}
	
	/**
	 * Returns the inputs connected on this gate.
	 * @return		the inputs
	 */
	public LogicGate[] getInputs() {
		return inputGates;
	}
	
	/**
	 * Should be implemented by subclasses.
	 * @param input		inputs from the input gates
	 * @return			output generated for this gate
	 */
	protected abstract boolean generateOutput(boolean[] input);
}
