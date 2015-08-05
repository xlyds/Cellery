package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cellery.topology.Topology2D;
import cellery.topology.Topology2D.Base;
import cellery.topology.Topology2D.Space;
import cellery.CellArray2D;

public class OrthTests {
	int[][] testBin;
	CellArray2D cells;
	Topology2D topo1;
	
	/*
	 * {{0,1,0},
	 *  {1,1,0},
	 *  {1,0,0}}
	 */
	 
	
	@Before
	public void setup() {
		testBin = new int[][] {{0,1,0},
							   {1,1,0}, 
							   {1,0,0}};
		
	}
	
	@Test
	public void testH1() {
		topo1 = new Topology2D(Base.HORIZONTAL_2D, 1, Space.STD);
		CellArray2D test = new CellArray2D( testBin, topo1 );

		int expectedh001 = 1;
		int actualh001 = test.getNeighborhood(0, 0);
		assertEquals(expectedh001, actualh001);

		int expectedh111 = 1;
		int actualh111 = test.getNeighborhood(1, 1);
		assertEquals(expectedh111, actualh111);

		int expectedh221 = 0;
		int actualh221 = test.getNeighborhood(2, 2);
		assertEquals(expectedh221, actualh221);



	}
	
	@Test
	public void testH2(){
		topo1 = new Topology2D(Base.HORIZONTAL_2D, 2, Space.STD);
		CellArray2D test = new CellArray2D( testBin, topo1 );
		
		int expectedh002 = 1;
		int actualh002 = test.getNeighborhood(0, 0);
		assertEquals(expectedh002, actualh002);
	}
	
	@Test
	public void testV1(){
		topo1 = new Topology2D(Base.VERTICAL_2D, 1, Space.STD);
		CellArray2D test = new CellArray2D( testBin, topo1 );
		
		int expectedv111 = 1;
		int actualv111 = test.getNeighborhood(1, 1);
		assertEquals(expectedv111, actualv111);


		int expectedv001 = 1;
		int actualv001 = test.getNeighborhood(0, 0);
		assertEquals(expectedv001, actualv001);

		int expectedv221 = 0;
		int actualv221 = test.getNeighborhood(2, 2);
		assertEquals(expectedv221, actualv221);
	}
	
	@Test
	public void testV2(){
		topo1 = new Topology2D(Base.VERTICAL_2D, 2, Space.STD);
		CellArray2D test = new CellArray2D( testBin, topo1 );
		int expectedv112 = 1;
		int actualv112 = test.getNeighborhood(1, 1);
		assertEquals(expectedv112, actualv112);

	}

}
