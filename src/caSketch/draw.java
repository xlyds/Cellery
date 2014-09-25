package caSketch;

import caTools.*;
import processing.core.*;

@SuppressWarnings("serial")
public class draw extends PApplet {

	protected float density;
	protected int cellSize;
	protected int gridWidth;
	protected int gridHeight;
	int[] singleDefect1D;
	int[][] singleDefect2D;

	int timeInterval = 100;
	int lastRunTime = 0;

	Sierpenski mySierpenski;
	TessellationAutomata2 myTA;
	TessellationAutomata4B myTA4B;
	Life myLife;
	DayAndNight myDay;

	public void setup() {
		size(600, 300, JAVA2D);
		noStroke();
		background(0);
		
		density = (float) 85;
		cellSize = 5;
		gridWidth = width / cellSize;
		gridHeight = height / cellSize;

		// singleDefect1D = new int[gridWidth];
		// singleDefect1D[gridWidth/2] = 1;
		//
		// singleDefect2D = new int[gridWidth][gridHeight];
		// singleDefect2D[gridWidth/2][gridHeight/2] = 1;

		// CellArray1D singleCell = new CellArray1D(singleDefect1D);
		// CellArray2D singleCell2D = new CellArray2D( randomOnes(density,
		// gridWidth, gridHeight) );
		// int[][] halfLattice = CellerySeeds.mixed(gridHeight, gridWidth);

		// myCA = new CellularAutomata(gridWidth, gridHeight, density, 6, "on"
		// );
		// mySierpenski = new Sierpenski(singleCell);
		// myTA = new TessellationAutomata2(singleCell2D, 2, 0);
		myLife = new Life( new CellArrayCylinder( CellerySeeds.randomOnes(density, gridWidth, gridHeight) ) );
		//myTA4B = new TessellationAutomata4B(new CellArray2D(halfLattice));
		//myDay = new DayAndNight(new CellArrayCylinder( CellerySeeds.randomOnes(density, gridWidth, gridHeight) ));
	}

	public void draw() {
		if (maybeIterate()) {
			drawStep2D(myLife);
			lastRunTime = millis();
		}
		
		if (keyCode == ENTER){
			saveFrame("home/zach/Pictures/CA/f.jpg");
		}
	}

	public boolean maybeIterate() {
		return abs(millis() - lastRunTime) > timeInterval;

	}

	public void drawStep1D(CellularAutomata1D ca) {
		int j = ca.step();
		for (int i = 0; i < gridWidth; i++) {
			ca.getCell(i).setAliveColor(i + j, 200 - i + j, (i - j));
			if (ca.getCell(i).isAlive())
				fill(ca.getCell(i).getAliveColor());
			else
				fill(ca.getCell(i).getDeadColor());
			rect(cellSize * i, cellSize * j, cellSize, cellSize);
		}
		ca.iterate();
	}

	public void drawStep2D(CellularAutomata2D ca) {
		int t = ca.step();
		// Iterate down each row of pixels
		for (int i = 0; i < gridHeight; i++) {
			// Iterate across each column in a row
			for (int j = 0; j < gridWidth; j++) {
				// Color Settings.
				ca.getCell(i, j).setAliveColor(
						(int) (255 * sin(PI / 1028 * t)),
						(int) (200 * cos(PI / 1028 * t)),
						(int) (185 + (i - j) * sin(PI / 512 * t)));
				if (ca.getCell(i, j).isAlive())
					fill(ca.getCell(i, j).getAliveColor());
				else
					fill(ca.getCell(i, j).getDeadColor());
				rect(cellSize * (j), cellSize * (i), cellSize, cellSize);
			}
		}
		ca.iterate();
	}

}
