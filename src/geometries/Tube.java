package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

/**
 * The Tube class represents a three-dimensional tube geometry in space.
 * It extends the RadialGeometry class and includes a central axis represented by a Ray.
 */
public class Tube extends RadialGeometry {

    /**
     * The central axis of the tube.
     */
    protected final Ray axis;

    /**
     * Constructs a new Tube with the specified axis and radius.
     *
     * @param myAxis the central axis of the tube
     * @param myRadius the radius of the tube
     */
    public Tube(Ray myAxis, double myRadius) {
        super(myRadius);
        this.axis = myAxis;
    }

    /**
     * Computes the normal vector to the tube at a given point.
     *
     * @param p the point on the surface of the tube
     * @return the normal vector to the tube at the specified point
     */
    public Vector getNormal(Point p) {
        return null; // This method needs to be implemented
    }
}
