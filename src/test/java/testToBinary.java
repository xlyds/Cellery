import static org.junit.Assert.*;
import org.junit.Test;

import cellery.CellArray1D;

public class testToBinary {
	CellArray1D test = new CellArray1D(new int[6]);

	@Test
	public void testToBinary0() {
        test.reviveCell(1);
        test.reviveCell(2);
        test.reviveCell(5);

		int[] expected = { 0, 1, 1, 0, 0, 1 };
		int[] actual = test.toArray();
		assertArrayEquals(expected, actual);

	}

}
