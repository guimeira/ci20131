package com.guimeira.ci.t2.logicgates;

/**
 * Implements the carry of the adder using a truth-table.
 * @author Gui Meira
 *
 */
public class Carry extends TruthTableImplementer {
	public LogicGate[] andLayer;
	public LogicGate[] orLayer;
	
	public Carry() {
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
				false,
				false,
				true,
				false,
				true,
				true,
				true
		});
	}
}
