package tests.TA2Tests.CellArrayTests;

import caTools.CellArray1D;
import static org.junit.Assert.*;
import org.junit.Test;

public class UpdateCellsTest {
	CellArray1D test = new CellArray1D(6);

	@Test
	public void test() {
		// {0,1,1,0,0,1}
		test.resurrectCell(1);
		test.resurrectCell(2);
		test.resurrectCell(5);
		int[] expected0 = { 0, 0, 1, 0, 0, 1 };
		int[] expected1 = { 0, 0, 1, 1, 0, 1 };
		test.killCell(1);
		int[] actual0 = test.toBinaryArray();
		test.resurrectCell(3);
		int[] actual1 = test.toBinaryArray();

		assertArrayEquals(expected0, actual0);
		assertArrayEquals(expected1, actual1);

	}

}
