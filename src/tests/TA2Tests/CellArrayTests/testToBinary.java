package tests.TA2Tests.CellArrayTests;

import static org.junit.Assert.*;
import org.junit.Test;
import caTools.CellArray1D;

public class testToBinary {
	CellArray1D test = new CellArray1D(6);

	@Test
	public void testToBinary0() {
		test.resurrectCell(1);
		test.resurrectCell(2);
		test.resurrectCell(5);
		System.out.println(test.toString());
		int[] expected = { 0, 1, 1, 0, 0, 1 };
		int[] actual = test.toBinaryArray();
		assertArrayEquals(expected, actual);

	}

}
