package com.guimeira.ci.t2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.guimeira.ci.t2.logicgates.Adder;
import com.guimeira.ci.t2.logicgates.BitStore;
import com.guimeira.ci.t2.logicgates.Carry;

public class MainWindow extends JFrame implements ActionListener {
	private NumberPanel panel1;
	private NumberPanel panel2;
	private JLabel answer;
	private Adder[] adders;
	private Carry[] carries;
	private BitStore[] input1;
	private BitStore[] input2;
	
	public MainWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
		setLayout(new GridLayout(5,1));
		panel1 = new NumberPanel("First number", 8, this);
		add(panel1);
		
		JLabel lblPlus = new JLabel("+");
		lblPlus.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlus.setFont(lblPlus.getFont().deriveFont(30f));
		add(lblPlus);
		
		panel2 = new NumberPanel("Second number", 8, this);
		add(panel2);
		
		JLabel lblEquals = new JLabel("=");
		lblEquals.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquals.setFont(lblEquals.getFont().deriveFont(30f));
		add(lblEquals);
		
		answer = new JLabel("00000000");
		answer.setHorizontalAlignment(SwingConstants.CENTER);
		answer.setFont(answer.getFont().deriveFont(30f));
		JPanel pnlAnswer = new JPanel();
		pnlAnswer.add(answer);
		pnlAnswer.setBorder(BorderFactory.createTitledBorder("Answer"));
		add(pnlAnswer);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuFile.add(menuItemExit);
		menuBar.add(menuFile);
		
		JMenu menuHelp = new JMenu("Help");
		JMenuItem menuItemAbout = new JMenuItem("About...");
		menuItemAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainWindow.this, "<html><b>Controle Inteligente 2013/1</b><br/>Guilherme Tebaldi Meira<br/>gtmeira@inf.ufes.br<br/>http://github.com/guimeira/ci20131</html>","About",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuHelp.add(menuItemAbout);
		menuBar.add(menuHelp);
		setJMenuBar(menuBar);
		
		adders = new Adder[8];
		carries = new Carry[8];
		input1 = new BitStore[8];
		input2 = new BitStore[8];
		
		for(int i = 0; i < 8; i++) {
			input1[i] = new BitStore();
			input2[i] = new BitStore();
		}
		
		for(int i = 0; i < 8; i++) {
			carries[i] = new Carry();
			carries[i].connectInput(input1[i], 0);
			carries[i].connectInput(input2[i], 1);
			carries[i].connectInput(i == 0 ? new BitStore(false) : carries[i-1], 2);
		}
		
		for(int i = 0; i < 8; i++) {
			adders[i] = new Adder();
			adders[i].connectInput(input1[i], 0);
			adders[i].connectInput(input2[i], 1);
			adders[i].connectInput(i == 0 ? new BitStore(false) : carries[i-1], 2);
		}
		
		setTitle("Controle Inteligente 2013/1");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String result = "";
		
		boolean[] num1 = panel1.getNumber();
		boolean[] num2 = panel2.getNumber();
		
		for(int i = 0; i < 8; i++) {
			input1[i].setBit(num1[i]);
			input2[i].setBit(num2[i]);
		}
		
		for(int i = 0; i < 8; i++) {
			carries[i].process();
		}
		
		for(int i = 0; i < 8; i++) {
			adders[i].process();
			result = (adders[i].getOutput() ? "1" : "0") + result;
		}
		
		result = (carries[7].getOutput() ? "1" : "0") + result;
		
		answer.setText(result);
	}
}
