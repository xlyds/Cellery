package tests.TA2Tests.CellArray2DTests;

import static org.junit.Assert.*;

import org.junit.Test;

import caTools.CellArray2D;

public class MooreTest {
	int[][] testBin = { { 1, 0, 0, 0, 0 }, 
			            { 1, 1, 0, 1, 0 },
			            { 0, 0, 0, 0, 0 }, 
			            { 0, 1, 0, 0, 0 }, 
			            { 0, 0, 0, 0, 1 } };
	@Test
	public void test() {
		CellArray2D test = new CellArray2D(testBin);
		
		int expected22 = 3;
		int actual22 = test.moore(2, 2);
		assertEquals(expected22, actual22);
		
		int expected33 = 1;
		int actual33 = test.moore(3, 3);
		assertEquals(expected33, actual33);
		
		int expected44 = 0;
		int actual44 = test.moore(4, 4);
		assertEquals(expected44, actual44);
		
	}

}
