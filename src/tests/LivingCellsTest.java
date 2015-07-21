package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import cellery.CellArray1D;

public class LivingCellsTest {
	CellArray1D test = new CellArray1D(6,null);

	@Test
	public void test() {
		// {0,1,1,0,0,1}
		test.nextState(1);
		test.nextState(2);
		test.nextState(5);
		int expected = 3;
		int actual = test.numCellsOfState();
		assertEquals(expected, actual);
	}

}
