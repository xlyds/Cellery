package cellery;


/**
 * {@link CellularAutomata2D} endowed with the Day & Night Rules.
 * @author zach
 *
 */
public class DayAndNight extends CellularAutomata2D{
	
	/**
	 * Constructs a {@link CellularAutomata2D} imbued with the Day & Night rules.
	 * http://en.wikipedia.org/wiki/Day_%26_Night
	 * @param cells
	 */
	public DayAndNight(CellArray2D cells){
		super(cells);
	}
	
	public void iterate(){
		CellArray2D buffer = this.buffer();
		for (int i = 0; i < this.length; i++){
			for (int j = 0; j < this.width; j++){
				//get the Moore neighborhood value of this cell
				int moore = this.cells().moore(i, j);
				if ( this.getCell(i, j).isAlive() ) {
					//Cell is alive, kill if necessary.
					if (moore == 3 || moore == 4 || moore == 6 || moore == 7 || moore == 8)
						buffer.resurrectCell(i, j);
					else
						buffer.killCell(i, j);
						
				} else {
					//Cell is dead, resurrect if necessary.
					if (moore == 3 || moore == 6 || moore == 7 || moore == 8 )
						buffer.resurrectCell(i, j);
					else
						buffer.killCell(i, j);
				}
			}
		}
		this.updateCells(buffer);
		this.next();
	}
	
	//TODO: Add inversion method.
}
