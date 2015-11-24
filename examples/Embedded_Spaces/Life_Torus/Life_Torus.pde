import cellery.rules.*;
import cellery.*;
import cellery.topology.*;

float theta;

//Circumference of the inner-circle coplanar with the center point of the torus
int torMajor;
//Circumference of the tube
int torMinor;

//Dimensions of the grid 
int lifeWidth;
int lifeHeight;
int cellSize;
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
  size(1200, 800, P3D);
  
  //assign mobius band geometry values
  torMajor = 800;
  torMinor = 700;
  
  //Assign grid geometry values
  cellSize = 10;
  lifeWidth = torMajor/cellSize;
  lifeHeight = torMinor/cellSize;
  density = 80;

  //Assemble CA ingredients
  topo = new Topology2D(Topology2D.Base.MOORE, 1, Topology2D.Space.TOR);
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
  rotateX(theta);
  rotateZ(theta*PI/4);

  for ( int i = -torMinor/2; i < torMinor/2; i+=cellSize ){
    for ( int j = -torMajor/2; j < torMajor/2; j+=cellSize ){

        ca.getCell(ii, jj).setAliveColor(150,200,200);
        stroke(ca.getCell(ii,jj).getAliveColor());                               
        if(ca.getCell(ii,jj).isAlive()){
          fill(ca.getCell(ii,jj).getAliveColor());
        } else {
          fill(0);
        }
        
      //Draw the mobius bands from a bunch of tiny squares
      beginShape(QUAD);
      float r = torMinor / TWO_PI ;
      float R = torMajor/ TWO_PI ;
      float angle = 1/r;
      float phi = 1/R;
      
      int iNext = i + cellSize;
      int jNext = j + cellSize;
      
      //first corner
      float x1 = (R + r  + r*cos(i * angle) )*cos(j * phi);
      float y1 = (R + r + r*cos(i * angle) )*sin(j * phi);
      float z1 = r*sin(i*angle);
      
      //second corner
      float x2 = (R + r + r*cos( iNext * angle) )*cos( j * phi);
      float y2 = (R + r + r*cos( iNext * angle) )*sin( j * phi);
      float z2 = r*sin( iNext*angle);
      
      //thrid corner
      float x3 = (R + r + r*cos( iNext * angle ) )*cos( jNext * phi);
      float y3 = (R + r + r*cos( iNext * angle ) )*sin( jNext * phi);
      float z3 = r*sin(iNext * angle);
      
      //fourth corner
      float x4 = (R + r + r*cos( i * angle ) )*cos( jNext * phi );
      float y4 = (R + r + r*cos( i * angle ) )*sin( jNext * phi );
      float z4 = r*sin(i * angle);
      
      //plug in the corners
      vertex( x1, y1, z1);
      vertex( x2, y2, z2);
      vertex( x3, y3, z3);
      vertex( x4, y4, z4);
      endShape(); 
      jj++;
      
    }
    jj=0;
    ii++;

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
