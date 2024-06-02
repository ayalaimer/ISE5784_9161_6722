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
        Tube tube=new Tube(new Ray(new Point(0,0,0),new Vector(0,1,0)),1);
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        // ensure there are no exceptions
        assertDoesNotThrow(() -> tube.getNormal(new Point(1, 2, 0)),
                "ERROR:throws wrong exception");

        // generate the test result
        final Vector result = tube.getNormal(new Point(1, 2, 0));

        // ensure |result| = 1
        assertEquals(1, result.length(), DELTA,
                "Tube's normal is not a unit vector");

        //checks that tubes normal is calculated correctly

        // assertEquals(new Vector(1,0,0), result, "Tube's normal is not calculated correctly"); need to check what the normal is

        // ============ BVA Tests ==============
        assertDoesNotThrow(() -> tube.getNormal(new Point(1, 0, 0)),
                "ERROR:throws wrong exception");

        // generate the test result
        final Vector result1 = tube.getNormal(new Point(1, 0, 0));

        // ensure |result| = 1
        assertEquals(1, result1.length(), DELTA,
                "Tube's normal is not a unit vector");
 }

}
