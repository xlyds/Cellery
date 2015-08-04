package java.cellery;

import java.cellery.rules.Rule;

/**
 * A 2-state {@link Automaton2D} 
 * @author Zach Tidwell
 */
public class BinaryAutomaton2D extends Automaton2D {
	
	/**
	 * Constructs a 2-state {@link Automaton2D} from an initial state given by a {@link CellArray2D} and a {@link java.cellery.rules.Rule}.
	 * @param cells A {@link CellArray2D} that provides the initial state of this 
	 * @param rule The {@link java.cellery.rules.Rule} that governs the evolution of this.
	 */
	public BinaryAutomaton2D(CellArray2D cells, Rule rule) {
		super(cells, rule);
	}

	@Override
	public void iterate() {
		CellArray2D buffer = this.buffer();
		for (int i = 0; i < this.length; i++) {
			for (int j = 0; j < this.width; j++) {
				Cell thisCell = this.getCell(i,j);
				int neighbors = this.cells().getNeighborhood(i, j);
				
				if ( (i == 0 || j == 0 || i == length - 1 || j == width - 1) && this.getRule().ignoreBoundary) {
					if (thisCell.isAlive())
						buffer.reviveCell(i, j);
					else
						buffer.killCell(i, j);
					continue;
				}
				
				if ( !thisCell.isAlive() ) {
					if ( this.getRule().applyB(neighbors) ){
						buffer.reviveCell(i, j);
					}
					else {
						buffer.killCell(i, j);
						}
				} 
				if ( thisCell.isAlive() ) {

					if ( this.getRule().applyS(neighbors) ){
						buffer.reviveCell(i, j);
					}
					else {
						buffer.killCell(i, j);
					}
				}
			}
		}
		this.updateCells(buffer);
		this.next();
	}
	

}
