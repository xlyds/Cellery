package caTools;

public class Life extends CellularAutomata2D {

	public Life(CellArray2D cells) {
		super(cells);
	}

	public void iterate() {
		CellArray2D buffer = this.buffer();
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				int h = this.cells().getHorizontal(i, j, 1).livingCells();
				int v = this.cells().getVertical(i, j, 1).livingCells();
				int d1 = this.cells().getLeftDiag(i, j, 1).livingCells();
				int d2 = this.cells().getRightDiag(i, j, 1).livingCells();
				int neighbes = h + v + d1 + d2;

				if (this.cells().getCell(i, j).isAlive()) {
					if (neighbes < 2 || neighbes > 3)
						buffer.killCell(i, j);
					else
						buffer.resurrectCell(i, j);
				} else {
					if (neighbes == 3)
						buffer.resurrectCell(i, j);
					else
						buffer.killCell(i, j);
				}
			}
		}
		this.updateCells(buffer);
		this.next();
	}

}
