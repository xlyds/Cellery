import cellery.rules.*;
import cellery.*;
import cellery.topology.*;

float theta;

//Circumference of the mobius band
int mobCirc;
//Thickness of the mobius band
int mobHeight;

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
  mobCirc = 1600;
  mobHeight = 250;
  
  //Assign grid geometry values
  cellSize = 10;
  lifeWidth = mobCirc/cellSize;
  lifeHeight = mobHeight/cellSize;
  density = 80;

  //Assemble CA ingredients
  topo = new Topology2D(Topology2D.Base.MOORE, 1, Topology2D.Space.MOEB);
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

  for ( int j = -mobHeight/2; j < mobHeight/2; j+=cellSize ){
    for ( int i = -mobCirc/2; i < mobCirc/2; i+=cellSize ){

        ca.getCell(jj, ii).setAliveColor(
            (int) (150*abs(sin(PI / (mobCirc) * (i+j)))),
            (int) (200*abs(cos(PI / (mobCirc) * (t+j)))),
            (int) (150*abs(sin(PI / (mobCirc) * (i+t))))
                                             );
        stroke(ca.getCell(jj,ii).getAliveColor(), 100);                               
        if(ca.getCell(jj,ii).isAlive()){
          fill(ca.getCell(jj,ii).getAliveColor());
        } else {
          fill(0);
        }
        
      //Draw the mobius bands from a bunch of tiny squares
      beginShape(QUAD);
      float r = mobCirc/TWO_PI ;
      float angle = TWO_PI / mobCirc;
      
      int iNext = i + cellSize;
      int jNext = j + cellSize;
      //first corner
      float x1 = (r  + j*cos(i/2 * angle) )*cos(i * angle);
      float y1 = (r  + j*cos(i/2 * angle) )*sin(i * angle);
      float z1 = j*sin(i*angle/2);
      
      //second corner
      float x2 = (r  + j*cos( iNext/2 * angle) )*cos( iNext * angle);
      float y2 = (r + j*cos( iNext/2 * angle) )*sin( iNext * angle);
      float z2 = j*sin( iNext*angle/2 );
      
      //thrid corner
      float x3 = (r + jNext*cos( iNext/2 * angle ) )*cos( iNext * angle);
      float y3 = (r + jNext*cos( iNext/2 * angle ) )*sin( iNext * angle);
      float z3 = jNext*sin(iNext/2 * angle);
      
      //fourth corner
      float x4 = (r + jNext*cos( i/2 * angle ) )*cos( i * angle );
      float y4 = (r + jNext*cos( i/2 * angle ) )*sin( i * angle );
      float z4 = jNext*sin(i/2 * angle);
      
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
