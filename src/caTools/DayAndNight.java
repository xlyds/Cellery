package caTools;

public class DayAndNight extends CellularAutomata2D{
	
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
}
