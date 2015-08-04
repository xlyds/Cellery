package java.cellery;

import processing.core.*;

/**
 * Cells are objects with two states: dead or alive. Cells can be killed or revived. 
 * Cells can have an two different RGB colors for each state.
 * 
 * @author Zach Tidwell
 * 
 */
public class Cell extends PApplet {

	
	private static final long serialVersionUID = -7200463615313274951L;

	private boolean alive;
	private int aliveColor = color(0, 200, 0, 255);
	private int deadColor = color(0, 0, 0, 255);
	
	/**
	 * Instantiates a dead {@link Cell}
	 */
	public Cell() {
		this(false);
	}

	/**
	 * Instantiates a new {@link Cell}. If int i is 1,
	 * then is cell is alive, and dead otherwise.
	 * @param i the encoding integer
	 */
	public Cell(int i) {
        this.alive = i == 1;
	}

	/**
	 * Instantiates a new {@link Cell}. If boolean alive is true,
	 * then the {@link Cell} is alive and dead otherwise.
	 * @param alive the encoding boolean
	 */
	public Cell(boolean alive) {
		this.alive = alive;
	}
	
	/**
	 * Kill this {@link Cell}
	 */
	public void kill() {
		this.alive = false;
	}

	/**
	 * Revive this {@link Cell}
	 */
	public void revive() {
		this.alive = true;
	}
	
	/**
	 * Returns true if this {@link Cell} is alive.
	 * @return is this alive?
	 */
	public boolean isAlive() {
		return this.alive;
	}

	/**
	 * Returns the bit value of this {@link Cell}.
	 * @return 0 if dead, 1 otherwise
	 */
	public int toBit() {
		if (this.isAlive())
			return 1;
		else
			return 0;

	}
	
	/**
	 * Gives the current color set to this {@link Cell} when it is alive.
	 * @return the RGB color
	 */
	public int getAliveColor() {
		return this.aliveColor;
	}
	/**
	 * Gives the current color set to this {@link Cell} when it is dead.
	 * @return the RGB color
	 */
	public int getDeadColor() {
		return this.deadColor;
	}

	/**
	 * Sets a grays scale color to this {@link Cell} when its is alive.
	 * @param B gray scale color
	 */
	public void setAliveColor(int B) {
		this.aliveColor = color(B);
	}

	/**
	 * Sets an RGB color to this {@link Cell} when alive. Input parameters should be
	 * in range [0,255].
	 * 
	 * @param R red
	 * @param G green 
	 * @param B blue
	 */
	public void setAliveColor(int R, int G, int B) {
		this.aliveColor = color(R, G, B);
	}

	/**
	 * Sets an RGB color to this {@link Cell} when alive with transparency. Input parameters should
	 * be in range [0,255].
	 * 
	 * @param R red
	 * @param G green
	 * @param B blue
	 * @param alpha transparency
	 */
	public void setAliveColor(int R, int G, int B, int alpha) {
		this.aliveColor = color(R, G, B, alpha);
	}

	/**
	 * Sets a gray scale color to this {@link Cell} when dead. Input parameter
	 * should be in range [0,255].
	 * @param B the grey scale value
	 */
	public void setDeadColor(int B) {
		this.deadColor = color(B);
	}

	/**
	 * Sets an RGB color to this {@link Cell} when dead. Input parameters 
	 * should be in range [0,255].
	 * 
	 * @param R red
	 * @param G green
	 * @param B blue
	 */
	public void setDeadColor(int R, int G, int B) {
		this.deadColor = color(R, G, B);
	}

	/**
	 * Sets an RGB color to this {@link Cell} when dead with transparency. Input parameters
	 * should be in range [0,255].
	 * 
	 * @param R red
	 * @param G green
	 * @param B blue
	 * @param alpha transparency
	 */
	public void setDeadColor(int R, int G, int B, int alpha) {
		this.deadColor = color(R, G, B, alpha);
	}
}
