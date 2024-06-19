package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * A class that represents a sphere in space, defined by its center and radius.
 * The sphere is a part of Radial Geometry, so this class extends from {@code RadialGeometry}.
 */
public class Sphere extends RadialGeometry {

    final Point center;

    /**
     * Constructs a new Sphere object with the specified radius and center point.
     *
     * @param radius the length of the radius of the sphere
     * @param center the point that represents the center of the sphere,
     *               i.e., the point that is equidistant (at the radius) from all points on the sphere.
     */
    public Sphere(double radius, Point center) {
        super(radius); // Call to the superclass constructor with the radius
        this.center = center;
    }

    /**
     * Returns the center point of the sphere.
     *
     * @return the point that represents the center of the sphere.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Calculates the normal vector to the sphere at a given point.
     *
     * @param point the point on the surface of the sphere at which the normal is to be calculated.
     * @return the normal vector to the sphere at the specified point.
     */
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    /**
     * Finds the intersection points between the sphere and a given ray.
     *
     * @param ray the ray for which intersections with the sphere are to be found.
     * @return a list of intersection points, or {@code null} if there are no intersections.
     */
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
            return List.of(new GeoPoint(this,p0.add(dir.scale(r))))
                    ;
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

        List<GeoPoint> intersections = new ArrayList<>();

        if (t1 > 0 && t2 > 0) {
            return List.of(ray.getPoint(t1), ray.getPoint(t2));
        } else if (t1 > 0) {
            return List.of(ray.getPoint(t1));
        } else if (t2 > 0) {
            return List.of(ray.getPoint(t2));
        } else {
            return null;
        }
    }
}
