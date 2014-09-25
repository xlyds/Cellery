package caTools;
/**
 * CellularAutomata object endowed with the Game of Life rules
 * @author Zach Tidwell
 *
 */
public class Life extends CellularAutomata2D {

	public Life(CellArray2D cells) {
		super(cells);
	}

	public void iterate() {
		CellArray2D buffer = this.buffer();
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				int moore = this.cells().moore(i,j);

				if (this.cells().getCell(i, j).isAlive()) {
					if (moore < 2 || moore > 3)
						buffer.killCell(i, j);
					else
						buffer.resurrectCell(i, j);
				} else {
					if (moore == 3)
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
