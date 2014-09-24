package caTools;

public class TessellationAutomata4A extends CellularAutomata2D {

	public TessellationAutomata4A(CellArray2D cells) {
		super(cells);
	}

	public void iterate() {

		CellArray2D buffer = this.buffer();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				// dont count the boundaries
				if (i == 0 || j == 0 || i == length - 1 || j == width - 1) {

					if (this.cells().getCell(i, j).isAlive())
						buffer.resurrectCell(i, j);
					else
						buffer.killCell(i, j);
					continue;
				}

				int h = this.cells().getHorizontal(i, j, 1).livingCells();
				int v = this.cells().getVertical(i, j, 1).livingCells();
				int d1 = this.cells().getLeftDiag(i, j, 1).livingCells();
				int neighbors = h + v + d1;
				if (this.cells().getCell(i, j).isAlive())
					if (neighbors == 3)
						buffer.killCell(i, j);
					else
						buffer.resurrectCell(i, j);
				if (!this.cells().getCell(i, j).isAlive())
					if (neighbors == 3)
						buffer.resurrectCell(i, j);
					else
						buffer.killCell(i, j);

			}
		}
		updateCells(buffer);
		next();
	}

}
