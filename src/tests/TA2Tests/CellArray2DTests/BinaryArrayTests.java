package tests.TA2Tests.CellArray2DTests;

import static org.junit.Assert.*;

import org.junit.Test;
import caTools.CellArray2D;

public class BinaryArrayTests {
	int[][] testBin = {{0,1,0}, {0,1,0}, {1,0,0}};

	/*
	 * {{0,1,0}, {0,1,0}, {1,0,0}}
	 */
	@Test
	public void test() {
		CellArray2D test = new CellArray2D(testBin);
		test.resurrectCell(2, 0);
		test.resurrectCell(0, 1);
		test.resurrectCell(1, 1);
		int[][] expected = { { 0, 1, 0 }, { 0, 1, 0 }, { 1, 0, 0 } };
		int[][] actual = test.toBinaryArray();
		assertArrayEquals(expected, actual);
	}
}
