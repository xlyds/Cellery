package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cellery.topology.Topology2D;
import cellery.topology.Topology2D.Base;
import cellery.topology.Topology2D.Space;
import cellery.CellArray2D;

public class MooreTest {
	int[][] testBin;
	CellArray2D cells;
	Topology2D topo1;
	Topology2D topo2;
	
	@Before
	public void setUp(){
		testBin = new int[][]  { { 1, 0, 0, 0, 0 }, 
	            				 { 1, 1, 0, 1, 0 },
	            				 { 0, 0, 0, 0, 0 }, 
	            				 { 0, 1, 0, 0, 0 }, 
	            				 { 0, 0, 0, 0, 1 } };
	
		topo1 = new Topology2D(Base.MOORE, 1, Space.STD);
		topo2 = new Topology2D(Base.MOORE, 2, Space.STD);
		
		
	}
	
	@Test
	public void testTopo1() {
		cells = new CellArray2D(testBin, topo1);
		
		int expected22 = 3;
		int actual22 = cells.getNeighborhood(2, 2);
		assertEquals(expected22, actual22);
		
		int expected33 = 1;
		int actual33 = cells.getNeighborhood(3, 3);
		assertEquals(expected33, actual33);
		
		int expected44 = 0;
		int actual44 = cells.getNeighborhood(4, 4);
		assertEquals(expected44, actual44);
		
		
	}
	
	@Test
	public void testTopo2() {
		cells = new CellArray2D(testBin, topo2);
		
		int expected202 = 4;
		int actual202 = cells.getNeighborhood(2, 0);
		assertEquals(expected202, actual202);
		
		int expected222 = 6;
		int actual222 = cells.getNeighborhood(2, 2);
		assertEquals(expected222, actual222);

	}

}
