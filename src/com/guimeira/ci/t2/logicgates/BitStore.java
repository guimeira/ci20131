package com.guimeira.ci.t2.logicgates;

/**
 * Logic gate that always outputs the defined bit.
 * @author Gui Meira
 *
 */
public class BitStore extends LogicGate {
	private boolean bit;
	
	/**
	 * Constructor, sets the output bit to zero.
	 */
	public BitStore() {
		super(0);
	}
	
	/**
	 * Constructor.
	 * @param bit		the output bit
	 */
	public BitStore(boolean bit) {
		super(0);
		setBit(bit);
		process();
	}
	
	/**
	 * Sets the output bit.
	 * @param bit		the output bit
	 */
	public void setBit(boolean bit) {
		this.bit = bit;
		process();
	}
	
	/**
	 * LogicGate interface implementation.
	 */
	@Override
	protected boolean generateOutput(boolean[] input) {
		return bit;
	}

}
