package geometries;

import primitives.Point;

/**
 * The Triangle class represents a triangle in three-dimensional space.
 * It extends the Polygon class and is defined by three vertices.
 */
public class Triangle extends Polygon {

    /**
     * Constructs a new Triangle with the specified vertices.
     *
     * @param p1 p2 p3 the vertices of the triangle
     * @throws IllegalArgumentException if the number of vertices is not equal to 3
     */
    public Triangle(Point p1, Point p2,Point p3) {
        super(p1,p2,p3);
    }
}

