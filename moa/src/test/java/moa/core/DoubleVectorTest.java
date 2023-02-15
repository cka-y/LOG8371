package moa.core;

import moa.core.DoubleVector;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DoubleVectorTest {

	@Test
	public void testConstructor() {
		DoubleVector vec = new DoubleVector(3);
		assertEquals(3, vec.numValues());
	}

	@Test
	public void testCopyConstructor() {
		DoubleVector vec1 = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		DoubleVector vec2 = new DoubleVector(vec1);
		assertEquals(vec1.numValues(), vec2.numValues());
		for (int i = 0; i < vec1.numValues(); i++)
			assertEquals(vec1.getValue(i), vec2.getValue(i),0.001);
	}

	@Test
	public void testSquared() {
		DoubleVector vec1 = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		DoubleVector vec2 = vec1.squared();
		assertEquals(1.0, vec2.getValue(0), 0.001);
		assertEquals(4.0, vec2.getValue(1), 0.001);
		assertEquals(9.0, vec2.getValue(2), 0.001);
	}

	@Test
	public void testDotProduct() {
		DoubleVector vec1 = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		DoubleVector vec2 = new DoubleVector(new double[]{4.0, 5.0, 6.0});
		double dotProduct = vec1.dotProduct(vec2);
		assertEquals(32.0, dotProduct, 0.001);
	}

	@Test
	public void testMultiply() {
		DoubleVector vec1 = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		DoubleVector vec2 = vec1.multiply(2.0);
		assertEquals(2.0, vec2.getValue(0), 0.001);
		assertEquals(4.0, vec2.getValue(1), 0.001);
		assertEquals(6.0, vec2.getValue(2), 0.001);
	}

	@Test
	public void testSetValue() {
		DoubleVector vec = new DoubleVector(3);
		vec.setValue(0, 1.0);
		vec.setValue(1, 2.0);
		vec.setValue(2, 3.0);
		assertEquals(1.0, vec.getValue(0), 0.001);
		assertEquals(2.0, vec.getValue(1), 0.001);
		assertEquals(3.0, vec.getValue(2), 0.001);
	}

	@Test
	public void testAddToValue() {
		DoubleVector vec = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		vec.addToValue(1, 2.0);
		assertEquals(4.0, vec.getValue(1), 0.001);
	}

	@Test
	public void testAddValues() {
		DoubleVector vec1 = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		DoubleVector vec2 = new DoubleVector(new double[]{2.0, 3.0, 4.0});
		vec1.addValues(vec2);
		assertEquals(3.0, vec1.getValue(0), 0.001);
		assertEquals(5.0, vec1.getValue(1), 0.001);
		assertEquals(7.0, vec1.getValue(2), 0.001);
	}
	@Test
	public void testSubtractValues() {
		DoubleVector vec1 = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		DoubleVector vec2 = new DoubleVector(new double[]{2.0, 3.0, 4.0});
		vec1.subtractValues(vec2);
		assertEquals(-1.0, vec1.getValue(0), 0.001);
		assertEquals(-1.0, vec1.getValue(1), 0.001);
		assertEquals(-1.0, vec1.getValue(2), 0.001);
	}

	@Test
	public void testAddToValues() {
		DoubleVector vec = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		vec.addToValues(2.0);
		assertEquals(3.0, vec.getValue(0), 0.001);
		assertEquals(4.0, vec.getValue(1), 0.001);
		assertEquals(5.0, vec.getValue(2), 0.001);
	}

	@Test
	public void testScaleValues() {
		DoubleVector vec = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		vec.scaleValues(2.0);
		assertEquals(2.0, vec.getValue(0), 0.001);
		assertEquals(4.0, vec.getValue(1), 0.001);
		assertEquals(6.0, vec.getValue(2), 0.001);
	}

	@Test
	public void testGetValue() {
		DoubleVector vec = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		assertEquals(1.0, vec.getValue(0), 0.001);
		assertEquals(2.0, vec.getValue(1), 0.001);
		assertEquals(3.0, vec.getValue(2), 0.001);
		assertEquals(0.0, vec.getValue(3), 0.001);
	}

	@Test
	public void testSumOfValues() {
		DoubleVector vec = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		assertEquals(6.0, vec.sumOfValues(), 0.001);
	}

	@Test
	public void testSumOfAbsoluteValues() {
		DoubleVector vec = new DoubleVector(new double[]{-1.0, 2.0, -3.0});
		assertEquals(6.0, vec.sumOfAbsoluteValues(), 0.001);
	}

	@Test
	public void testMaxIndex() {
		DoubleVector vec = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		assertEquals(2, vec.maxIndex());
	}

	@Test
	public void testNormalize() {
		DoubleVector vec = new DoubleVector(new double[]{-1.0, 2.0, -3.0});
		vec.normalize();
		assertEquals(-1.0 / 6.0, vec.getValue(0), 0.001);
		assertEquals(1.0 / 3.0, vec.getValue(1), 0.001);
		assertEquals(-1.0 / 2.0, vec.getValue(2), 0.001);
	}
	@Test
	public void testMinWeight() {
		DoubleVector vec = new DoubleVector(new double[]{2.0, -1.0, 3.0, 0.0});
		assertEquals(-1.0, vec.minWeight(), 0.001);
	}

	@Test
	public void testGetArrayCopy() {
		DoubleVector vec = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		double[] copy = vec.getArrayCopy();
		assertEquals(1.0, copy[0], 0.001);
		assertEquals(2.0, copy[1], 0.001);
		assertEquals(3.0, copy[2], 0.001);
	}

	@Test
	public void testGetArrayRef() {
		DoubleVector vec = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		double[] ref = vec.getArrayRef();
		assertEquals(1.0, ref[0], 0.001);
		assertEquals(2.0, ref[1], 0.001);
		assertEquals(3.0, ref[2], 0.001);
	}

	@Test
	public void testSetArrayLength() {
		DoubleVector vec = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		vec.setArrayLength(5);
		assertEquals(0.0, vec.getValue(3), 0.001);
		assertEquals(0.0, vec.getValue(4), 0.001);
	}

	@Test
	public void testGetSingleLineDescription() {
		DoubleVector vec = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		StringBuilder sb = new StringBuilder();
		vec.getSingleLineDescription(sb);
		assertEquals("{1|2|3}", sb.toString());
	}

	@Test
	public void testGetSingleLineDescriptionNumValues() {
		DoubleVector vec = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		StringBuilder sb = new StringBuilder();
		vec.getSingleLineDescription(sb, 2);
		assertEquals("{1|2}", sb.toString());
	}

	@Test
	public void testGetDescription() {
		DoubleVector vec = new DoubleVector(new double[]{1.0, 2.0, 3.0});
		StringBuilder sb = new StringBuilder();
		vec.getDescription(sb, 0);
		assertEquals("{1|2|3}", sb.toString());
	}
}
