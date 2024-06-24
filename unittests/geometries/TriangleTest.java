package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/** The class is a JUnit test class used to test the functionality of the Triangle class */
class TriangleTest {

    /** Test method for {@link geometries.Triangle#getNormal(primitives.Point)} */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        // Define three non-collinear points to create a triangle
        Point p1 = new Point(0, 0, 1);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(1, 0, 0);

        Triangle triangle = new Triangle(p1, p2, p3);

        // generate the test result
        Vector result = triangle.getNormal(p1);
        // ensure |result| = 1 , Verify that the length of the computed normal vector is equal to 1
        assertEquals(1, result.length(), 0.00000001, "Triangle's normal is not a unit vector");

        // ensure the result is orthogonal to all the edges
        assertTrue(isZero(result.dotProduct(p1.subtract(p2))),
                "Triangle's normal is not orthogonal to one of the edges");

        assertTrue(isZero(result.dotProduct(p1.subtract(p3))),
                "Triangle's normal is not orthogonal to one of the edges");

        assertTrue(isZero(result.dotProduct(p2.subtract(p3))),
                "Triangle's normal is not orthogonal to one of the edges");

    }

    /** Test method for {@link geometries.Triangle#findGeoIntersectionsHelper(Ray)}. */
    @Test
    void findIntersections() {
        Triangle triangle = new Triangle(new Point(0,-1,2), new Point(3,3,2), new Point(-3,3,2));

        // ============ Equivalence Partitions Tests ==============

        //TC01: the point inside the triangle
        Ray ray = new Ray(new Point(2,0,0), new Vector(-1, 2,2));
        assertEquals(1, triangle.findIntersections(ray).size(), "ERROR: Wrong number of points intersects the sphere " +
                "when the point Inside the triangle");

        assertEquals(new Point(1,2,2), triangle.findIntersections(ray).getFirst(), "ERROR: Wrong point intersects the sphere " +
                "when the point Inside the triangle");

        //TC02: the point outside against edge
        ray = new Ray(new Point(2,0,0), new Vector(-4, 1,2));
        assertNull(triangle.findIntersections(ray), "ERROR: Wrong number of points intersects the sphere " +
                "when the point outside against edge the triangle");

        //TC03: the point outside against vertex
        ray = new Ray(new Point(2,0,0), new Vector(-2, -3,2));
        assertNull(triangle.findIntersections(ray), "ERROR: Wrong number of points intersects the sphere " +
                "when the point outside against vertex the triangle");

        // =============== Boundary Values Tests ==================

        //TC11: the point on edge of the triangle
        ray = new Ray(new Point(2,0,0), new Vector(-2, 3,2));
        assertNull(triangle.findIntersections(ray),"ERROR: Wrong number of points intersects the sphere " +
                "when the point on edge of the triangle");

        //TC12: the point in vertex of the triangle
        ray = new Ray(new Point(2,0,0), new Vector(-5, 3,2));
        assertNull(triangle.findIntersections(ray),"ERROR: Wrong number of points intersects the sphere " +
                "when the point in vertex of the triangle");

        //TC13: the point on edge's continuation
        ray = new Ray(new Point(2,0,0), new Vector(1, -5,2));
        assertNull(triangle.findIntersections(ray),"ERROR: Wrong number of points intersects the sphere " +
                "when the point on edge's continuation");
    }
}
