package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

/**
 * The Plane class represents a plane in three-dimensional space.
 * It implements the Geometry interface and is defined by a point on the plane and a normal vector.
 */
public class  Plane implements Geometry {

    /**
     * A point on the plane.
     */
    private final Point myPoint;

    /**
     * The normal vector of the plane.
     */
    private final Vector myNormal;

    /**
     * Constructs a new Plane defined by three points.
     * The normal vector is calculated based on the cross product of vectors formed by these points.
     *
     * @param p1 the first point on the plane
     * @param p2 the second point on the plane
     * @param p3 the third point on the plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.myPoint = p1;
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        this.myNormal = v1.crossProduct(v2).normalize();
    }

    /**
     * Constructs a new Plane with a point on the plane and a normal vector.
     *
     * @param p the point on the plane
     * @param v the normal vector of the plane
     */
    public Plane(Point p, Vector v) {
        myPoint = p;
        myNormal = v.normalize();
    }

    /**
     * Returns the normal vector of the plane.
     *
     * @return the normal vector of the plane
     */
    public Vector getNormal() {
        return myNormal;
    }

    /**
     * Returns the normal vector of the plane at a given point.
     * Since the normal is the same everywhere on the plane, the input point is ignored.
     *
     * @param p the point at which the normal is requested
     * @return the normal vector of the plane at the specified point
     */
    public Vector getNormal(Point p) {
        return myNormal;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return  null;
    }
}
