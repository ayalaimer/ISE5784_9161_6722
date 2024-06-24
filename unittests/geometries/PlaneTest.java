package geometries;

import primitives.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/** The class is a JUnit test class used to test the functionality of the Plane class */
class PlaneTests {

    /** Test method for {@link geometries.Plane#Plane(Point, Point, Point)}. */
    @Test
    void testConstructor() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct plane
        try {
            new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct plane");
        }

        // =============== Boundary Values Tests ==================

        // TC10 - Test for two equal vertices
        Point p = new Point(1, 0, 0);
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(p, p, new Point(0, 1, 0)),
                "Constructed a plane with two equal vertices");

        // TC11 - Constructed a plane with three collinear vertices
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(1, 0, 0), new Point(2, 0, 0), new Point(3, 0, 0)),
                "Constructed a plane with collinear vertices");
    }

    /** Test method for {@link geometries.Plane#getNormal(primitives.Point)} */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        // Define three non-collinear points to create a plane
        Point p1 = new Point(0, 0, 1);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(1, 0, 0);

        Plane plane = new Plane(p1, p2, p3);

        // generate the test result
        Vector result = plane.getNormal(p1);
        // ensure |result| = 1 , Verify that the length of the computed normal vector is equal to 1
        assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");

        // ensure the result is orthogonal to the plane
        assertTrue(isZero(result.dotProduct(p1.subtract(p2))),
                "Plane's normal is not orthogonal to the plane");
    }

    /** Test method for {@link geometries.Plane#findGeoIntersectionsHelper(Ray)}. */
    @Test
    void testFindIntersections() {
        Plane pl = new Plane(new Point(0, 0, 2), new Point(1, 0, 2), new Point(0, 1, 2));

        // ============ Equivalence Partitions Tests ==============

        // TC01: ray intersects the plane
        Ray ray = new Ray(new Point(-1, 0, -1), new Vector(1, 0, 1));
        assertEquals(1, pl.findIntersections(ray).size(), "ERROR: Wrong number of points intersects the plane");
        assertEquals(new Point(2, 0, 2), pl.findIntersections(ray).getFirst(), "ERROR: Wrong value point intersects the plane");

        // TC02: Ray does not intersect the plane
        ray = new Ray(new Point(3, 0, 3), new Vector(1, 0, 1));
        assertNull(pl.findIntersections(ray), "ERROR: Wrong number of points intersects the plane");

        // =============== Boundary Values Tests ==================

        // TC11: the ray included in the plane
        ray = new Ray(new Point(3, 0, 2), new Vector(1, 0, 1));
        assertNull(pl.findIntersections(ray), "ERROR: Wrong number of points intersects the plane" +
                " when Ray is parallel to the plane and included in the plane");

        // TC12: the ray not included in the plane
        ray = new Ray(new Point(3, 0, 3), new Vector(1, 1, 0));
        assertNull(pl.findIntersections(ray), "ERROR: Wrong number of points intersects the plane " +
                "when Ray is parallel to the plane and not included in the plane");

        // TC13: before the plane
        ray = new Ray(new Point(2, 3, 1), new Vector(0, 0, 1));
        assertEquals(1, pl.findIntersections(ray).size(), "ERROR: Wrong number of points intersects the plane" +
                "when the ray is orthogonal to the plane before the plane");
        assertEquals(new Point(2, 3, 2), pl.findIntersections(ray).getFirst(), "ERROR: Wrong value point intersects the plane" +
                "when the ray is orthogonal to the plane before the plane");

        // TC14: in the plane
        ray = new Ray(new Point(5, 3, 2), new Vector(0, 0, 1));
        assertNull(pl.findIntersections(ray), "ERROR: Wrong number of points intersects the plane" +
                "when the ray is orthogonal to the plane in the plane");

        // TC15: after the plane
        ray = new Ray(new Point(0, 3, 4), new Vector(0, 0, 1));
        assertNull(pl.findIntersections(ray), "ERROR: Wrong number of points intersects the plane" +
                "when the ray is orthogonal to the plane after the plane");

        // TC16: Ray is neither orthogonal nor parallel to and begins at the plane
        ray = new Ray(new Point(1, 0, 2), new Vector(1, 1, 1));
        assertNull(pl.findIntersections(ray), "ERROR: Wrong number of points intersects the plane " +
                "when the ray is neither orthogonal nor parallel to and begins at the plane");

        // TC17: Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane
        ray = new Ray(pl.getQ0(), new Vector(0, 1, 1));
        assertNull(pl.findIntersections(ray), "ERROR: Wrong number of points intersects the plane " +
                "when the ray is neither orthogonal nor parallel to and begins in the same point which appears as reference point in the plane");
    }
}
