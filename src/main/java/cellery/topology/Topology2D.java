package cellery.topology;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * An object that specifies how a {@link cellery.CellArray2D} should retrieve neighborhoods. This object specifies the
 * size and geometry of neighborhoods in the client CellArray2D and specifies an identity space over the client.
 * @see {@link Base}, {@link Space}, {@link cellery.CellArray2D}, {@link Automaton2D}
 * @author Zach Tidwell
 */
public class Topology2D{

	/**
	 * An enumeration of the supported neighborhood geometries: 
	 * {@link Topology2D.Base#MOORE Moore},
	 * {@link Topology2D.Base#VON_NEUMANN Von Neumann},
	 * {@link Topology2D.Base#LEFT_CORNER Left Corners},
	 * {@link Topology2D.Base#RIGHT_CORNER Right Corners},
	 * {@link Topology2D.Base#HORIZONTAL_2D Horizontal},
	 * {@link Topology2D.Base#VERTICAL_2D Vertical},
	 * {@link Topology2D.Base#LEFT_DIAGONAL Left Diagonal},
	 * {@link Topology2D.Base#RIGHT_DIAGONAL Right Diagonal}.
	 * Each item has a field containing the appropriate method name in {@link cellery.CellArray2D} so that a
	 * {@link Topology2D} object can specify which method the target CellArray2D should use for
	 * neighborhood lookup.
	 * @see <a href="http://en.wikibooks.org/wiki/Cellular_Automata/Neighborhood">Neighborhoods</a>
	 * @author Zach Tidwell
	 *
	 */
	public static enum Base {
		/**
		 * A square shaped neighborhood about a {@link Cell}.
		 * @see <a href="http://en.wikipedia.org/wiki/Moore_neighborhood">Moore neighborhood wiki</a>
		 */
		MOORE  ("moore"),
		
		/**
		 * A diamond shaped neighborhood about a {@link Cell}
		 * @see <a href="http://en.wikipedia.org/wiki/Von_Neumann_neighborhood">Von Neumann neighborhood wiki</a>
		 */
		VON_NEUMANN ("vonNeumann"),
		
		/**
		 * The region about a {@link Cell} that includes the orthogonal Cells and the diagonal Cells from the
		 * northwest to the southeast.
		 */
		LEFT_CORNER ("leftCorners"),
		
		/**
		 * The region about a {@link Cell} that includes the orthogonal Cells and the diagonal Cells from the
		 * north-east to the south-west.
		 */
		RIGHT_CORNER ("rightCorners"),
		
		/**
		 * The region about a {@link Cell} that includes all the Cells that are horizontal and orthogonal.
		 */
		HORIZONTAL_2D ("getHorizontal"),
		
		/**
		 * The region about a {@link Cell} that includes all the Cells that are vertical and orthogonal.
		 */
		VERTICAL_2D ("getVertical"),
		
		/**
		 * The region about a {@link Cell} that includes all the diagonal Cells from the northwest to the southeast.
		 */
		LEFT_DIAGONAL ("getLeftDiag"),
		
		/**
		 * The region about a {@link Cell} that includes all the diagonal Cells from the northeast to the southwest.
		 */
		RIGHT_DIAGONAL ("getRightDiag");	
		
		private String methodName;

		private Base(String methodName){
			this.methodName = methodName;
		}
		
		/**
		 * Gets the method name that this
		 * @return method name
		 */
		public String getMethodName(){
			return this.methodName;
		}
	}
	
	/**
	 * An enumeration of the supported identity spaces: {@link Topology2D.Space#STD Standard},
	 * {@link Topology2D.Space#CYL Cylinder}, {@link Topology2D.Space#MOEB Moebius Band}.
	 * Each item has a field containing the name of the inner class in {@link Topology2D} which contains the 
	 * appropriate identity functions for that identity space. This name can be retrieved with the 
	 * {@link Topology2D.Space#getTau getTau()} method.
	 * @author Zach Tidwell
	 */
	public static enum Space {
		
		/**
		 * The standard topology on a subset of R^2.
		 */
		STD ("Standard"),
		
