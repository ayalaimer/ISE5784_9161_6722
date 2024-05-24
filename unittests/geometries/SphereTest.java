package geometries;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link geometries.Sphere} class.
 */
public class SphereTest {
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /** Test method for {@link geometries.Sphere#getNormal(primitives.Point)}. */
    @Test
    void testGetNormal()
    {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple case
        Sphere sphere = new Sphere(new Point(0, 0, 0), 1);
        Vector normal = sphere.getNormal(new Point(1, 0, 0));
        assertEquals(new Vector(1, 0, 0), normal, "Sphere's normal is incorrect");

        // TC02: Another point on the sphere
        normal = sphere.getNormal(new Point(0, 1, 0));
        assertEquals(new Vector(0, 1, 0), normal, "Sphere's normal is incorrect");

        // =============== Boundary Values Tests ==================

        // TC10: Normal at a point on the surface
        normal = sphere.getNormal(new Point(0, 0, 1));
        assertEquals(new Vector(0, 0, 1), normal, "Sphere's normal at the surface is incorrect");

        // TC11: Normal length is 1
        assertEquals(1, normal.length(), DELTA, "Sphere's normal is not a unit vector");
    }
}
