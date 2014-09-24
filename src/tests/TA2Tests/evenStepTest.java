package tests.TA2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import caTools.CellArray2D;
import caTools.TessellationAutomata2;

public class evenStepTest {
	int[][] testBin = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 0, 0 } };

	CellArray2D test = new CellArray2D(testBin);

	TessellationAutomata2 testCA = new TessellationAutomata2(test);

	@Test
	public void test() {

		int expected00 = 0;
		assertEquals(expected00, testCA.evenStep(0, 0));

		int expected01 = 1;
		assertEquals(expected01, testCA.evenStep(0, 1));

		int expected02 = 0;
		assertEquals(expected02, testCA.evenStep(0, 2));

		int expected10 = 0;
		assertEquals(expected10, testCA.evenStep(1, 0));

		int expected11 = 0;
		assertEquals(expected11, testCA.evenStep(1, 1));

		int expected12 = 1;
		assertEquals(expected12, testCA.evenStep(1, 2));

		int expected20 = 0;
		assertEquals(expected20, testCA.evenStep(2, 0));

		int expected21 = 0;
		assertEquals(expected21, testCA.evenStep(2, 1));

		int expected22 = 0;
		assertEquals(expected22, testCA.evenStep(2, 2));

	}

}
