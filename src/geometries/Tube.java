package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;

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

    public Vector getNormal(Point p) {
        /*
         * caculates the length of the projection of the vector from p to head
         */
        double t =(axis.getDirection()).dotProduct(p.subtract(axis.getHead()));
        /*
          if the degree between direction and p-head is not 90
         */
        if(t!=0) {
            Point o = (axis.getHead()).add((axis.getDirection()).scale(t));
            return (p.subtract(o)).normalize();
        }

        return (p.subtract(axis.getHead())).normalize();

}

    @Override
    public List<Point> findIntersectable(Ray ray) {
        return List.of();
    }
}
