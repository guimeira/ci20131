package com.guimeira.ci.t2.logicgates;

import java.util.LinkedList;
import java.util.List;

/**
 * Implements any truth table using an AND gate layer and a OR gate layer.
 * @author Gui Meira
 *
 */
public class TruthTableImplementer extends LogicGate {
	private BitStore[] input;
	private List<LogicGate> notGates;
	private List<LogicGate> andGates;
	private OrGate output;
	
	/**
	 * Constructor.
	 * @param inputs		array containing the truth-table
	 * @param outputs		vector containing the outputs
	 */
	public TruthTableImplementer(boolean[][] inputs, boolean[] outputs) {
		super(inputs[0].length);
		
		notGates = new LinkedList<LogicGate>();
		andGates = new LinkedList<LogicGate>();
		
		input = new BitStore[inputs[0].length];
		for(int i = 0; i < input.length; i++)
			input[i] = new BitStore();
		
		//For each row in the truth-table:
		for(int i = 0; i < outputs.length; i++) {
			if(outputs[i] == true) {
				//Creates an AND gate:
				AndGate and = new AndGate(inputs[i].length);
				
				//Connect the input directly to the AND gate, or uses an NOT gate to invert:
				for(int j = 0; j < inputs[i].length; j++) {
					if(inputs[i][j] == true) {
						and.connectInput(input[j], j);
					} else {
						NotGate not = new NotGate();
						not.connectInput(input[j], 0);
						and.connectInput(not, j);
						notGates.add(not);
					}
				}
				andGates.add(and);
			}
		}
		
		//Connects all AND gates to the OR gate:
		output = new OrGate(andGates.size());
		for(int i = 0; i < andGates.size(); i++) {
			output.connectInput(andGates.get(i), i);
		}
	}
	
	@Override
	protected boolean generateOutput(boolean[] in) {
		for(int i = 0; i < in.length; i++) {
			input[i].setBit(in[i]);
		}
		
		//Processes all the NOT gates:
		for(LogicGate g : notGates) {
			g.process();
		}
		
		//Processes all the AND gates:
		for(LogicGate g : andGates) {
			g.process();
		}
		
		//Processes the OR gate:
		output.process();
		return output.getOutput();
	}

}