		/**
		 * Cylindrical quotient space topology on a rectangular subset of R^2.
		 */
		CYL ("Cylinder"),
		
		/**
		 * Toroidal quotient space topology on a rectangular subset of R^2.
		 */
		TOR ("Torus"),
		
		/**
		 * The Quotient space generating a Moebius band on a rectangular subset of R^2.
		 */
		MOEB ("Moebius"),
		
		/**
		 * The quotient space that generates a Klein bottle on a rectangular subset of R^2.
		 */
		KLN ("Klein");
		
		private String tau;
		private Space(String tau){
			this.tau = tau;
		}
		
		/**
		 * Gets the Class name containing the appropriate identity functions for this Space.
		 * @return String the class name.
		 */
		public String getTau(){
			return this.tau;
		}
	}
	/**
	 * The basis of this Topology. In other words, the definition of a neighborhood about a given {@link Cell}.
	 */
	private final Base basis;
	
	/**
	 * Represents the granularity of this topology, or in other words, the radius of the neighborhood about a given {@link 
	 * Cell}. 1 is this finest topology and the topology becomes coarser for each successive int value. A topology can have
	 * arbitrary coarseness.
	 */
	public final int coarseness;
	
	/**
	 * The type of identity space of this topology.
	 */
	private final Class<?> space;

	/**
	 * Identity function that maps boundary points as specified by this topology.
	 */
	private Method idI;
	
	/**
	 * Identity function that maps boundary points as specified by this topology.
	 */
	private Method idJ;

