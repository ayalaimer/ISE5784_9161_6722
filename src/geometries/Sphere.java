package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * A class that represents a sphere in space, with the center and the radius of the sphere.
 * sphere is a part of Radial Geometry, so this class extends from "RadialGeometry".
 */
public class Sphere extends RadialGeometry {

    final Point center;

    /**
     * create a new Sphere object with the specified point and radius
     *
     * @param radius the length of the radius of the sphere
     * @param center the point that represent the center of the sphere.
     *               or in other words, the point that has the same distance (radius) to all points on the sphere.
     */
    public Sphere(double radius, Point center) {
        super(radius); //call to the father constructor with the radius
        this.center = center;
    }

    /**
     * @return the point that represent the center of the sphere.
     */
    public Point getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

        // Get the start point and direction of the ray
        Point p0 = ray.getHead();
        Vector dir = ray.getDirection();
        // Get the radius of the sphere
        double r = this.radius;

        // Calculate vector from the ray's start point to the center of the sphere
        Vector u;

        try {
            u = this.center.subtract(p0);
        } catch (IllegalArgumentException e) {
            // If the ray starts at the center of the sphere, return a single intersection point
            return List.of(new GeoPoint(this, p0.add(dir.scale(r))));
        }

        // Calculate necessary values for intersection calculation
        double tm = u.dotProduct(dir);
        double ul = u.length();
        double d = Math.sqrt(ul * ul - tm * tm);

        // Check if there are no intersections
        if (d >= r) {
            return null;
        }

        // Calculate the intersection points
        double th = Math.sqrt(r * r - d * d);
        double t1 = tm + th;
        double t2 = tm - th;
        if (t1 > 0 && t2 > 0) {
            return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
        } else if (t1 > 0) {
            return List.of(new GeoPoint(this,ray.getPoint(t1)));
        } else if (t2 > 0) {
            return List.of(new GeoPoint(this,ray.getPoint(t2)));
        } else {
            return null;
        }
    }
}
