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
    @Test
    void testGetNormal() {
        Cylinder cylinder = new Cylinder(2.5, new Ray(new Point(1, 1, 0), new Vector(0, 1, 1)), 2.0);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Point on the side surface of the cylinder
        assertEquals(new Vector(1, 0, 0), cylinder.getNormal(new Point(3, 2.5, 2)), "Bad normal to the side surface of the cylinder");

        // TC02: Point on the bottom base of the cylinder
        assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(1, 1, -1)), "Bad normal to the bottom base of the cylinder");

        // TC03: Point on the top base of the cylinder
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(1, 3.5, 2.5)), "Bad normal to the top base of the cylinder");

        // =============== Boundary Values Tests ==================
        // TC11: Point at the center of the bottom base
        assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(1, 1, 0)), "Bad normal to the center of the bottom base");

        // TC12: Point at the center of the top base
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(1, 3.5, 2.5)), "Bad normal to the center of the top base");

        // TC13: Point at the edge between the side and the bottom base
        assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(3, 1, 0)), "Bad normal to the edge between the side and the bottom base");

        // TC14: Point at the edge between the side and the top base
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(3, 3.5, 2.5)), "Bad normal to the edge between the side and the top base");
    }
}
