import rules.*;
import cellery.*;
import topology.*;

protected int cellSize;
protected int gridWidth;
protected int gridHeight;
int density;
int[] defects1D;
Topology1D topo;

CellArray1D cells;
Rule rule;
BinaryAutomaton1D ca;

void setup(){
    size(1000, 600, JAVA2D);
    noStroke();
    background(0, 0, 55);
    
    //Controls the density of living cells in the initial state
    density = 50;
    
    //Pixel width of the cells
    cellSize = 4;
    
    //setup grid geometry
    gridWidth = width / cellSize;
    gridHeight = height / cellSize;
    
    topo = new Topology1D(Topology1D.Base.RADIAL,3, Topology1D.Space.CIRC);    
    
    //Place random living cells into the grid
    defects1D = CellerySeeds.randomOnes(density, gridWidth, gridHeight)[0];
    
    
    //Create a rule for this 1D automaton
    int[] B = {1,3};
    int[] S = {0,4,5,6};
    rule = new Rule(B, S);
    
    //Create a CellArray using the topology and the seeded initial state
    cells = new CellArray1D(defects1D, topo);
    
    ca = new BinaryAutomaton1D(cells, rule);
  
}

void draw() {
      drawStep1D(ca);
}

void drawStep1D(Automaton1D ca) {
  int j = ca.step();
  for (int i = 0; i < gridWidth; i++) {
    ca.getCell(i).setAliveColor(
        (int) (155 + random(100) ),
        (int) (105 + random(150) ),
        (int) 0);
    ca.getCell(i).setDeadColor(0,0,55);
    if (ca.getCell(i).isAlive())
      fill(ca.getCell(i).getAliveColor());
    else
      fill(ca.getCell(i).getDeadColor());
    rect(cellSize * i, cellSize * j, cellSize, cellSize);
  }
  ca.iterate();
}

