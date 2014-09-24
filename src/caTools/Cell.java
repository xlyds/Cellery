package caTools;

import processing.core.*;

/**
 * Cells are objects that can be killed, resurrected. Cells can have an RGB
 * color.
 * 
 * @author Zach Tidwell
 * 
 */
public class Cell extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7200463615313274951L;

	private boolean alive;
	private int aliveColor = color(0, 200, 0, 255);
	private int deadColor = color(0, 0, 0, 255);

	public Cell() {
		this(false);
	}

	public Cell(int i) {
		if (i == 1)
			this.alive = true;
		else
			this.alive = false;
	}

	public Cell(boolean alive) {
		this.alive = alive;
	}

	public void kill() {
		this.alive = false;
	}

	public void resurrect() {
		this.alive = true;
	}

	public boolean isAlive() {
		return this.alive;
	}

	/**
	 * returns the bit value of this Cell
	 * 
	 * @return
	 */
	public int toBit() {
		if (this.isAlive())
			return 1;
		else
			return 0;

	}

	public int getAliveColor() {
		return this.aliveColor;
	}

	public int getDeadColor() {
		return this.deadColor;
	}

	/**
	 * Sets a grays scale color to alive cells
	 * 
	 * @param B
	 *            grayscale
	 */
	public void setAliveColor(int B) {
		this.aliveColor = color(B);
	}

	/**
	 * Sets an RGB color to cell when alive.
	 * 
	 * @param R
	 * @param G
	 * @param B
	 */
	public void setAliveColor(int R, int G, int B) {
		this.aliveColor = color(R, G, B);
	}

	/**
	 * Sets an RGB color to cell when dead with transparency.
	 * 
	 * @param R
	 * @param G
	 * @param B
	 * @param alpha
	 *            transparency
	 */
	public void setAliveColor(int R, int G, int B, int alpha) {
		this.aliveColor = color(R, G, B, alpha);
	}

	/**
	 * Sets a gray scale color to cell when dead.
	 * 
	 * @param B
	 */
	public void setDeadColor(int B) {
		this.deadColor = color(B);
	}

	/**
	 * Sets an RGB color to cell when dead.
	 * 
	 * @param R
	 * @param G
	 * @param B
	 */
	public void setDeadColor(int R, int G, int B) {
		this.deadColor = color(R, G, B);
	}

	/**
	 * Sets an RGB color to cell when dead with transparency.
	 * 
	 * @param R
	 * @param G
	 * @param B
	 * @param alpha
	 *            transparency
	 */
	public void setDeadColor(int R, int G, int B, int alpha) {
		this.deadColor = color(R, G, B, alpha);
	}
}
