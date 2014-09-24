package tests.TA2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import caTools.CellArray2D;
import caTools.TessellationAutomata2;

public class oddStepTest {
	int[][] testBin = { { 0, 1, 0 }, { 0, 0, 0 }, { 1, 0, 0 } };
	CellArray2D test = new CellArray2D(testBin);
	TessellationAutomata2 testCA = new TessellationAutomata2(test);

	@Test
	public void test() {
		int expected00 = 1;
		assertEquals(expected00, testCA.oddStep(0, 0));

		int expected01 = 0;
		assertEquals(expected01, testCA.oddStep(0, 1));

		int expected02 = 1;
		assertEquals(expected02, testCA.oddStep(0, 2));

		int expected10 = 0;
		assertEquals(expected10, testCA.oddStep(1, 0));

		int expected11 = 0;
		assertEquals(expected11, testCA.oddStep(1, 1));

		int expected12 = 1;
		assertEquals(expected12, testCA.oddStep(1, 2));

		int expected20 = 0;
		assertEquals(expected20, testCA.oddStep(2, 0));

		int expected21 = 1;
		assertEquals(expected21, testCA.oddStep(2, 1));

		int expected22 = 0;
		assertEquals(expected22, testCA.oddStep(2, 2));
	}

}
