import cellery.rules.*;
import cellery.*;
import cellery.topology.*;

float theta;

//Cyliner circumfrence
int cylCirc;
//Cylinder height
int cylHeight;

//Dimensions of the grid 
int cellSize;
int lifeWidth;
int lifeHeight;
int density;

//The amount of time between iterations of the CA in milliseconds
int timeInterval = 100;

//the previoud runtime in milliseconds
int lastRunTime = 0;

/**
*CA ingredients:The underlying space that the automaton lives in, the topology on that space and
*finally the rule that governs the automaton. 
*/
Topology2D topo;
CellArray2D seeds;
Rule life;
BinaryAutomaton2D ca;

void setup() {
  size(1200, 1000, P3D);

  
  //Assign cylinder geometry values
  cylCirc = 1600;
  cylHeight = 300;
  
  //Assign grid geometry values
  cellSize = 10;
  lifeWidth = cylCirc/cellSize;
  lifeHeight = cylHeight/cellSize;
  density = 50;
  
  // assemble CA ingredients 
  topo = new Topology2D(Topology2D.Base.MOORE, 1, Topology2D.Space.CYL);
  seeds = new CellArray2D( CellerySeeds.randomOnes(density, lifeWidth, lifeHeight), topo );
  life = new Rule(new int[] {3}, new int[] {2,3});
  ca = new BinaryAutomaton2D(seeds, life);
  
  noFill();
  stroke(40);
  smooth(8);
}

void draw() { 
  int t = ca.step();  
  int jj = 0;
  int ii = 0;
  background(0);
  translate(width/2, height/2);
  rotateX(-PI/5);
  rotateY(theta);
  
  for ( int j = -cylHeight/2; j < cylHeight/2; j+=cellSize ){
    for ( int i = -cylCirc/2; i < cylCirc/2; i+=cellSize ){

          ca.getCell(jj, ii).setAliveColor(
            (int) (255 * sin(PI / 1028 * t)),
            (int) (200 * cos(PI / 1028 * t)),
            (int) (185 + j*sin(PI / 512 * t))
                                             );
        stroke(ca.getCell(jj,ii).getAliveColor());                               
        if(ca.getCell(jj,ii).isAlive()){
          fill(ca.getCell(jj,ii).getAliveColor());
        } else {
          fill(0);
        }
      
      //draw a cylinder from many squares  
      beginShape(QUADS);
      float r = cylCirc/TWO_PI;
      float angle = 1/r;
      
      int iNext = i + cellSize;
      int jNext = j + cellSize;
       
      //first corner
      float x1 = r*cos(angle*i);
      float y1 = r*sin(angle*i);
      float z1 = j;
      
      //second corner
      float x2 = r*cos(angle*iNext);
      float y2 = r*sin(angle*iNext);
      float z2 = j;
      
      //third corner
      float x3 = r*cos(angle*iNext);
      float y3 = r*sin(angle*iNext);
      float z3 = jNext;
      
      //fourth corner
      float x4 = r*cos(angle*i);
      float y4 = r*sin(angle*i);
      float z4 = jNext;
      
      //plug in the corners
      vertex( x1, y1, z1);
      vertex( x2 , y2 , z2);
      vertex( x3, y3, z3);
      vertex( x4, y4, z4);
      
      endShape(); 
      ii++;
    }
    ii=0;
    jj++;

  }
  
  if (maybeIterate()){
    ca.iterate();
    lastRunTime = millis();
  }
  
  theta += 0.01;
}

public boolean maybeIterate() {
  return abs(millis() - lastRunTime) > timeInterval;

}
