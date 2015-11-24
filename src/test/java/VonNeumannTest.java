import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cellery.topology.Topology2D;
import cellery.topology.Topology2D.Base;
import cellery.topology.Topology2D.Space;
import cellery.CellArray2D;

public class VonNeumannTest {
	
	int[][] testBin;
	CellArray2D cells;
	Topology2D topo1;
	Topology2D topo2;
	
	@Before
	public void setUp(){
	testBin = new int[][]{ { 0, 0, 0, 0, 0 }, 
	                       { 1, 0, 0, 1, 0 },
	                       { 0, 0, 0, 0, 0 }, 
	                       { 0, 1, 0, 0, 0 }, 
	                       { 0, 0, 0, 0, 0 } };
	
	topo1 = new Topology2D(Base.VON_NEUMANN, 1, Space.STD);
	topo2 = new Topology2D(Base.VON_NEUMANN, 2, Space.STD);
	
	
	}

	@Test
	public void testTopo1() {
		
		cells = new CellArray2D(testBin,topo1);
		
		int expected221 = 0;
		int actual221 = cells.getNeighborhood(2, 2);
		assertEquals(expected221, actual221);
		
		
	}
	
	@Test
	public void testTopo2() {
		
		cells = new CellArray2D(testBin, topo2);
		
		int expected222 = 2;
		int actual222 = cells.getNeighborhood(2, 2);
		assertEquals(expected222, actual222);
		
		int expected112 = 3;
		int actual112 = cells.getNeighborhood(1, 1);
		assertEquals(expected112, actual112);
		
	}

}
