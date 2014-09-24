package tests.TA2Tests.CellArrayTests;

import static org.junit.Assert.*;
import org.junit.Test;
import caTools.CellArray1D;

public class LivingCellsTest {
	CellArray1D test = new CellArray1D(6);

	@Test
	public void test() {
		// {0,1,1,0,0,1}
		test.resurrectCell(1);
		test.resurrectCell(2);
		test.resurrectCell(5);
		int expected = 3;
		int actual = test.livingCells();
		assertEquals(expected, actual);
	}

}
