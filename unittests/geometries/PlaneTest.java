package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link geometries.Plane} class.
 */
class PlaneTest {

    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Plane#Plane(Point, Point, Point)} constructor.
     */
    @Test
    void testConstructorWithThreePoints() {
        // =============== Boundary Values Tests ==================
        // TC10: Two points are the same
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(0, 0, 0);
        Point p3 = new Point(1, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p3),
                "ERROR: Two points are the same");

        // TC11: Three points are on the same line
        Point p4 = new Point(1, 1, 1);
        Point p5 = new Point(2, 2, 2);
        Point p6 = new Point(3, 3, 3);
        assertThrows(IllegalArgumentException.class, () -> new Plane(p4, p5, p6),
                "ERROR: Three points are on the same line");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {

        Plane plane = new Plane(new Point(1, 1, 0), new Point(2, 1, 0), new Point(1, 2, 3));

        // Expected normal vector (based on manual cross product calculation)
        Vector expectedNormal1 = new Vector(0, -3, 1).normalize();
        Vector expectedNormal2 = expectedNormal1.scale(-1);

        Vector normal = plane.getNormal(new Point(3, 2, 0));

        assertTrue(normal.equals(expectedNormal1) || normal.equals(expectedNormal2),
                " The calculation of the normal to the plane is incorrect");

        assertEquals(1, normal.length(), DELTA, " The normal vector is not a unit vector");
    }
}
