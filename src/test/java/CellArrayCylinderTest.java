package java;

import static org.junit.Assert.*;

import org.junit.Test;

import java.cellery.topology.Topology2D;
import java.cellery.topology.Topology2D.Base;
import java.cellery.topology.Topology2D.Space;
import java.cellery.CellArray2D;

public class CellArrayCylinderTest {
	int[][] testBin = {{1,0,0,0,1},
					   {1,0,0,1,1},
					   {0,0,1,1,1}};
	
	@Test
	public void testHorizontal() {
		Topology2D topo = new Topology2D(Base.HORIZONTAL_2D, 1, Space.CYL);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expected001 = 1;
		int actual001 = test.getNeighborhood(0, 0);
		assertEquals(expected001, actual001);
		
		int expected141 = 2;
		int actual141 = test.getNeighborhood(1, 4);
		assertEquals(expected141, actual141);
		
		int expected121 = 1;
		int actual121 = test.getNeighborhood(1,2);
		assertEquals(expected121, actual121);
		

	}
	
	@Test
	public void testHorizontal2(){
		Topology2D topo = new Topology2D(Base.HORIZONTAL_2D, 2, Space.CYL);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expected112 = 3;
		int actual112 = test.getNeighborhood(1, 1);
		assertEquals(expected112, actual112);
	}
	
	@Test
	public void rightDiagTest() {
		Topology2D topo = new Topology2D(Base.RIGHT_DIAGONAL, 1, Space.CYL);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expected141 = 2;
		int actual141 = test.getNeighborhood(1, 4);
		assertEquals(expected141, actual141);
		
		int expected101 = 1;
		int actual101 = test.getNeighborhood(1, 0);
		assertEquals(expected101, actual101);
		
	}
	
	@Test
	public void leftDiagTest() {
		Topology2D topo = new Topology2D(Base.LEFT_DIAGONAL, 1, Space.CYL);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expected101 = 1;
		int actual101 = test.getNeighborhood(1, 0);
		assertEquals(expected101, actual101);
		
		int expected041 = 1;
		int actual041 = test.getNeighborhood(0, 4);
		assertEquals(expected041, actual041);
	}
	
	@Test
	public void mooreTest() {
		Topology2D topo = new Topology2D(Base.MOORE, 1, Space.CYL);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expected10 = 4;
		int actual10 = test.getNeighborhood(1, 0);
		assertEquals(expected10, actual10);
		
		int expected14 = 6;
		int actual14 = test.getNeighborhood(1, 4);
		assertEquals(expected14, actual14);
	}
	
}
