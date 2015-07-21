package tests;

import cellery.CellArray1D;
import static org.junit.Assert.*;
import org.junit.Test;

public class UpdateCellsTest {
	CellArray1D test = new CellArray1D(6, null);

	@Test
	public void test() {
		// {0,1,1,0,0,1}
		test.nextState(1);
		test.nextState(2);
		test.nextState(5);
		int[] expected0 = { 0, 0, 1, 0, 0, 1 };
		int[] expected1 = { 0, 0, 1, 1, 0, 1 };
		test.previousState(1);
		int[] actual0 = test.toArray();
		test.nextState(3);
		int[] actual1 = test.toArray();

		assertArrayEquals(expected0, actual0);
		assertArrayEquals(expected1, actual1);

	}

}
