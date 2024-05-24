package primitives;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

public class VectorTest {

    @Test
    public void testConstructorWithCoordinates() {
        Vector v = new Vector(1.0, 2.0, 3.0);
        assertEquals(new Double3(1.0, 2.0, 3.0),v.xyz);

    }

    @Test
    public void testConstructorWithDouble3() {
        Double3 coordinates = new Double3(4.0, 5.0, 6.0);
        Vector v = new Vector(coordinates);
        assertEquals( v.xyz,coordinates);
    }

    @Test
    public void testConstructorThrowsExceptionForZeroVector() {

        assertThrows(IllegalArgumentException.class,
                () -> new Vector(0.0, 0.0, 0.0),
                "ERROR: zero vector does not throw an exception"
        );
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(Double3.ZERO),
                "ERROR: zero vector does not throw an exception"
        );
    }

    @Test
    public void testAdd() {
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        Vector v2 = new Vector(3.0, 2.0, 1.0);
        assertEquals(new Vector(4.0, 4.0, 4.0), v1.add(v2));

        assertThrows(IllegalArgumentException.class,
                () -> v1.add(new Vector(-1,-2,-3)),
                "ERROR: Vector + itself does not throw an exception"
                );
    }

    @Test
    public void testScale() {
        Vector v = new Vector(1.0, 2.0, 3.0);
        assertEquals(new Vector(2.0, 4.0, 6.0), v.scale(2));
    }

    @Test
    public void testDotProduct() {
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        Vector v2 = new Vector(4.0, -5.0, 6.0);
        assertEquals(12.0, v1.dotProduct(v2), 0.0001);
    }

    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        Vector v2 = new Vector(4.0, 5.0, 6.0);
        Vector v3 = new Vector(2.0, 4.0, 6.0);
        assertEquals(new Vector(-3.0, 6.0, -3.0), v1.crossProduct(v2));
        assertThrows(IllegalArgumentException.class,
                () -> v1.crossProduct(v3),
                "ERROR: crossProduct() for parallel vectors does not throw an exception"
        );
    }

    @Test
    public void testLengthSquared() {
        Vector v = new Vector(1.0, 2.0, 2.0);
        double result = v.lengthSquared();
        assertEquals(9.0, result, 1e-6);
    }

    @Test
    public void testLength() {
        Vector v = new Vector(1.0, 2.0, 3.0);
        assertEquals(Math.sqrt(14),v.length(), 0.0001,"ERROR: length() wrong value");

        assertThrows(IllegalArgumentException.class,
                () -> v.length(),
                "ERROR: the normalized vector is not a unit vector"
        );
    }

    @Test
    public void testNormalize() {
        Vector v = new Vector(3.0, 0.0, 4.0);
        assertEquals(new Vector(0.6, 0.0, 0.8), v.normalize());
    }

    @Test
    public void testNormalizeLength() {
        Vector v = new Vector(3.0, 0.0, 4.0);
        assertEquals(1.0, v.normalize().length(), 0.0001);
    }
}
