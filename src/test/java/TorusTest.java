package java;

import static org.junit.Assert.*;
import org.junit.Test;
import java.cellery.CellArray2D;
import java.cellery.topology.Topology2D;
import java.cellery.topology.Topology2D.Base;
import java.cellery.topology.Topology2D.Space;

public class TorusTest {
	int[][] testBin = {{1,0,0,0,1},
					   {1,0,0,1,0},
					   {0,0,1,1,1},
					   {1,0,0,0,0},
					   {0,1,0,1,0}};
	
	CellArray2D cells;
	Topology2D topo;
	Space space = Space.TOR;
	
	@Test
	public void testVert1() {
		topo = new Topology2D(Base.VERTICAL_2D, 1, space );
		cells = new CellArray2D(testBin, topo);
		
		int e12 = 1;
		int a12 = cells.getNeighborhood(1, 2);
		assertEquals(e12, a12);
		
		int e03 = 2;
		int a03 = cells.getNeighborhood(0, 3);
		assertEquals(e03, a03);
		
		int e44 = 1;
		int a44 = cells.getNeighborhood(4, 4);
		assertEquals(e44, a44);
		
	}
	
	@Test
	public void testVert2(){
		topo = new Topology2D(Base.VERTICAL_2D, 2, space );
		cells = new CellArray2D(testBin, topo);
		
		int e12 = 1;
		int a12 = cells.getNeighborhood(1, 2);
		assertEquals(e12, a12);
		
		int e03 = 3;
		int a03 = cells.getNeighborhood(0, 3);
		assertEquals(e03, a03);
		
		int e44 = 2;
		int a44 = cells.getNeighborhood(4, 4);
		assertEquals(e44, a44);
		
	}
	
	@Test
	public void testRightDiag1(){
		topo = new Topology2D(Base.RIGHT_DIAGONAL, 1, space );
		cells = new CellArray2D(testBin, topo);
		
		int e12 = 0;
		int a12 = cells.getNeighborhood(1, 2);
		assertEquals(e12, a12);
		
		int e03 = 0;
		int a03 = cells.getNeighborhood(0, 3);
		assertEquals(e03, a03);
		
		int e44 = 1;
		int a44 = cells.getNeighborhood(4, 4);
		assertEquals(e44, a44);
	}
	
	@Test
	public void testRightDiag2(){
		topo = new Topology2D(Base.RIGHT_DIAGONAL, 2, space );
		cells = new CellArray2D(testBin, topo);
		
		int e12 = 1;
		int a12 = cells.getNeighborhood(1, 2);
		assertEquals(e12, a12);
		
		int e13 = 2;
		int a13 = cells.getNeighborhood(1, 3);
		assertEquals(e13, a13);
		
		int e44 = 1;
		int a44 = cells.getNeighborhood(4, 4);
		assertEquals(e44, a44);
	}
	
	@Test
	public void testMoore1(){
		topo = new Topology2D(Base.MOORE, 1, space );
		cells = new CellArray2D(testBin, topo);
		
		int e12 = 3;
		int a12 = cells.getNeighborhood(1, 2);
		assertEquals(e12, a12);
		
		int e03 = 3;
		int a03 = cells.getNeighborhood(0, 3);
		assertEquals(e03, a03);
		
		int e44 = 4;
		int a44 = cells.getNeighborhood(4, 4);
		assertEquals(e44, a44);
	}

}
