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

    public Vector getNormal(Point point) {
        Point p0 = this.axis.getHead(); // base center of the cylinder
        Vector dir =this.axis.getDirection(); // direction of the cylinder axis

        // Calculate the top center point of the cylinder
        Point p1 = p0.add(dir.scale(this.height));

        // Check if the point is on the top or bottom base
        if (point.equals(p0) || point.equals(p1)) {
            return dir;
        }

        Vector v1 = point.subtract(p0);
        double t = v1.dotProduct(dir);

        // If the point is on the bottom base
        if (t <= 0) {
            return dir.scale(-1);
        }

        // If the point is on the top base
        if (t >= this.height) {
            return dir;
        }

        // If the point is on the side surface
        Point o = p0.add(dir.scale(t)); // Projection of the point onto the axis
        return point.subtract(o).normalize();
    }
}

