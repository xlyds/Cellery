import static org.junit.Assert.*;

import org.junit.Test;

import cellery.topology.Topology2D;
import cellery.topology.Topology2D.Base;
import cellery.topology.Topology2D.Space;
import cellery.CellArray2D;

public class BinaryArrayTests {
	int[][] testBin = {{0,1,0}, 
					   {0,1,0},
					   {1,0,0}};
	
	Topology2D topo = new Topology2D(Base.MOORE, 1, Space.STD);

	@Test
	public void test() {
		CellArray2D test = new CellArray2D(testBin, topo);
		test.reviveCell(2, 0);
		test.reviveCell(0, 1);
		test.reviveCell(1, 1);
		int[][] expected = {{ 0, 1, 0 },
							{ 0, 1, 0 },
							{ 1, 0, 0 } };
		int[][] actual = test.toArray();
		assertArrayEquals(expected, actual);
	}
}
