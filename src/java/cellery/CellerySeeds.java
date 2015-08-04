package java.cellery;

/**
 * Static methods for generating initial conditions for 2-state Automata.
 * @author Zach Tidwell
 */
public class CellerySeeds {

	/**
	 * Generates a rectangular binary array with alternating 0's and 1's of the following form:
	 * 						
	 * 							0 1 0 1 0 1 0
	 * 							1 0 1 0 1 0 1
	 * 							0 1 0 1 0 1 0
	 * 							1 0 1 0 1 0 1		
	 *							0 1 0 1 0 1 0
	 *							1 0 1 0 1 0 1
	 *							0 1 0 1 0 1 0 
	 *
	 * @param length the number of rows.
	 * @param width the number of columns.
	 * @return a centered rectangular array.
	 */
	public static int[][] centeredRectangular(int length, int width) {
		int[][] cr = new int[length][width];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				cr[i][j] = (i + j) % 2;
			}
		}
		int ir = (int) (100 * Math.random());
		int jr = (int) (100 * Math.random());
		cr[ir][jr] = 1;
		return cr;
	}

	/**
	 * Generates a rectangular binary array with alternating 0's and 1's of the following form:
	 * 
	 * 				  		    1 0 1 0 1 0 1
	 * 							1 0 1 0 1 0 1
	 * 							1 0 1 0 1 0 1
	 * 							1 0 1 0 1 0 1
	 * 							1 0 1 0 1 0 1
	 * 							1 0 1 0 1 0 1
	 * 							1 0 1 0 1 0 1
	 * 
	 * @param length the number of rows
	 * @param width the number of columns
	 * @return a rectangular binary array
	 */
	public static int[][] rectangular(int length, int width) {
		int[][] r = new int[length][width];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				r[i][j] = j % 2;
			}
		}
		int ir = (int) (100 * Math.random());
		int jr = (int) (100 * Math.random());
		r[ir][jr] = 1;
		return r;
	}
	
	/**
	 * Generates a rectangular binary array with a centered rectangular pattern on the top half, and rectangular pattern
	 * on the remaining half.
	 * @param length the number of rows
	 * @param width the number of columns
	 * @return a rectangular binary array
	 */
	public static int[][] mixed(int length, int width) {
		int[][] r = new int[length][width];
		for (int i = 0; i < length / 2; i++) {
			for (int j = 0; j < width; j++) {
				r[i][j] = (i + j) % 2;
			}
		}
		for (int i = length / 2; i < length; i++) {
			for (int j = 0; j < width; j++) {
				r[i][j] = j % 2;
			}
		}

		r[length / 4][width / 2] = 1;
		r[length * 3 / 4 + 1][width / 2+1] = 1;
		return r;
	}
	
	/**
	 * Generates a rectangular binary array with randomly placed 1's and 0's whose density is controlled by an integer in range [0,100].
	 * @param density controls the density of 1's
	 * @param width the number of rows
	 * @param height the number of columns
	 * @return a rectangular binary array
	 */
	public static int[][] randomOnes(float density, int width, int height) {
		int[][] randomOnes = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int random = (int) (100*Math.random());
				if (random < density)
					randomOnes[i][j] = 0;
				else
					randomOnes[i][j] = 1;
			}
		}
		return randomOnes;
	}
}
