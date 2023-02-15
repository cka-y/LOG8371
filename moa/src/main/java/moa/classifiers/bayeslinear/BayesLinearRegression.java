package moa.classifiers.bayeslinear;

import moa.classifiers.AbstractClassifier;
import moa.core.DoubleVector;
import moa.core.Measurement;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

import java.util.Collection;
import java.util.Collections;

public class BayesLinearRegression extends AbstractClassifier {

	private static final long serialVersionUID = 1L;

	private BayesianLinearRegressionModel model;

	@Override
	public double[] getVotesForInstance(com.yahoo.labs.samoa.instances.Instance inst) {
		double[] result = new double[inst.numClasses()];

		if (model == null) {
			return result;
		}

		DoubleVector x = new DoubleVector(inst.numAttributes() - 1);
		for (int i = 0; i < inst.numAttributes() - 1; i++) {
			x.addValues((DoubleVector) Collections.singletonList(inst.value(i)));
		}

		DoubleVector beta = model.getBeta();
		double y = beta.dotProduct(x);
		result[(int) y] = 1.0;

		return result;
	}

	@Override
	public void resetLearningImpl() {
		model = new BayesianLinearRegressionModel();
	}

	@Override
	public void trainOnInstanceImpl(com.yahoo.labs.samoa.instances.Instance inst) {
		if (model == null) {
			model = new BayesianLinearRegressionModel(inst.numAttributes() - 1);
		}

		DoubleVector x = new DoubleVector(inst.numAttributes() - 1);
		for (int i = 0; i < inst.numAttributes() - 1; i++) {
			x.addValues((DoubleVector) Collections.singletonList(inst.value(i)));
		}

		double y = inst.classValue();
		model.update(x, y);
	}

	@Override
	protected Measurement[] getModelMeasurementsImpl() {
		return new Measurement[0];
	}

	@Override
	public void getModelDescription(StringBuilder out, int indent) {

	}

	@Override
	public boolean isRandomizable() {
		return false;
	}
}
