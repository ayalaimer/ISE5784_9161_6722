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
     * @param myAxis   the central axis of the tube
     * @param myRadius the radius of the tube
     */
    public Tube(Ray myAxis, double myRadius) {
        super(myRadius);
        this.axis = myAxis;
    }

    /**
     * Computes the normal vector to the tube at a given point.
     * The normal vector is calculated by finding the closest point on the tube's axis to the given point,
     * and then computing the vector from that point to the given point.
     *
     * @param point a point on the surface of the tube
     * @return the normal vector to the tube at the specified point
     */
    public Vector getNormal(Point point) {
        // Get the head (starting point) of the axis
        Point p0 = this.axis.getHead();
        // Get the direction vector of the axis
        Vector dir = this.axis.getDirection();

        // Calculate the vector from the head of the axis to the given point
        Vector p0ToPoint = point.subtract(p0);
        // Calculate the parameter t by projecting the vector onto the axis
        double t = dir.dotProduct(p0ToPoint);
        // Calculate the closest point on the axis to the given point
        Point o = p0.add(dir.scale(t));

        // Calculate the normal vector by subtracting the closest point on the axis from the given point
        Vector normal = point.subtract(o);
        // Normalize the normal vector to ensure it has unit length
        return normal.normalize();
    }
}
