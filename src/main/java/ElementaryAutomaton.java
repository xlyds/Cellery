import cellery.rules.ElementaryRule;
import cellery.rules.Rule;

/**
 * A 2-state {@link Automaton1D} controlled by Stephen Wolfram's 256 Elementary Automation Rules.
 * The Rules for this Automaton are governed by the byte-codes enumerated in {@link cellery.rules.ElementaryRule}.
 * @author Zachary Tidwell
 *
 */
public class ElementaryAutomaton extends Automaton1D {
	
	private String rule;
	
	
	/**
	 * Constructs an {@link ElementaryAutomaton} from an initial state furnished by a {@link CellArray1D} and a Wolfram Code furnished by
	 * {@link cellery.rules.ElementaryRule}
	 * @param cells A {@link CellArray1D} that provides the initial state of this 
	 * @param E A {@link cellery.rules.ElementaryRule} representing the Wolfram Code that governs the evolution of this.
	 */
	public ElementaryAutomaton(CellArray1D cells, ElementaryRule E) {
		super(cells, new Rule(new int[] {}, new int[] {}, false));
		setRule(E);
	}
	
	/**
	 * Resets the current {@link cellery.rules.ElementaryRule} with the on provided.
	 * @param E The new {@link cellery.rules.ElementaryRule}
	 */
	public void setRule(ElementaryRule E){
		this.rule = E.getCode();
	}
	
	@Override
	public void iterate() {
		
		for (int i = 0; i < this.length; i++) {
			String cellPattern = "" + cells().leftOf(i, 1) 
					                + cells().getCell(i).toBit() 
					                + cells().rightOf(i,1);
			switch(cellPattern){
			
			case "111":
				applyRule(0, i);
				break;
			case "110":
				applyRule(1, i);
				break;
			case "101":
				applyRule(2, i);
				break;
			case "100":
				applyRule(3, i);
				break;
			case "011":
				applyRule(4, i);
				break;
			case "010":
				applyRule(5, i);
				break;
			case "001":
				applyRule(6, i);
				break;
			case "000":
				applyRule(7, i);
				break;
				
			}
		}
		this.updateCells( buffer() );
		this.next();
	}
	
	private void applyRule(int j, int i){
		if ( this.rule.charAt(j) == '1' )
			buffer().reviveCell(i);
		if (this.rule.charAt(j) == '0')
			buffer().killCell(i);
		
	}
}
