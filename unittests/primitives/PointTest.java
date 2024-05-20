package primitives;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

public class PointTest {

    @Test
    public void testConstructorWithCoordinates() {
        Point p = new Point(1.0, 2.0, 3.0);
        assertEquals(new Double3(1.0, 2.0, 3.0), p.xyz);
    }

    @Test
    public void testConstructorWithDouble3() {
        Double3 coordinates = new Double3(4.0, 5.0, 6.0);
        assertEquals(coordinates, new Point(coordinates).xyz);
    }

    @Test
    public void testSubtract() {
        Point p1 = new Point(1.0, 2.0, 3.0);
        Point p2 = new Point(0.5, 1.5, 2.5);
        assertEquals(new Vector(0.5, 0.5, 0.5), p1.subtract(p2), "ERROR: (point2 - point1) does not work correctly");
    }

    @Test
    public void testAdd() {
        if (!(p1.add(v1Opposite).equals(Point.ZERO)))
            out.println("ERROR: (point + vector) = center of coordinates does not work correctly");
        Point p = new Point(1.0, 2.0, 3.0);
        Vector v = new Vector(0.5, 0.5, 0.5);
        Vector v2 = new Vector(-1, -1, -3);

        assertEquals(new Point(1.5, 2.5, 3.5),  p.add(v),"ERROR: (point + vector) = other point does not work correctly");
        assertEquals(new Point(0, 0, 0),  p.add(v2),"ERROR: (point + vector) = center of coordinates does not work correctly");
    }

    @Test
    public void testDistanceSquared() {
        Point p1 = new Point(1.0, 2.0, 3.0);
        Point p2 = new Point(2, 4, 6);

        assertEquals(50.0, p1.distanceSquared(p2), 0.0001);
        assertEquals(new Point(0, 0, 0), p1.distanceSquared(p1),"ERROR: point squared distance to itself is not zero");
       // assertEquals(new Point(0, 0, 0), p1.distanceSquared(p1),"ERROR: point squared distance to itself is not zero");
        //assertEquals(new Point(0, 0, 0), p1.distanceSquared(p1),"ERROR: point squared distance to itself is not zero");

    }

    @Test
    public void testDistance() {
        Point p1 = new Point(1.0, 2.0, 3.0);
        Point p2 = new Point(4.0, 6.0, 8.0);
        double result = p1.distance(p2);
        assertEquals(Math.sqrt(50.0), result, 1e-6);
    }

    @Test
    public void testEquals() {
        Point p1 = new Point(1.0, 2.0, 3.0);
        Point p2 = new Point(1.0, 2.0, 3.0);
        Point p3 = new Point(4.0, 5.0, 6.0);
        assertTrue(p1.equals(p2));
        assertFalse(p1.equals(p3));
    }

    @Test
    public void testToString() {
        Point p = new Point(1.0, 2.0, 3.0);
        String result = p.toString();
        assertEquals("(1.0,2.0,3.0)", result);
    }
}
