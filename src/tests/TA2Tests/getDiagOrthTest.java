package tests.TA2Tests;

import caTools.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class getDiagOrthTest {
	int[][] testBin = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 0, 0 } };
	CellArray2D test = new CellArray2D(testBin);
	TessellationAutomata2 testCA = new TessellationAutomata2(test);

	@Test
	public void test() {
		System.out.println(test.toString());
		// orth-diag about (0,0) should be (0,1),(1,1),(1,0)
		int expected00 = 1;
		assertEquals(expected00, testCA.getDiagOrth(0, 0));

		int expected11 = 1;
		assertEquals(expected11, testCA.getDiagOrth(1, 1));

		int expected21 = 1;
		assertEquals(expected21, testCA.getDiagOrth(2, 1));

	}

}
