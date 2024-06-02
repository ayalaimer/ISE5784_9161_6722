package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * The Triangle class represents a triangle in three-dimensional space.
 * It extends the Polygon class and is defined by three vertices.
 */
public class Triangle extends Polygon {

    /**
     * Constructs a new Triangle with the specified vertices.
     *
     * @param p1 the first vertex of the triangle
     * @param p2 the second vertex of the triangle
     * @param p3 the third vertex of the triangle
     * @throws IllegalArgumentException if the number of vertices is not equal to 3
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        // Find intersections with the plane of the triangle
        List<Point> planeIntersections = plane.findIntersections(ray);
        if (planeIntersections == null) {
            return null;
        }

        Point p0 = ray.getHead();
        Vector v = ray.getDirection();

        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);

        Vector v1 = p1.subtract(p0);
        Vector v2 = p2.subtract(p0);
        Vector v3 = p3.subtract(p0);

        // Calculate the normal vectors for each edge of the triangle
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        // Calculate the dot products
        double d1 = v.dotProduct(n1);
        double d2 = v.dotProduct(n2);
        double d3 = v.dotProduct(n3);

        // If all dot products have the same sign, the point is inside the triangle
        if ((d1 > 0 && d2 > 0 && d3 > 0) || (d1 < 0 && d2 < 0 && d3 < 0)) {
            return planeIntersections;
        }

        // Otherwise, the ray does not intersect the triangle
        return null;
    }
}
