package tests.TA2Tests.CellArray2DTests;

import static org.junit.Assert.*;

import org.junit.Test;

import caTools.CellArray2D;

public class OrthTests {
	CellArray2D test = new CellArray2D(3, 3);

	/*
	 * {{0,1,0}, {1,1,0}, {1,0,0}}
	 */
	@Test
	public void test() {
		test.resurrectCell(2, 0);
		test.resurrectCell(1, 0);
		test.resurrectCell(0, 1);
		test.resurrectCell(1, 1);

		int[] expectedh001 = { 1 };
		int[] actualh001 = test.getHorizontal(0, 0, 1).toBinaryArray();
		assertArrayEquals(expectedh001, actualh001);

		int[] expectedh002 = { 1, 0 };
		int[] actualh002 = test.getHorizontal(0, 0, 2).toBinaryArray();
		assertArrayEquals(expectedh002, actualh002);

		int[] expectedh111 = { 1, 0 };
		int[] actualh111 = test.getHorizontal(1, 1, 1).toBinaryArray();
		assertArrayEquals(expectedh111, actualh111);

		int[] expectedh221 = { 0 };
		int[] actualh221 = test.getHorizontal(2, 2, 1).toBinaryArray();
		assertArrayEquals(expectedh221, actualh221);

		int[] expectedv111 = { 1, 0 };
		int[] actualv111 = test.getVertical(1, 1, 1).toBinaryArray();
		assertArrayEquals(expectedv111, actualv111);

		int[] expectedv112 = { 1, 0 };
		int[] actualv112 = test.getVertical(1, 1, 2).toBinaryArray();
		assertArrayEquals(expectedv112, actualv112);

		int[] expectedv001 = { 1 };
		int[] actualv001 = test.getVertical(0, 0, 1).toBinaryArray();
		assertArrayEquals(expectedv001, actualv001);

		int[] expectedv221 = { 0 };
		int[] actualv221 = test.getVertical(2, 2, 1).toBinaryArray();
		assertArrayEquals(expectedv221, actualv221);

	}

}
