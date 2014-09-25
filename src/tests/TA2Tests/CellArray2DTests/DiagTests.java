package tests.TA2Tests.CellArray2DTests;

import static org.junit.Assert.*;

import org.junit.Test;

import caTools.CellArray2D;

public class DiagTests {
	int[][] testBin = {{0,1,0}, 
			           {0,1,0}, 
			           {1,0,0}};

	@Test
	public void test() {
		CellArray2D test = new CellArray2D(testBin);

		int expectedr001 = 0;
		int actualr001 = test.getRightDiag(0, 0, 1);
		assertEquals(expectedr001, actualr001);

		int expectedl001 = 1;
		int actuall001 = test.getLeftDiag(0, 0, 1);
		assertEquals(expectedl001, actuall001);

		int expectedl002 = 1;
		int actuall002 = test.getLeftDiag(0, 0, 2);
		assertEquals(expectedl002, actuall002);

		int expectedr111 = 1;
		int actualr111 = test.getRightDiag(1, 1, 1);
		assertEquals(expectedr111, actualr111);

		int expectedl111 = 0;
		int actuall111 = test.getLeftDiag(1, 1, 1);
		assertEquals(expectedl111, actuall111);

		int expectedl112 = 0;
		int actuall112 = test.getLeftDiag(1, 1, 2);
		assertEquals(expectedl112, actuall112);

		int expectedr112 = 1;
		int actualr112 = test.getRightDiag(1, 1, 2);
		assertEquals(expectedr112, actualr112);

		int expectedr201 = 1;
		int actualr201 = test.getRightDiag(2, 0, 1);
		assertEquals(expectedr201, actualr201);

		int expectedr202 = 1;
		int actualr202 = test.getRightDiag(2, 0, 2);
		assertEquals(expectedr202, actualr202);

		int expectedl201 = 0;
		int actuall201 = test.getLeftDiag(2, 0, 1);
		assertEquals(expectedl201, actuall201);

		int expectedr021 = 1;
		int actualr021 = test.getRightDiag(0, 2, 1);
		assertEquals(expectedr021, actualr021);

		int expectedl221 = 1;
		int actuall221 = test.getLeftDiag(2, 2, 1);
		assertEquals(expectedl221, actuall221);
	}

}
