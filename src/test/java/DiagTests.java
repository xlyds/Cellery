package java;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.cellery.topology.Topology2D;
import java.cellery.topology.Topology2D.Base;
import java.cellery.topology.Topology2D.Space;
import java.cellery.CellArray2D;

public class DiagTests {
	int[][] testBin;
	Topology2D topo;
	
	@Before
	public void setup(){
		testBin = new int[][] {{0,1,0}, 
		           			   {0,1,0}, 
		                       {1,0,0}};

	}
	@Test
	public void testRD1() {
		topo = new Topology2D(Base.RIGHT_DIAGONAL, 1, Space.STD);
		CellArray2D test = new CellArray2D(testBin, topo);

		int expectedr001 = 0;
		int actualr001 = test.getNeighborhood(0, 0);
		assertEquals(expectedr001, actualr001);

		int expectedr111 = 1;
		int actualr111 = test.getNeighborhood(1, 1);
		assertEquals(expectedr111, actualr111);

		int expectedr201 = 1;
		int actualr201 = test.getNeighborhood(2, 0);
		assertEquals(expectedr201, actualr201);

		int expectedr021 = 1;
		int actualr021 = test.getNeighborhood(0, 2);
		assertEquals(expectedr021, actualr021);


	}
	
	@Test
	public void testRD2(){
		topo = new Topology2D(Base.RIGHT_DIAGONAL, 2, Space.STD);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expectedr112 = 1;
		int actualr112 = test.getNeighborhood(1, 1);
		assertEquals(expectedr112, actualr112);
		
		int expectedr202 = 1;
		int actualr202 = test.getNeighborhood(2, 0);
		assertEquals(expectedr202, actualr202);
	}
	
	@Test
	public void testLD1(){
		topo = new Topology2D(Base.LEFT_DIAGONAL, 1, Space.STD);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expectedl201 = 0;
		int actuall201 = test.getNeighborhood(2, 0);
		assertEquals(expectedl201, actuall201);
		
		int expectedl111 = 0;
		int actuall111 = test.getNeighborhood(1, 1);
		assertEquals(expectedl111, actuall111);
		
		int expectedl001 = 1;
		int actuall001 = test.getNeighborhood(0, 0);
		assertEquals(expectedl001, actuall001);
		
		int expectedl221 = 1;
		int actuall221 = test.getNeighborhood(2, 2);
		assertEquals(expectedl221, actuall221);

	}
	
	@Test
	public void testLD2(){
		topo = new Topology2D(Base.LEFT_DIAGONAL, 2, Space.STD);
		CellArray2D test = new CellArray2D(testBin, topo);
		
		int expectedl002 = 1;
		int actuall002 = test.getNeighborhood(0, 0);
		assertEquals(expectedl002, actuall002);
		
		int expectedl112 = 0;
		int actuall112 = test.getNeighborhood(1, 1);
		assertEquals(expectedl112, actuall112);
	}

}
