package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Plane class represents a plane in three-dimensional space.
 * It implements the Geometry interface and is defined by a point on the plane and a normal vector.
 */
public class Plane implements Geometry {

    /**
     * A point on the plane.
     */
    private final Point myPoint;

    /**
     * The normal vector of the plane.
     */
    private final Vector myNormal;

    /**
     * Constructs a new Plane with three points in the plane. The normal vector is not calculated in this constructor.
     *
     * @param x the first point on the plane
     * @param y the second point on the plane
     * @param z the third point on the plane
     */
    public Plane(Point x, Point y, Point z) {
       myPoint = x;
       try{
           myNormal = x.subtract(y).crossProduct(x.subtract(z)).normalize();
       }catch(IllegalArgumentException e) {
           throw new IllegalArgumentException("the points is on same vector");
       }
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
     *
     * @param p the point at which the normal is requested
     * @return the normal vector of the plane at the specified point
     */
    public Vector getNormal(Point p) {
        return myNormal;
    }
}

