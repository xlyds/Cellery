package caTools;

public class CellerySeeds {

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
}