	/**
	 * Creates a Topology2D object that specifies how a {@link cellery.CellArray2D} should retrieve neighborhoods. The given Basis specifies the
	 * geometry of the neighborhood. The supported neighborhood geometries are enumerated in {@link Topology2D.Base}. Moreover, 
	 * the size of this neighborhood is controlled by the coarseness parameter. Coarseness can be any int value, but if coarseness is 
	 * negative, the absolute value will be used. The space parameter specifies which identity functions this {@link Topology2D}
	 * should provide to the target CellArray2D. The Types of identity spaces supported are enumerated in {@link Topology2D.Space}.
	 * @param basis The {@link Topology2D.Base neighborhood} geometry selected for this topology
	 * @param coarseness sets the size of the neighborhoods about a given {@link Cell}.
	 * @param space The {@link Topology2D.Space identification space} associated with this Topology.
	 */
	public Topology2D(Base basis, int coarseness, Space space){
		this.basis = basis;
		this.coarseness = Math.abs(coarseness);
		
		switch(space.getTau()){
		
		case "Cylinder":
			this.space = Topology2D.Cylinder.class;
			break;
		case "Torus":
			this.space = Topology2D.Torus.class;
			break;
		case "Moebius":
			this.space = Topology2D.Moebius.class;
			break;
		case "Klein":
			this.space = Topology2D.Klein.class;
			break;
		default:
			this.space = Topology2D.Standard.class;
			break;
			
		}
		
		Class<?>[] args0 = {int.class, int.class, int.class, int.class};

		try {
			this.idI = this.space.getMethod("idI", args0);
			this.idJ = this.space.getMethod("idJ", args0);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * The identity function which maps the row index "i" of the target {@link cellery.CellArray2D} as specified by this {@link Topology2D}.
	 * @param x
	 * @param y
	 * @param length
	 * @param width
	 * @return
	 */
	public int idI(int x, int y, int length, int width){
		try {
			return (int) this.idI.invoke(null, x, y, length, width);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { 
			return 0;
		}
	}
	
	/**
	 * Invokes the identity function from the {@link Topology2D} inner class specified by {@link Space} for column coordinates of the
	 * target {@link cellery.CellArray2D}.
	 * @param x
	 * @param y
	 * @param length
	 * @param width
	 * @return
	 */
	public int idJ(int x, int y, int length, int width){
		try {
			return (int) this.idJ.invoke(null, x, y, length, width);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return 0;
		}
	}
	
	/**
	 * Returns the topology inner class that this {@link Topology2D} is using.
	 * @return {@link Space} the inner class that belongs to this.
	 */
	public Class<?> getSpace(){
		return this.space;
	}
	
	/**
	 * Returns the basis of this {@link Topology2D}.
	 * @return {@link Base} the basis of this topology
	 */
	public Base getBasis(){
		return this.basis;
	}
	
	//////////////////////////////////////
	/////** TOPOLOGY INNER-CLASSES **/////
	//////////////////////////////////////
	
	/**
	 * The Standard topology inner class for subsets of R^2 where all boundary points are identified with themselves.
	 * Provides Identity functions to map (i, j) coordinates of the client {@link cellery.CellArray2D} according to this specification.
	 * @author Zach Tidwell
	 * @see {@link Topology2D.Space Spaces}, {@link cellery.CellArray2D CellArray2D}
	 */
	public final static class Standard {
		
		/**
		 * The identify function that maps the i value of the client {@link cellery.CellArray2D} to the {@link Standard} identification space.
		 * @see {@link Space}
		 * @param i The row index of the CellArray2D.
		 * @param j The column index of the CellArray2D.
		 * @param length of the CellArray2D.
		 * @param width of the CellArray2D
		 * @return the mapped i value
		 */
		public static int idI(int i, int j, int length, int width){
			return i;
		}
		
		/**
		 * The identify function that maps the j value of the client {@link cellery.CellArray2D} to the {@link Cylinder} identification space.
		 * @see {@link Space}
		 * @param i The row index of the CellArray2D.
		 * @param j The column index of the CellArray2D.
		 * @param length of the CellArray2D.
		 * @param width of the CellArray2D
		 * @return the mapped j value
		 */
		public static int idJ(int i, int j, int length, int width){
			return j;
		}
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * A topology inner class for subsets of R^2 that directly identifies the left and right boundaries to each other and all points on 
	 * the top and bottom boundaries are identified with themselves. Provides Identity functions to map (i, j) coordinates of the client
	 * {@link cellery.CellArray2D} according to this specification.
	 * @author Zach Tidwell
	 * @see {@link Topology2D.Space Spaces}, {@link cellery.CellArray2D CellArray2D}
	 *
	 */
	public final static class Cylinder {
		
		/**
		 * The identify function that maps the i value of the client {@link cellery.CellArray2D} to the {@link Cylinder} identification space.
		 * @see {@link Space}
		 * @param i The row index of the CellArray2D.
		 * @param j The column index of the CellArray2D.
		 * @param length of the CellArray2D.
		 * @param width of the CellArray2D
		 * @return the mapped i value
		 */
		public static int idI(int i, int j, int length, int width){
			return i;
		}
		
		/**
		 * The identify function that maps the j value of the client {@link cellery.CellArray2D} to the {@link Cylinder} identification space.
		 * @see {@link Space}
		 * @param i The row index of the CellArray2D.
		 * @param j The column index of the CellArray2D.
		 * @param length of the CellArray2D.
		 * @param width of the CellArray2D
		 * @return the mapped j value
		 */
		public static int idJ(int i, int j, int length, int width){
			if ( j < 0 ){
				return idJ(i, width+j, length, width);
			}
			else{
			    return j%width;
			}
		}
		
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * A topology inner class for subsets of R^2 that directly identifies the left and right boundaries to each other and 
	 * also directly identifies the top and bottom boundaries to each other. Provides Identity functions to map (i, j) coordinates
	 * of the client {@link cellery.CellArray2D} according to this specification.
	 * @author Zach Tidwell
	 * @see {@link Topology2D.Space Spaces}, {@link cellery.CellArray2D CellArray2D}
	 */
	public final static class Torus {
		
		/**
		 * The identify function that maps the i value of the client {@link cellery.CellArray2D} to the {@link Torus} identification space.
		 * @see {@link Space}
		 * @param i The row index of the CellArray2D.
		 * @param j The column index of the CellArray2D.
		 * @param length of the CellArray2D.
		 * @param width of the CellArray2D
		 * @return the mapped i value
		 */
		public static int idI(int i, int j, int length, int width){
			if ( i < 0 ){
				return idI(length + i, j, length, width);
			}
			else{
			    return i%length;
			}
		}
		/**
		 * The identify function that maps the j value of the client {@link cellery.CellArray2D} to the {@link Torus} identification space.
		 * @see {@link Space}
		 * @param i The row index of the CellArray2D.
		 * @param j The column index of the CellArray2D.
		 * @param length of the CellArray2D.
		 * @param width of the CellArray2D
		 * @return the mapped j value
		 */
		public static int idJ(int i, int j, int length, int width){
				if ( j < 0 ){
					return idJ(i, width + j, length, width);
				}
				else{
				    return j%width;
				}
			}
		
		}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * A topology inner class for subsets of R^2 that identifies the left and right hand boundaries to each other with a 180 degree twist 
	 * and all points on the top and bottom boundaries are identified with themselves. Provides Identity functions to map (i, j) coordinates
	 * of the client {@link cellery.CellArray2D} according to this specification.
	 * @author Zach Tidwell
	 * @see {@link Topology2D.Space Spaces}, {@link cellery.CellArray2D CellArray2D}
	 */
	public final static class Moebius {
		
		/**
		 * The identify function that maps the i value of the client {@link cellery.CellArray2D} to the {@link Moebius} identification space.
		 * @see {@link Space}
		 * @param i The row index of the CellArray2D.
		 * @param j The column index of the CellArray2D.
		 * @param length of the CellArray2D.
		 * @param width of the CellArray2D
		 * @return the mapped i value
		 */
		public static int idI(int i, int j, int length, int width ){
			if(j >= width || j < 0 )
				return length - i - 1;
			else
				return i;
		}
		
		/**
		 * The identify function that maps the j value of the client {@link cellery.CellArray2D} to the {@link Moebius} identification space.
		 * @see {@link Space}
		 * @param i The row index of the CellArray2D.
		 * @param j The column index of the CellArray2D.
		 * @param length of the CellArray2D.
		 * @param width of the CellArray2D
		 * @return the mapped j value
		 */
		public static int idJ(int i, int j, int length, int width){
			if (j >= 0) {
				return j % width;
			} else {
				return idJ( i, width + j, length, width);
			}
		}
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * A topology inner class for subsets of R^2 that identifies the left and right hand boundaries to each other with a 180 degree twist and
	 * directly identifies the top and bottom boundaries to each other. Provides identity functions to map (i, j) coordinates of the client
	 * {@link cellery.CellArray2D} according to this specification.
	 * @author Zach Tidwell
	 * @see {@link Topology2D.Space Spaces}, {@link cellery.CellArray2D CellArray2D}
	 */
	public final static class Klein {
		
		/**
		 * The identify function that maps the i value of the client {@link cellery.CellArray2D} to the {@link Klein} identification space.
		 * @see {@link Space}
		 * @param i The row index of the CellArray2D.
		 * @param j The column index of the CellArray2D.
		 * @param length of the CellArray2D.
		 * @param width of the CellArray2D
		 * @return the mapped i value
		 */
		public static int idI(int i, int j, int length, int width){
			if(j >= width || j < 0 )
				return length - i - 1;
			else 
			if ( i < 0 ){
				return idI(length + i, j, length, width);
			}
			
			else{
			    return i%length;
			}
		}
		
		/**
		 * The identify function that maps the j value of the client {@link cellery.CellArray2D} to the {@link Klein} identification space.
		 * @see {@link Space}
		 * @param i The row index of the CellArray2D.
		 * @param j The column index of the CellArray2D.
		 * @param length of the CellArray2D.
		 * @param width of the CellArray2D
		 * @return the mapped j value
		 */
		public static int idJ(int i, int j, int length, int width){
			if (j >= 0) {
				return j % width;
			} else {
				return idJ( i, width + j, length, width);
			}
		}
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public String toString() {
		String theString = "(";
		theString += this.basis.name() + ", " + this.coarseness + ", " + this.space.toString();
		return theString;
	}
	
	
	
}
