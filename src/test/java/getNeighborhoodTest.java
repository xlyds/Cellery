import static org.junit.Assert.*;

import org.junit.Test;

import cellery.CellArray1D;

public class getNeighborhoodTest {
	CellArray1D test = new CellArray1D(new int[6]);

	@Test
	public void test() {
		// {0,1,1,0,0,1}
		test.reviveCell(1);
		test.reviveCell(2);
		test.reviveCell(5);

		int expected02 = 2;
		int actual02 = test.radial(0, 2);
		assertEquals(expected02, actual02);

		int expected20 = 0;
		int actual20 = test.radial(2, 0);
		assertEquals(expected20, actual20);

		int expected01 = 1;
		int actual01 = test.radial(0, 1);
		assertEquals(expected01, actual01);

		int expected52 = 0;
		int actual52 = test.radial(5, 2);
		assertEquals(expected52, actual52);

		int expected51 =0;
		int actual51 = test.radial(5, 1);
		assertEquals(expected51, actual51);

		int expected31 = 1;
		int actual31 = test.radial(3, 1);
		assertEquals(expected31, actual31);

		int expected22 = 1;
		int actual22 = test.radial(2, 2);
		assertEquals(expected22, actual22);

		int expected23 = 2;
		int actual23 = test.radial(2, 3);
		assertEquals(expected23, actual23);

	}

}
