package primitives;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * Unit tests for the Point class.
 */
public class PointTest {

    /**
     * Test method for Point constructor with coordinates.
     */
    @Test
    public void testConstructorWithCoordinates() {
        // ============ Equivalence Partitions Tests ==============
        Point p = new Point(1.0, 2.0, 3.0);
        assertEquals(new Double3(1.0, 2.0, 3.0), p.xyz);
    }

    /**
     * Test method for Point constructor with Double3.
     */
   // @Test
    public void testConstructorWithDouble3() {
        // ============ Equivalence Partitions Tests ==============
        Double3 coordinates = new Double3(4.0, 5.0, 6.0);
        assertEquals(coordinates, new Point(coordinates).xyz);
    }

    /**
     * Test method for Point subtract method.
     */

    @Test
    public void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1.0, 2.0, 3.0);
        Point p2 = new Point(0.5, 1.5, 2.5);
        assertEquals(new Vector(0.5, 0.5, 0.5), p1.subtract(p2), "ERROR: (point2 - point1) does not work correctly");
    }

    /**
     * Test method for Point add method.
     */
    @Test
    public void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        Point p = new Point(1.0, 2.0, 3.0);
        Vector v = new Vector(0.5, 0.5, 0.5);
        Vector v2 = new Vector(-1, -1, -3);
        assertEquals(new Point(1.5, 2.5, 3.5), p.add(v), "ERROR: (point + vector) = other point does not work correctly");
        assertEquals(new Point(0, 0, 0), p.add(v2), "ERROR: (point + vector) = center of coordinates does not work correctly");
    }

    /**
     * Test method for Point distanceSquared method.
     */
    @Test
    public void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1.0, 2.0, 3.0);
        Point p3 = new Point(2, 4, 5);
        assertEquals(9.0, p1.distanceSquared(p3), 0.0001);

        // =============== Boundary Values Tests ==================
        assertEquals(0, p1.distanceSquared(p1), "ERROR: point squared distance to itself is not zero");
        assertEquals(0, p1.distanceSquared(p3) - 9, "ERROR: squared distance between points is wrong");
        assertEquals(0, p3.distanceSquared(p1) - 9, "ERROR: squared distance between points is wrong");
    }

    /**
     * Test method for Point distance method.
     */
    @Test
    public void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1.0, 2.0, 3.0);
        Point p3 = new Point(2, 4, 5);
        assertEquals(Math.sqrt(9.0), p1.distance(p3), 1e-6);

        // =============== Boundary Values Tests ==================
        assertEquals(0, p1.distance(p1), "ERROR: point distance to itself is not zero");
        assertEquals(0, p1.distance(p3) - 3, "ERROR: distance between points is wrong");
        assertEquals(0, p3.distance(p1) - 3, "ERROR: distance between points is wrong");
    }
}
