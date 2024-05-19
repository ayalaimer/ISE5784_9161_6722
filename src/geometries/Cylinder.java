package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * The Cylinder class represents a cylinder in three-dimensional space.
 * It extends the Tube class and includes a height parameter.
 */
public class Cylinder extends Tube {

    /**
     * The height of the cylinder.
     */
    private final double height;

    /**
     * Constructs a new Cylinder with the specified height, axis, and radius.
     *
     * @param myHeight the height of the cylinder
     * @param myAxis the central axis of the cylinder
     * @param myRadius the radius of the cylinder
     */
    public Cylinder(double myHeight, Ray myAxis, double myRadius) {
        super(myAxis, myRadius);
        this.height = myHeight;
    }

    /**
     * Computes the normal vector to the cylinder at a given point.
     *
     * @param p the point on the surface of the cylinder
     * @return the normal vector to the cylinder at the specified point
     */
    public Vector getNormal(Point p) {
        // This method needs to be implemented
        return null;
    }
}

