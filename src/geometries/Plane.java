package geometries;

import primitives.*;


import java.util.LinkedList;
import java.util.List;
import static primitives.Util.*;

/** Plane ia A class that implements the "Geometry" interface.
 * This class represents a plane by a one point on the plane and a vector
 * that orthogonal to the plane (the normal). */
public class Plane extends Geometry{

    final Point q0;
    final Vector normal;

    /**  Creates a Plane object with the specified point and vector.
     * @param q0 - point of reference on the plane
     * @param vector - the normal. vector that orthogonal to the plane. */
    public Plane(Point q0, Vector vector) {
        this.q0 = q0;
        this.normal = vector.normalize();
    }

    /** Creates a Plane object with the specified 3 points on the plane.
     * with the help of this 3 points we will find the normal vector
     * @param p1 - point on the plane
     * @param p2- point on the plane
     * @param p3- point on the plane */
    public  Plane(Point p1, Point p2, Point p3) {

        this.q0 = p1; //save one random point to be point of reference
        Vector u = p2.subtract(p1); // u = p2 - p1
        Vector v = p3.subtract(p1); // v = p3 - p1
        Vector n = u.crossProduct(v);

        this.normal = n.normalize(); // n = normalize(v1 * v2)
    }
    /** function to get the point
     * @return the plane's point of reference. */
    public Point getQ0() {
        return q0;
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    /** function to get normal without specified point.
     * because in a plane the normal at all points is the same.
     * @return call to the other func to get normal, with one point from our plane
     * (p0 in our case is very comfortable for us). */
    public Vector getNormal() {
        return getNormal(q0);
    }

    @Override
    public List<Point> findIntersections(Ray ray){

        // Check if the Ray starts on the Plane, if so return null.
        if (q0.equals(ray.getHead())) {
            return null;
        }

        // Calculate the vector between q0 and the Ray's starting point.
        Vector qMinusP0 = q0.subtract(ray.getHead());
        // Calculate the dot product between the Plane's normal and qMinusP0.
        double nQMinusP0 = normal.dotProduct(qMinusP0);
        // Calculate the dot product between the Plane's normal and the Ray's direction vector.
        double nv = normal.dotProduct(ray.getDirection());

        // If the dot product between the Plane's normal and the Ray's direction vector is close to zero, return null.
        if (isZero(nv)) {
            return null;
        }

        // Calculate the intersection parameter t.
        double t = alignZero(nQMinusP0 / nv);

        // If t is greater than 0, create a new list containing the intersection point and return it.
        if (t > 0d) {
            List<Point> list = List.of(ray.getPoint(t));
            return list;
        }

        // Otherwise, return null.
        return null;
    }
}
