import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import cellery.topology.Topology2D;
import cellery.topology.Topology2D.Base;
import cellery.topology.Topology2D.Space;
import cellery.CellArray2D;

public class MobiusTest {
	int[][] testBin = {{1,0,0,0,1},
			   		   {1,0,0,1,0},
			           {0,0,1,1,1},
			           {1,0,0,0,0}};
	
	Topology2D topo;
	
	@Test
	public void testHorizontal() {
		topo = new Topology2D(Base.HORIZONTAL_2D, 1, Space.MOEB);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expected011 = 1;
		int actual011 = test.getNeighborhood(0,1);
		assertEquals(expected011, actual011);
		
		int expected001 = 0;
		int actual001 = test.getNeighborhood(0,0);
		assertEquals(expected001, actual001);
		
		int expected341 = 1;
		int actual341 = test.getNeighborhood(3, 4);
		assertEquals(expected341, actual341);
		
		int expected241 = 1;
		int actual241 = test.getNeighborhood(3, 4);
		assertEquals(expected241, actual241);
		
		int expected121 = 1;
		int actual121 = test.getNeighborhood(1, 2);
		assertEquals(expected121, actual121);
		
	}
	
	@Test
	public void testHorizontal2(){
		topo = new Topology2D(Base.HORIZONTAL_2D, 2, Space.MOEB);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expected112 = 3;
		int actual112 = test.getNeighborhood(1, 1);
		assertEquals(expected112, actual112);
		
	}
	
	@Test
	public void testHorizontal3(){
		topo = new Topology2D(Base.HORIZONTAL_2D, 3, Space.MOEB);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expected043 = 1;
		int actual043 = test.getNeighborhood(0, 4);
		assertEquals(expected043, actual043);
		
	}
	
	@Ignore
	public void testLeftDiag() {
		topo = new Topology2D(Base.LEFT_DIAGONAL, 1, Space.MOEB);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expected001 = 0;
		int actual001 = test.getNeighborhood(0, 0);
		assertEquals(expected001, actual001);
		
		int expected101 = 0;
		int actual101 = test.getNeighborhood(1,0);
		assertEquals(expected101, actual101);
		
		int expected201 = 1;
		int actual201 = test.getNeighborhood(2, 0);
		assertEquals(expected201, actual201);
		
		int expected301 = 0;
		int actual301 = test.getNeighborhood(3, 0);
		assertEquals(expected301, actual301);
		
		int expected041 = 0;
		int actual041 = test.getNeighborhood(0, 4);
		assertEquals(expected041, actual041);
		
		int expected141 = 1;
		int actual141 = test.getNeighborhood(1, 4);
		assertEquals(expected141, actual141);
		
		int expected241 = 2;
		int actual241 = test.getNeighborhood(2, 4);
		assertEquals(expected241,actual241);
		
		int expected341 = 1;
		int actual341 = test.getNeighborhood(3, 4);
		assertEquals(expected341, actual341);
		
		int expected221 = 0;
		int actual221 = test.getNeighborhood(2, 2);
		assertEquals(expected221, actual221);
		
		int expected032 = 1;
		int actual032 = test.getNeighborhood(0, 3);
		assertEquals(expected032, actual032);
		
	}
	
	@Test
	public void testLeftDiag2(){
		topo = new Topology2D(Base.LEFT_DIAGONAL, 2, Space.MOEB);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expected032 = 1;
		int actual032 = test.getNeighborhood(0, 3);
		assertEquals(expected032, actual032);
	}
	
	@Test
	public void testRightDiag() {
		topo = new Topology2D(Base.RIGHT_DIAGONAL, 1, Space.MOEB);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expected001 = 1;
		int actual001 = test.getNeighborhood(0, 0);
		assertEquals(expected001, actual001);
		
		int expected101 = 0;
		int actual101 = test.getNeighborhood(1, 0);
		assertEquals(expected101, actual101);
		
		int expected201 = 1;
		int actual201 = test.getNeighborhood(2, 0);
		assertEquals(expected201, actual201);
		
		int expected301 = 0;
		int actual301 = test.getNeighborhood(3, 0);
		assertEquals(expected301, actual301);
		
		int expected041 = 1;
		int actual041 = test.getNeighborhood(0, 4);
		assertEquals(expected041, actual041);
		
		int expected141 = 2;
		int actual141 = test.getNeighborhood(1, 4);
		assertEquals(expected141, actual141);
		
		int expected241 = 0;
		int actual241 = test.getNeighborhood(2, 4);
		assertEquals(expected241, actual241);
		
		int expected341 = 1;
		int actual341  = test.getNeighborhood(3, 4);
		assertEquals(expected341, actual341);
		
		int expected221 = 1;
		int actual221 = test.getNeighborhood(2, 2);
		assertEquals(expected221, actual221);
		
		int expected321 = 1;
		int actual321 = test.getNeighborhood(3, 2);
		assertEquals(expected321, actual321);
		
	}
	
	@Test 
	public void TestRightDiag2(){
		topo = new Topology2D(Base.RIGHT_DIAGONAL, 2, Space.MOEB);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expected002 = 2;
		int actual002 = test.getNeighborhood(0, 0);
		assertEquals(expected002, actual002);
	}
	
	@Test 
	public void testMoore1(){
		topo = new Topology2D(Base.MOORE, 1, Space.MOEB);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expected10 = 2;
		int actual10 = test.getNeighborhood(1, 0);
		assertEquals(expected10, actual10);
		
		int expected24 = 4;
		int actual24 = test.getNeighborhood(2, 4);
		assertEquals(expected24, actual24);
		
	}
	
	@Test
	public void testMoore2() {
		topo = new Topology2D(Base.MOORE, 2, Space.MOEB);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int e33 = 6;
		int a33 = test.getNeighborhood(3, 3);
		assertEquals(e33, a33);
		
		int e01 = 6;
		int a01 = test.getNeighborhood(0, 1);
		assertEquals(e01, a01);
		
	}

}
