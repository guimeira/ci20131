package com.guimeira.ci.t2;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class NumberPanel extends JPanel implements ActionListener {
	private JToggleButton[] buttons;
	private int numBits;
	private ActionListener changeListener;
	
	public NumberPanel(String title, int numBits, ActionListener changeListener) {
		this.numBits = numBits;
		this.changeListener = changeListener;
		buttons = new JToggleButton[numBits];
		setLayout(new GridLayout(1, numBits));
		
		for(int i = 0; i < numBits; i++) {
			buttons[i] = new JToggleButton("0");
			buttons[i].setForeground(Color.RED);
			buttons[i].addActionListener(this);
			buttons[i].setFont(buttons[i].getFont().deriveFont(20f));
			add(buttons[i]);
		}
		setBorder(BorderFactory.createTitledBorder(title));
	}
	
	public boolean[] getNumber() {
		boolean[] number = new boolean[numBits];
		
		for(int i = 0; i < numBits; i++) {
			number[numBits-1-i] = buttons[i].isSelected();
		}
		
		return number;
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		JToggleButton source = (JToggleButton) ev.getSource();
		source.setForeground(source.isSelected() ? Color.GREEN : Color.RED);
		source.setText(source.isSelected() ? "1" : "0");
		changeListener.actionPerformed(new ActionEvent(this, 0, null));
	}
}
