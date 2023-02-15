package moa.classifiers.bayeslinear;

import com.yahoo.labs.samoa.instances.Attribute;
import com.yahoo.labs.samoa.instances.DenseInstance;
import moa.classifiers.bayeslinear.BayesLinearRegression;
import com.yahoo.labs.samoa.instances.Instance;
import com.yahoo.labs.samoa.instances.Instances;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BayesLinearRegressionTest {
	@Test
	public void testBayesLinearRegression() throws Exception {
		BayesLinearRegression classifier = new BayesLinearRegression();
		Instances header = createHeader();

		for (int i = 0; i < 10; i++) {
			Instance inst = createInstance(i % 2 == 0 ? "sunny" : "rainy", i % 3 == 0 ? "yes" : "no");
			inst.setDataset(header);
			classifier.trainOnInstance(inst);
		}

		Instance testInstance = createInstance("sunny", "yes");
		testInstance.setDataset(header);
		double[] result = classifier.getVotesForInstance(testInstance);
		assertEquals(result.length, 2);
	}

	private Instances createHeader() {
		ArrayList<Attribute> attributes = new ArrayList<>();
		attributes.add(new Attribute("outlook", Arrays.asList("sunny", "overcast", "rainy")));
		attributes.add(new Attribute("windy", Arrays.asList("yes", "no")));
		attributes.add(new Attribute("play", Arrays.asList("yes", "no")));
		Instances header = new Instances("weather", attributes, 0);
		header.setClassIndex(header.numAttributes() - 1);
		return header;
	}

	private Instance createInstance(String outlook, String windy) {
		Attribute outlookAttr = new Attribute("outlook", Arrays.asList("sunny", "overcast", "rainy"));
		Attribute windyAttr = new Attribute("windy", Arrays.asList("yes", "no"));
		Attribute playAttr = new Attribute("play", Arrays.asList("yes", "no"));
		ArrayList<Attribute> attributes = new ArrayList<>(Arrays.asList(outlookAttr, windyAttr, playAttr));
		Instances header = new Instances("weather", attributes, 0);
		header.setClassIndex(header.numAttributes() - 1);
		Instance inst = new DenseInstance(3);
		inst.setValue(0, header.attribute(0).indexOfValue(outlook));
		inst.setValue(1, header.attribute(1).indexOfValue(windy));
		inst.setMissing(2);
		inst.setDataset(header);
		return inst;
	}
}
