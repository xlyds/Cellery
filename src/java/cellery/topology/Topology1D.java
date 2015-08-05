package cellery.topology;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * An object that specifies how a {@link cellery.CellArray1D} should retrieve neighborhoods. This object specifies the
 * size and geometry of neighborhoods in the client CellArray2D and specifies an identity space over the client.
 * @see {@link Base}, {@link Space}, {@link cellery.CellArray1D}, {@link cellery.Automaton1D},
 * @author Zach Tidwell
 */

public class Topology1D {
	
	/**
	 * An enumeration of the supported neighborhood geometries: 
	 * {@link Topology1D.Base#RADIAL radial},
	 * {@link Topology1D.Base#LEFT left},
	 * {@link Topology1D.Base#RIGHT right},
	 * Each item has a field containing the appropriate method name in {@link cellery.CellArray1D} so that a
	 * {@link Topology1D} object can specify which method the target CellArray1D should use for
	 * neighborhood lookup.
	 * @see <a href="http://en.wikibooks.org/wiki/Cellular_Automata/Neighborhood">Neighborhoods</a>
	 * @author Zach Tidwell
	 *
	 */
	public static enum Base {
		
		/**
		 * A symmetrical region about a {@link cellery.Cell}
		 */
		RADIAL ("radial"),
		
		/**
		 * The region contiguous region to the left of a {@link cellery.Cell}
		 */
		LEFT ("leftOf"),
		
		/**
		 * The contiguous region to the right of a {@link cellery.Cell}
		 */
		RIGHT ("rightOf");
		
		private String methodName;
		
		private Base(String methodName){
			this.methodName = methodName;
		}
		
		/**
		 * Gets the method name that this
		 * @return name of method
		 */
		public String getMethodName(){
			return this.methodName;
		}
	}
	/**
	 * An enumeration of the supported identity spaces: {@link Topology1D.Space#STD Standard},
	 * {@link Topology1D.Space#CIRC Circle}
	 * Each item has a field containing the name of the inner class in {@link Topology1D} which contains the 
	 * appropriate identity functions for that identity space. This name can be retrieved with the 
	 * {@link Topology1D.Space#getTau getTau()} method.
	 * @author Zach Tidwell
	 */
	public static enum Space {
		
		/**
		 * The standard topology on a subset of R^1.
		 */
		STD ("Standard"),
		
		/**
		 * Circular quotient space topology on a rectangular subset of R^1.
		 */
		CIRC ("Circle");
		
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
	 * The basis of this Topology. In other words, the definition of a neighborhood about a given {@link cellery.Cell}.
	 */
	private final Base basis;
	
	/**
	 * The type of identity space of this topology.
	 */
	private final Class<?> space;
	
	/**
	 * Represents the granularity of this topology, or in other words, the radius of the neighborhood about a given {@link 
	 * cellery.Cell}. 1 is this finest topology and the topology becomes coarser for each successive int value. A topology can have
	 * arbitrary coarseness.
	 */
	public int coarseness;
	
	/**
	 * Identity function that maps boundary points as specified by this topology.
	 */
	private Method idI;
	
	/**
	 * Creates a Topology1D object that specifies how a {@link cellery.CellArray1D} should retrieve neighborhoods. The given Basis specifies the
	 * geometry of the neighborhood. The supported neighborhood geometries are enumerated in {@link Topology1D.Base}. Moreover,
	 * the size of this neighborhood is controlled by the coarseness parameter. Coarseness can be any int value, but if coarseness is 
	 * negative, the absolute value will be used. The space parameter specifies which identity functions this {@link Topology1D}
	 * should provide to the target CellArray1D. The Types of identity spaces supported are enumerated in {@link Topology1D.Space}.
	 * @param basis The {@link Topology1D.Base neighborhood} geometry selected for this topology
	 * @param coarseness sets the size of the neighborhoods about a given {@link cellery.Cell}.
	 * @param space The {@link Topology1D.Space identification space} associated with this Topology.
	 */
	public Topology1D(Base basis, int coarseness, Space space){
		this.basis = basis;
		this.coarseness = Math.abs(coarseness);
		
		switch(space.getTau()){
		
		case "Circle":
			this.space = Topology1D.Circle.class;
			break;
		default:
			this.space = Topology1D.Standard.class;
			break;
		}
		
		Class<?>[] args0 = {int.class, int.class};

		try {
			this.idI = this.space.getMethod("idI", args0);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The identity function which maps the row index "i" of the target {@link cellery.CellArray2D} as specified by this {@link Topology2D}.
	 * @param x a point in the identification space
	 * @param length the number of elements in this space
	 * @return x identified with another point
	 */
	public int idI(int x, int length){
		try {
			return (int) this.idI.invoke(null, x, length);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { 
			return 0;
		}
	}
	
	/**
	 * Returns the topology inner class that this {@link Topology1D} is using.
	 * @return {@link Space} the inner class that belongs to this.
	 */
	public Class<?> getSpace(){
		return this.space;
	}
	
	/**
	 * Returns the basis of this {@link Topology1D}.
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
	* Provides Identity functions to map i coordinate of the client {@link cellery.CellArray1D} according to this specification.
	* @author Zach Tidwell
	* @see {@link Topology1D.Space Spaces}, {@link cellery.CellArray1D CellArray1D}
	*/
	public final static class Standard {
	
		/**
		* The identify function that maps the i value of the client {@link cellery.CellArray1D} to the {@link Standard} identification space.
		* @see {@link Space}
		* @param i The row index of the CellArray1D.
		* @return the mapped i value
		*/
		public static int idI(int i, int length){
			return i;
		}
		
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	* The Standard topology inner class for subsets of R^1 where all boundary points are identified with themselves.
	* Provides Identity functions to map i coordinates of the client {@link cellery.CellArray1D} according to this specification.
	* @author Zach Tidwell
	* @see {@link Topology1D.Space Spaces}, {@link cellery.CellArray1D CellArray1D}
	*/
	public final static class Circle {
		/**
		 * The identify function that maps the j value of the client {@link cellery.CellArray1D} to the {@link Circle} identification space.
		 * @see {@link Space}
		 * @param i The row index of the CellArray1D.
		 * @param length of the CellArray1D.
		 * @return the mapped i value
		 */
		public static int idI(int i, int length){
			if ( i < 0 ){
				return idI(length+i, length);
			}
			else{
			    return i%length;
			}
		}
	}
	

}
