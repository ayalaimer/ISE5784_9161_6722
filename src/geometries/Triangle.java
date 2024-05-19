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
     * @param vertices the vertices of the triangle
     * @throws IllegalArgumentException if the number of vertices is not equal to 3
     */
    public Triangle(Point... vertices) {
        super(vertices);
        if (vertices.length != 3) {
            throw new IllegalArgumentException("A triangle must have exactly 3 vertices");
        }
    }
}

