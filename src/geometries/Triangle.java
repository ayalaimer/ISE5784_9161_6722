package geometries;


import primitives.*;

import java.util.List;

import static primitives.Util.*;

public class Triangle extends Polygon {

    /**
     * constructor for triangle. call to the father constructor because triangle is a type of polygon.
     *
     * @param p1 vertex of the triangle
     * @param p2 vertex of the triangle
     * @param p3 vertex of the triangle
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> pointList = plane.findIntersections(ray);

        // Check if the ray intersects the plane of the triangle
        if (pointList == null) {
            return null;
        }

        // Calculate the normal vectors of the triangle's edges
        Vector v1 = vertices.get(0).subtract(ray.getHead());
        Vector v2 = vertices.get(1).subtract(ray.getHead());
        Vector v3 = vertices.get(2).subtract(ray.getHead());

        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        // Calculate the dot products between the ray's direction vector and the normal vectors of the edges
        double vn1 = alignZero(ray.getDirection().dotProduct(n1));
        double vn2 = alignZero(ray.getDirection().dotProduct(n2));
        double vn3 = alignZero(ray.getDirection().dotProduct(n3));

        // Check if the ray intersects the triangle
        if ((vn1 > 0 && vn2 > 0 && vn3 > 0) || (vn1 < 0 && vn2 < 0 && vn3 < 0))
            return pointList;
        else
            return null;
    }
}
