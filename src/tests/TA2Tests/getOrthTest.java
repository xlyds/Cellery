package tests.TA2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import caTools.CellArray2D;
import caTools.TessellationAutomata2;

public class getOrthTest {
	int[][] testBin = { { 0, 0, 0, 0, 0 }, { 1, 0, 0, 1, 0 },
			{ 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };

	CellArray2D test = new CellArray2D(testBin);
	TessellationAutomata2 testCA = new TessellationAutomata2(test);

	@Test
	public void test() {

		int expected00 = 1;
		assertEquals(expected00, testCA.getOrth(0, 0));

		int expected22 = 0;
		assertEquals(expected22, testCA.getOrth(2, 2));

		int expected21 = 1;
		assertEquals(expected21, testCA.getOrth(2, 1));

	}

}
