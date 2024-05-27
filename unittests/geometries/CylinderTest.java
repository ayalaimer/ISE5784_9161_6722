package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link geometries.Cylinder} class.
 */
class CylinderTest {
    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    private final double DELTA = 0.000001;
    @Test
    void getNormal() {
        Cylinder cylinder=new Cylinder(1,new Ray(new Point(0,0,0),new Vector(0,0,1)),1);
        // ============ Equivalence Partitions Tests ==============
        // Test that check normal when point is on casing


        // ensure there are no exceptions
        assertDoesNotThrow(() -> cylinder.getNormal(new Point(1, 0, 0.5)),
                "ERROR:throws wrong exception");

        // generate the test result
        Vector result = cylinder.getNormal(new Point(1, 0, 0.5));

        // ensure |result| = 1
        assertEquals(1, result.length(), DELTA,
                "Cylinder's normal is not a unit vector");


        //test when point is on  the base 1
        // ensure there are no exceptions
        assertDoesNotThrow(() -> cylinder.getNormal(new Point(0.25, 0.25, 1)),
                "ERROR:throws wrong exception");

        // generate the test result
        Vector result2 = cylinder.getNormal(new Point(0.25, 0.25, 1));

        // ensure |result| = 1
        assertEquals(1, result2.length(), DELTA,
                "Cylinder's normal is not a unit vector");



        //test when point is on the base 2
        // ensure there are no exceptions
        assertDoesNotThrow(() -> cylinder.getNormal(new Point(0.25, 0.25, 0)),
                "ERROR:throws wrong exception");

        // generate the test result
        Vector result3 = cylinder.getNormal(new Point(0.25, 0.25, 0));

        // ensure |result| = 1
        assertEquals(1, result3.length(), DELTA,
                "Cylinder's normal is not a unit vector");



        // =============== Boundary Values Tests ==================
        // ensure there are no exceptions
        assertDoesNotThrow(() -> cylinder.getNormal(new Point(1, 0, 1)),
                "ERROR:throws wrong exception");

        // generate the test result
        Vector result4 = cylinder.getNormal(new Point(1, 0, 1));

        // ensure |result| = 1
        assertEquals(1, result4.length(), DELTA,
                "Cylinder's normal is not a unit vector");


        assertDoesNotThrow(() -> cylinder.getNormal(new Point(1, 0, 0)),
                "ERROR:throws wrong exception");

        // generate the test result
        Vector result5 = cylinder.getNormal(new Point(1, 0, 0));

        // ensure |result| = 1
        assertEquals(1, result5.length(), DELTA,
                "Cylinder's normal is not a unit vector");


        assertDoesNotThrow(() -> cylinder.getNormal(new Point(0,0,0)),
                "ERROR:throws wrong exception");

        // generate the test result
        Vector result6 = cylinder.getNormal(new Point(0,0,0));

        // ensure |result| = 1
        assertEquals(1, result6.length(), DELTA,
                "Cylinder's normal is not a unit vector");


        assertDoesNotThrow(() -> cylinder.getNormal(new Point(0, 0, 1)),
                "ERROR:throws wrong exception");

        // generate the test result
        Vector result7 = cylinder.getNormal(new Point(0, 0, 1));

        // ensure |result| = 1
        assertEquals(1, result7.length(), DELTA,
                "Cylinder's normal is not a unit vector");
 }

}
