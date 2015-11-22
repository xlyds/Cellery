import static org.junit.Assert.*;

import cellery.CellArray1D;
import org.junit.Test;

public class LivingCellsTest {
	CellArray1D test = new CellArray1D(new int[6]);

	@Test
	public void test() {
		// {0,1,1,0,0,1}
        test.reviveCell(1);
        test.reviveCell(2);
        test.reviveCell(5);
		int expected = 3;
		int actual = test.livingCells();
		assertEquals(expected, actual);
	}

}
