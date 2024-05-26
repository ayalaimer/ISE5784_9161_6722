package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * The Sphere class represents a three-dimensional sphere geometry in space.
 * It extends the RadialGeometry class and includes a center point.
 */
public class Sphere extends RadialGeometry {

    /**
     * The center point of the sphere.
     */
    private final Point center;

    /**
     * Constructs a new Sphere with the specified center point and radius.
     *
     * @param myCenter the center point of the sphere
     * @param myRadius the radius of the sphere
     */
    public Sphere(Point myCenter, double myRadius) {
        super(myRadius);
        this.center = myCenter;
    }

    /**
     * Computes the normal vector to the sphere at a given point.
     * The normal vector is calculated as the direction vector from the center of the sphere to the given point,
     * normalized to have unit length.
     *
     * @param p a point on the surface of the sphere
     * @return the normal vector to the sphere at the specified point
     */
    public Vector getNormal(Point p) {
        // Calculate the vector from the center of the sphere to the given point
        Vector direction = p.subtract(center);
        // Normalize the direction vector to ensure it has unit length
        return direction.normalize();
    }

    @Override
    public List<Point> findIntersectable(Ray ray) {
        return List.of();
    }
}
