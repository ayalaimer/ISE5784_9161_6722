package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Tube tube = new Tube(new Ray(new Point(1, 2, 3), new Vector(0, 1, 0)), 2.5);

        // ============ Equivalence Partitions Tests ==============

        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(3, 2, 4.5)),
                " The calculation of the tube's normal is incorrect");


        assertEquals(1, tube.getNormal(new Point(3, 4, 3)).length(), DELTA,
                "The normal vector is not a unit vector");

        // =============== Boundary Values Tests ==================

        // Test when the point is orthogonal to the ray's head goes to the ZERO vector
        assertThrows(IllegalArgumentException.class, () -> {
                    tube.getNormal(new Point(1, 2, 3)); // Use the same point as the ray's head
                },
                "ZERO vector is not allowed");
    }
}
