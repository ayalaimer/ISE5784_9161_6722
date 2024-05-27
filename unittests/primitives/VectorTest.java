package primitives;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link primitives.Vector} class.
 */
public class VectorTest {

    /**
     * Test method for {@link primitives.Vector#Vector(double, double, double)}constructor.
     */
    @Test
    public void testConstructorWithCoordinates() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1.0, 2.0, 3.0);
        assertEquals(new Double3(1.0, 2.0, 3.0), v.xyz);
    }

    /**
     * Test method for {@link primitives.Vector#Vector(Double3)} constructor.
     */
    @Test
    public void testConstructorWithDouble3() {
        // ============ Equivalence Partitions Tests ==============
        Double3 coordinates = new Double3(4.0, 5.0, 6.0);
        Vector v = new Vector(coordinates);
        assertEquals(coordinates, v.xyz);
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(0,0,0),
                "Error: value cannot be Zero");
    }

    /**
     * Test method for {@link primitives.Vector#Vector(Double3)} constructor.
     */
    @Test
    public void testConstructorThrowsExceptionForZeroVector() {
        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(0.0, 0.0, 0.0),
                "ERROR: zero vector does not throw an exception"
        );
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(Double3.ZERO),
                "ERROR: zero vector does not throw an exception"
        );
    }

    /**
     * Test method for {@link primitives.Vector#add(Vector)}
     */
    @Test
    public void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        Vector v2 = new Vector(3.0, 2.0, 1.0);
        assertEquals(new Vector(4.0, 4.0, 4.0), v1.add(v2));

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,
                () -> v1.add(new Vector(-1, -2, -3)),
                "ERROR: Vector + itself does not throw an exception"
        );
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}
     */
    @Test
    public void testScale() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1.0, 2.0, 3.0);
        assertEquals(new Vector(2.0, 4.0, 6.0), v.scale(2));
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}
     */
    @Test
    public void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        Vector v2 = new Vector(4.0, -5.0, 6.0);
        assertEquals(12.0, v1.dotProduct(v2), 0.0001);
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}
     */
    @Test
    public void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        Vector v2 = new Vector(4.0, 5.0, 6.0);
        Vector v3 = new Vector(2.0, 4.0, 6.0);
        assertEquals(new Vector(-3.0, 6.0, -3.0), v1.crossProduct(v2));

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,
                () -> v1.crossProduct(v3),
                "ERROR: crossProduct() for parallel vectors does not throw an exception"
        );
    }

    /**
     * Test method for {@link Vector#lengthSquared()}
     */
    @Test
    public void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1.0, 2.0, 2.0);
        double result = v.lengthSquared();
        assertEquals(9.0, result, 1e-6);
    }

    /**
     * Test method for {@link Vector#length()}
     */
    @Test
    public void testLength() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1.0, 2.0, 3.0);
        assertEquals(Math.sqrt(14), v.length(), 0.0001, "ERROR: length() wrong value");
    }

    /**
     * Test method for {@link Vector#normalize()}
     */
    @Test
    public void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(3.0, 0.0, 4.0);
        Vector normalized = v.normalize();
        assertEquals(new Vector(0.6, 0.0, 0.8),normalized);

        // =============== Boundary Values Tests ==================
        // Check that the normalized vector is a unit vector
        assertEquals(1.0, normalized.length(), 0.0001, "ERROR: the normalized vector is not a unit vector");
    }
}
