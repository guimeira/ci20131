package com.guimeira.ci.t2.logicgates;

/**
 * Implements a full adder using a truth-table.
 * @author Gui Meira
 *
 */
public class Adder extends TruthTableImplementer {
	public LogicGate[] andLayer;
	public LogicGate[] orLayer;
	
	/**
	 * Constructor.
	 */
	public Adder() {
		super(new boolean[][] {
				{false, false, false},
				{false, false, true},
				{false, true, false},
				{false, true, true},
				{true, false, false},
				{true, false, true},
				{true, true, false},
				{true, true, true}
		}, new boolean[] {
				false,
				true,
				true,
				false,
				true,
				false,
				false,
				true
		});
	}
}
