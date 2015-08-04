package java.cellery;

import java.cellery.rules.Rule;


/**
 * Automaton is an object that can evolve {@link Cell}s contained within a
 * {@link CellArray2D} or {@link CellArray1D}.
 * 
 * @author Zach Tidwell
 * 
 */
public abstract class Automaton {
	
	/**
	 * The number of iterations that has been applied to this
	 */
	private int step;
	
	/**
	 * The {@link java.cellery.rules.Rule} that governs this {@link Automaton}
	 */
	private Rule rule;
	
	/**
	 * Calculates the next generation of this.
	 */
	public abstract void iterate();
		//put evolution rules here
	
	/**
	 * Returns the number of times this has been iterated.
	 * @return the number of times this has been iterated.
	 */
	public int step() {
		return this.step;
	}
	
	/**
	 * Increments the iteration count of this.
	 */
	public void next() {
		// should probably be somewhere in iterate()
		this.step++;
	}
	
	/**
	 * Returns the Rule for this Automaton
	 * @return the rule governing this Automaton
	 */
	public Rule getRule(){
		return this.rule;
	}
	
	/**
	 * Sets the rule of this Automaton
	 * @param rule the new rule governing this Automaton
	 */
	public void setRule(Rule rule){
		this.rule = rule;

	}
	

}
