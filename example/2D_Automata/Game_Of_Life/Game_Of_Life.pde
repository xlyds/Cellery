import cellery.rules.*;
import cellery.*;
import cellery.topology.*;



int cellSize;
int gridWidth;
int gridHeight;
int density;
/**
*To create the Game of Life, or any 2D automaton, using Cellery we must declare and specify the 3 elements of an 
*Automaton: The underlying space that the automaton lives in, the topology on that space and finally the rule that governs the automaton.
**/
Topology2D topo;
CellArray2D cells;
Rule rule;
/**
*These three elements determine the behavior of the Automaton
**/
BinaryAutomaton2D ca;

int[][] grid;


void setup(){
   size(1000, 600, JAVA2D);
    noStroke();
    background(0, 0, 0);
    
    //Controls the density of living cells in the initial state
    density = 80;
    
    //Dimension of the square shaped cell
    cellSize = 4;
    
    //setup grid geometry
    gridWidth = width / cellSize;
    gridHeight = height / cellSize;
    
    //Create a rule for GOL, which is B3/S23
    int[] B = {3};
    int[] S = {2,3};
    rule = new Rule(B, S);
    
    //Seed the grid with a random initial state
    grid = CellerySeeds.randomOnes(density, gridWidth, gridHeight);
    
    //Create a standard topology with the moore neighborhood of range 1
    topo = new Topology2D(Topology2D.Base.MOORE, 1, Topology2D.Space.STD);
    
    //Create a CellArray with the correct topology and seeded initial state
    cells = new CellArray2D(grid, topo);
    
    ca = new BinaryAutomaton2D(cells, rule);
    
}

void draw(){
  //Draw each step!
  drawStep2D(ca);
}

public void drawStep2D(Automaton2D ca) {
  int t = ca.step();
  // Iterate down each row of pixels
  for (int i = 0; i < gridHeight; i++) {
    // Iterate across each column in a row
    for (int j = 0; j < gridWidth; j++) {
      // Color Settings.
      ca.getCell(i, j).setAliveColor(
          (int) (255 * sin(PI / 1028 * t)),
          (int) (200 * cos(PI / 1028 * t)),
          (int) (185 * sin(PI / 512 * t)));
      ca.getCell(i, j).setDeadColor(0,0,0);
      if (ca.getCell(i, j).isAlive())
        fill(ca.getCell(i, j).getAliveColor());
      else
        fill(ca.getCell(i, j).getDeadColor());
      rect(cellSize * (j), cellSize * (i), cellSize, cellSize);

    }
  }
   ca.iterate();
}
