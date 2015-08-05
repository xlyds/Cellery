package cellery.rules;


/**
 * Handles the transition rules of a {@link cellery.Automaton}. Rules are encoded in arrays of non-negative integers up to the cardinality of the neighborhood
 * of the CellArray that underlies the client Automaton.
 * @see  <a href = "http://en.wikipedia.org/wiki/Life-like_cellular_automaton#Notation_for_rules">Notation for rules</a>, {@link cellery.topology.Topology2D.Base Base}
 * @author Zach Tidwell
 *
 */
public class Rule {
	
	/**
	 * The Rules for the birth of a {@link cellery.Cell} in the next generation.
	 */
	private final int[] B;
	
	/**
	 * The Rules for the survival of a {@link cellery.Cell} in the next generation
	 */
	private final int[] S;
	
	/**
	 * Boolean indicating if this Rule should ignore Boundaries when computing transitions.
	 */
	public final boolean ignoreBoundary;
	
	/**
	 * Creates a {@link Rule} from two int arrays that carry the conditions for the transition of Cells to the next
	 * state for each iteration of the client {@link cellery.Automaton}. It assumed that each array will contain ints in the range
	 * of the cardinality of the neighborhood that is specified for the Automaton by its underlying  
	 * @param B an array 
	 * @param S
	 * 
	 */
	public Rule( int[] B, int[] S){
		this(B, S, false);
	}
	/**
	 * Creates a {@link Rule} from two int arrays that carry the conditions for the transition of Cells to the next
	 * state for each iteration of the client {@link cellery.Automaton}. It assumed that each array will contain ints in the range
	 * of the cardinality of the neighborhood that is specified for the Automaton by its underlying  
	 * @param B an array 
	 * @param S
	 * @param ignoreBoundary
	 */
	public Rule( int[] B, int[] S, boolean ignoreBoundary ){
		this.B = B;
		this.S = S;
		this.ignoreBoundary = ignoreBoundary;
	}
	
	
	
	public boolean applyB(int neighbors) {
		boolean value = false;
		for (int rule : B){
			if ( rule == neighbors ){
				value = true;
				break;
			}
		}
		return value;
	}
	
	public boolean applyS(int neighbors) {
		boolean value = false;
		for (int rule: S){
			if ( rule == neighbors ){
				value = true;
				break;
			}
		}
		return value;
	}
	
}
