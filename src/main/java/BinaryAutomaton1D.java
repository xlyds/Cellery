import cellery.rules.Rule;

/**
 * A 2-state {@link Automaton1D} 
 * @author Zach Tidwell
 */
public class BinaryAutomaton1D extends Automaton1D{
	
	/**
	 * Constructs a 2-state {@link Automaton1D} from an initial state given by a {@link CellArray1D} and a {@link cellery.rules.Rule}.
	 * @param cells A {@link CellArray1D} that provides the initial state of this 
	 * @param rule The {@link cellery.rules.Rule} that governs the evolution of this.
	 */
	public BinaryAutomaton1D(CellArray1D cells,  Rule rule) {
		super(cells, rule);
	}
	
	@Override
	public void iterate() {
		CellArray1D buffer = this.buffer();
		for (int i = 0; i < this.length; i++) {
			Cell thisCell = this.getCell(i);
			int neighbors = this.cells().getNeighborhood(i);
				
			if ( (i == 0 || i == length - 1) && this.getRule().ignoreBoundary) {
				if (thisCell.isAlive())
					buffer.reviveCell(i);
				else
					buffer.killCell(i);
				continue;
			}
				
			if ( !thisCell.isAlive() ) {
				
				if ( this.getRule().applyB(neighbors) ){
					buffer.reviveCell(i);
				}
				else{
					buffer.killCell(i);
					}
			} 
			if ( thisCell.isAlive() ) {

				if ( this.getRule().applyS(neighbors) ){
						buffer.reviveCell(i);
				}
				else {
					buffer.killCell(i);
				}
			}
		}
		this.updateCells(buffer);
		this.next();
		
	}

}
