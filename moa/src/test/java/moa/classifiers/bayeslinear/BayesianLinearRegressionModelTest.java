package moa.classifiers.bayeslinear;

import moa.core.DoubleVector;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BayesianLinearRegressionModelTest {

	@Test
	public void testBayesianLinearRegressionModel() {
		BayesianLinearRegressionModel model = new BayesianLinearRegressionModel(3);
		DoubleVector x = new DoubleVector(new double[] {1.0, 2.0, 3.0});
		double y = 4.0;
		model.update(x, y);

		DoubleVector beta = model.getBeta();
		assertEquals(beta.numValues(), 3);
	}
}
