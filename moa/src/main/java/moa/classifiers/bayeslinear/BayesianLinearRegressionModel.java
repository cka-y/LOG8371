
package moa.classifiers.bayeslinear;

import moa.core.DoubleVector;

public class BayesianLinearRegressionModel {

	private DoubleVector beta;
	private DoubleVector gamma;
	private DoubleVector mu;
	private double lambda;
	private int numInstances;

	public BayesianLinearRegressionModel() {
		this.beta = new DoubleVector();
		this.gamma = new DoubleVector();
		this.mu = new DoubleVector();
		this.lambda = 1.0;
		this.numInstances = 0;
	}

	public BayesianLinearRegressionModel(int numFeatures) {
		this.beta = new DoubleVector(numFeatures);
		this.gamma = new DoubleVector(numFeatures);
		this.mu = new DoubleVector(numFeatures);
		this.lambda = 1.0;
		this.numInstances = 0;
	}

	public void update(DoubleVector x, double y) {
		double alpha = 1.0 / (1.0 + lambda);
		double betaNew = 1.0 / (numInstances + 1.0);

		gamma.scaleValues(alpha);
		gamma.addValues(x.squared());
		mu.scaleValues(alpha);
		mu.addValues(x.multiply(y));

		double lambdaNew = lambda + x.squared().dotProduct(gamma) - 2.0 * x.dotProduct(mu) * alpha + betaNew * x.squared().dotProduct(x);
		beta.scaleValues(1.0 - alpha);
		beta.addValues(mu.multiply(alpha));
		lambda = lambdaNew;
		numInstances++;
	}

	public DoubleVector getBeta() {
		return beta;
	}
}
