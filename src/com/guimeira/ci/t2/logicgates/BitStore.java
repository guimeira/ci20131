package com.guimeira.ci.t2.logicgates;

public class BitStore extends LogicGate {
	private boolean bit;
	
	public BitStore() {
		super(0);
	}
	
	public BitStore(boolean bit) {
		super(0);
		setBit(bit);
		process();
	}
	
	public void setBit(boolean bit) {
		this.bit = bit;
		process();
	}
	
	@Override
	protected boolean generateOutput(boolean[] input) {
		return bit;
	}

}
