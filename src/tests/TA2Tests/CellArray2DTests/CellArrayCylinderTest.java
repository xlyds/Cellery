package tests.TA2Tests.CellArray2DTests;

import static org.junit.Assert.*;

import org.junit.Test;

import caTools.CellArrayCylinder;

public class CellArrayCylinderTest {
	int[][] testBin = {{1,0,0,0,1},
					   {1,0,0,1,1},
					   {0,0,1,1,1}};
	@Test
	public void testMod(){
		int x1 = -1;
		int M = 5;
		int expected1 = 4;
		int actual1 = CellArrayCylinder.mod(x1,M);
		assertEquals(expected1, actual1);
		
		int x2 = 0;
		int expected2 = 0;
		int actual2 = CellArrayCylinder.mod(x2, M);
		assertEquals(expected2, actual2);
		
		int x3 = 3;
		int expected3 = 3;
		int actual3  = CellArrayCylinder.mod(x3, M);
		assertEquals(expected3, actual3);
		
		int x4 = 6;
		int expected4 = 1;
		int actual4 = CellArrayCylinder.mod(x4, M);
		assertEquals(expected4, actual4);
		
		int x5 = -6;
		int expected5 = 4;
		int actual5 = CellArrayCylinder.mod(x5, M);
		assertEquals(expected5, actual5);
	}
	
	
	@Test
	public void testHorizontal() {
		CellArrayCylinder test = new CellArrayCylinder(testBin);
		
		int expected001 = 1;
		int actual001 = test.getHorizontal(0, 0, 1);
		assertEquals(expected001, actual001);
		
		int expected141 = 2;
		int actual141 = test.getHorizontal(1, 4, 1);
		assertEquals(expected141, actual141);
		
		int expected121 = 1;
		int actual121 = test.getHorizontal(1,2,1);
		assertEquals(expected121, actual121);
		
		int expected112 = 3;
		int actual112 = test.getHorizontal(1, 1, 2);
		assertEquals(expected112, actual112);
	}
	
	@Test
	public void rightDiagTest() {
		CellArrayCylinder test = new CellArrayCylinder(testBin);
		
		int expected141 = 2;
		int actual141 = test.getRightDiag(1,4,1);
		assertEquals(expected141, actual141);
		
		int expected101 = 1;
		int actual101 = test.getRightDiag(1, 0, 1);
		assertEquals(expected101, actual101);
		
	}
	
	@Test
	public void leftDiagTest() {
		CellArrayCylinder test = new CellArrayCylinder(testBin);
		
		int expected101 = 1;
		int actual101 = test.getLeftDiag(1,0,1);
		assertEquals(expected101, actual101);
		
		int expected041 = 1;
		int actual041 = test.getLeftDiag(0, 4, 1);
		assertEquals(expected041, actual041);
	}
	
	@Test
	public void mooreTest() {
		CellArrayCylinder test = new CellArrayCylinder(testBin);
		
		int expected10 = 4;
		int actual10 = test.moore(1, 0);
		assertEquals(expected10, actual10);
		
		int expected14 = 6;
		int actual14 = test.moore(1, 4);
		assertEquals(expected14, actual14);
	}

}
