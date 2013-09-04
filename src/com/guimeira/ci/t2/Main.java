package com.guimeira.ci.t2;

public class Main {
	public static void main(String[] args) {
		new MainWindow();
		/*Perceptron p = new Perceptron(3, new UnitStepActivationFunction());
		p.train(new double[][] {{0,0,0},{0,0,1},{0,1,0},{0,1,1},{1,0,0},{1,0,1},{1,1,0},{1,1,1}}, 
				new double[]{0,0,0,0,0,0,0,1},
				0.2, 
				1e-5, 
				-1);
		System.out.printf("%f\n",p.process(new double[]{0,0,0}));
		System.out.printf("%f\n",p.process(new double[]{0,0,1}));
		System.out.printf("%f\n",p.process(new double[]{0,1,0}));
		System.out.printf("%f\n",p.process(new double[]{0,1,1}));
		System.out.printf("%f\n",p.process(new double[]{1,0,0}));
		System.out.printf("%f\n",p.process(new double[]{1,0,1}));
		System.out.printf("%f\n",p.process(new double[]{1,1,0}));
		System.out.printf("%f\n",p.process(new double[]{1,1,1}));
		/*p.train(new double[][]{{0,0},{0,1},{1,0},{1,1}},
				new double[]{0,0,0,1},
				0.2,
				1e-5, -1);
		
		System.out.printf("%f\n",p.process(new double[]{0,0}));
		System.out.printf("%f\n",p.process(new double[]{0,1}));
		System.out.printf("%f\n",p.process(new double[]{1,0}));
		System.out.printf("%f\n",p.process(new double[]{1,1}));*/
	}
}

