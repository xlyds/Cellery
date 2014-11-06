import rules.*;
import cellery.*;
import topology.*;

protected int cellSize;
protected int gridWidth;
protected int gridHeight;

CellArray1D cells;
ElementaryRule rule;
ElementaryAutomaton ca;

int[] singleDefect1D;

void setup(){
    size(1000, 600, JAVA2D);
    noStroke();
    background(0, 0, 55);
    cellSize = 4;
    gridWidth = width / cellSize;
    gridHeight = height / cellSize;

    singleDefect1D = new int[gridWidth];
    singleDefect1D[gridWidth/2] = 1;
    
    rule = ElementaryRule.ER70;
    
    cells = new CellArray1D(singleDefect1D);
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
    singleDefect1D = new int[gridWidth];
    singleDefect1D[gridWidth/2] = 1;
    cells = new CellArray1D(singleDefect1D);
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
