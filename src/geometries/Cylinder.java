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
     * @param point the point on the surface of the cylinder
     * @return the normal vector to the cylinder at the specified point
     */

    @Override
    public Vector getNormal(Point point) {
        Point p0 = axis.getHead();
        Vector v = axis.getDirection();

        //returns v because it is in the direction of the axis
        if (point.equals(p0))
            return v;

        //project point-p0 on the ray
        Vector u = point.subtract(p0);

        // distance from p0 to p1
        double t = u.dotProduct(v);

        //if the given point is at the base of the cylinder, return direction vector
        if (t == 0 || height - t==0)
            return v;

        //Calculates the other point on the axis facing the given point
        Point p1= p0.add(v.scale(t));

        //return the normalized vector
        return point.subtract(p1).normalize();
    }
}

