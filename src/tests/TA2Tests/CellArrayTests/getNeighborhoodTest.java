package tests.TA2Tests.CellArrayTests;

import static org.junit.Assert.*;

import org.junit.Test;

import caTools.CellArray1D;

public class getNeighborhoodTest {
	CellArray1D test = new CellArray1D(6);

	@Test
	public void test() {
		// {0,1,1,0,0,1}
		test.resurrectCell(1);
		test.resurrectCell(2);
		test.resurrectCell(5);

		int[] expected02 = { 1, 1 };
		int[] actual02 = test.getNeighbors(0, 2).toBinaryArray();
		assertArrayEquals(expected02, actual02);

		int[] expected20 = {};
		int[] actual20 = test.getNeighbors(2, 0).toBinaryArray();
		assertArrayEquals(expected20, actual20);

		int[] expected01 = { 1 };
		int[] actual01 = test.getNeighbors(0, 1).toBinaryArray();
		assertArrayEquals(expected01, actual01);

		int[] expected52 = { 0, 0 };
		int[] actual52 = test.getNeighbors(5, 2).toBinaryArray();
		assertArrayEquals(expected52, actual52);

		int[] expected51 = { 0 };
		int[] actual51 = test.getNeighbors(5, 1).toBinaryArray();
		assertArrayEquals(expected51, actual51);

		int[] expected31 = { 1, 0 };
		int[] actual31 = test.getNeighbors(3, 1).toBinaryArray();
		assertArrayEquals(expected31, actual31);

		int[] expected22 = { 0, 1, 0, 0 };
		int[] actual22 = test.getNeighbors(2, 2).toBinaryArray();
		assertArrayEquals(expected22, actual22);

		int[] expected23 = { 0, 1, 0, 0, 1 };
		int[] actual23 = test.getNeighbors(2, 3).toBinaryArray();
		assertArrayEquals(expected23, actual23);

	}

}
