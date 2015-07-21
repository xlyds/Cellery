package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import cellery.CellArray1D;

public class testToBinary {
	CellArray1D test = new CellArray1D(6, null);

	@Test
	public void testToBinary0() {
		test.nextState(1);
		test.nextState(2);
		test.nextState(5);
		System.out.println(test.toString());
		int[] expected = { 0, 1, 1, 0, 0, 1 };
		int[] actual = test.toArray();
		assertArrayEquals(expected, actual);

	}

}
