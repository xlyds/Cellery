import static org.junit.Assert.*;
import org.junit.Test;

public class UpdateCellsTest {
	CellArray1D test = new CellArray1D(new int[6]);

	@Test
	public void test() {
		// {0,1,1,0,0,1}
        test.reviveCell(1);
        test.reviveCell(2);
        test.reviveCell(5);
		int[] expected0 = { 0, 0, 1, 0, 0, 1 };
		int[] expected1 = { 0, 0, 1, 1, 0, 1 };
		test.killCell(1);
		int[] actual0 = test.toArray();
		test.reviveCell(3);
		int[] actual1 = test.toArray();

		assertArrayEquals(expected0, actual0);
		assertArrayEquals(expected1, actual1);

	}

}
