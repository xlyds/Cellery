import rules.*;
import cellery.*;
import topology.*;

protected int cellSize;
protected int gridWidth;
protected int gridHeight;
int density;

CellArray1D cells;
ElementaryRule rule;
ElementaryAutomaton ca;

int[] defects1D;

void setup(){
    size(1000, 600, JAVA2D);
    noStroke();
    background(0, 0, 55);
    
    density = 50;
    cellSize = 4;
    gridWidth = width / cellSize;
    gridHeight = height / cellSize;

    defects1D = CellerySeeds.randomOnes(density, gridWidth, gridHeight)[0];
    
    rule = ElementaryRule.ER0;
    
    cells = new CellArray1D(defects1D);
    ca = new ElementaryAutomaton(cells, rule);
  
}

void draw() {
  
      drawStep1D(ca);
      ca.setRule(rule);
}

void keyPressed(){
  if (key==ENTER){
    print(rule.ordinal() + 1);
    rule = ElementaryRule.values()[rule.ordinal() + 1];
    defects1D = CellerySeeds.randomOnes(density, gridWidth, gridHeight)[0];
    cells = new CellArray1D(defects1D);
    ca = new ElementaryAutomaton(cells, rule);
  }
  
  if (key=='s'){
    saveFrame("rule" + rule.ordinal() + ".jpg");
  }
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
