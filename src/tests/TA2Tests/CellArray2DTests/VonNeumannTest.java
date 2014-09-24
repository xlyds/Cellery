package tests.TA2Tests.CellArray2DTests;

import static org.junit.Assert.*;

import org.junit.Test;

import caTools.CellArray2D;

public class VonNeumannTest {
	int[][] testBin = { { 0, 0, 0, 0, 0 }, 
			            { 1, 0, 0, 1, 0 },
			            { 0, 0, 0, 0, 0 }, 
			            { 0, 1, 0, 0, 0 }, 
			            { 0, 0, 0, 0, 0 } };
	@Test
	public void test() {
		CellArray2D test = new CellArray2D(testBin);
		
		int expected221 = 0;
		int actual221 = test.vonNeumann(2, 2, 1);
		assertEquals(expected221, actual221);
		
		int expected222 = 2;
		int actual222 = test.vonNeumann(2, 2, 2);
		assertEquals(expected222, actual222);
		
		int expected112 = 3;
		int actual112 = test.vonNeumann(1, 1, 2);
		assertEquals(expected112, actual112);
		
	}

}
