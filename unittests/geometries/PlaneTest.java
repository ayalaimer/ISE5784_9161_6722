package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
    @Test
   void testFindIntersection() {
        final Vector v010 = new Vector(0, 1, 0);
        final Vector v001 = new Vector(0, 0, 1);
        final Point p111 = new Point(1, 1, 1);
        final Point p211 = new Point(2, 1, 1);
        final Point p122 = new Point(1, 2, 2);
        final Plane myPlan = new Plane(p111,v001);

        //TC01: Ray intersect the plane in 1 point
        Point p1=new Point(-1,0,1);
        var result1=myPlan.findIntersections(new Ray(new Point(0,0,2),new Vector(-1,0,-1)));
        assertEquals(1,result1.size(), "Wrong number of points");
        assertEquals(new Point(-1,0,1),result1.get(0), " Ray intersect the plane");

        //TC02: Ray does not intersect the plane in 1 point
        assertNull(myPlan.findIntersections(new Ray(new Point(0,0,2),new Vector(-1,0,-1))));

        //===============Boundary Value Tests==================

        //Group: Ray is parallel to the plane

        //TC03:  Ray is parallel and lies in the plane(0 points)
        assertNull(myPlan.findIntersections(new Ray(p111,v010)),"Ray is parallel and lies in the plane");

        //TC04:  Ray is parallel and not included in the plane(0 points)
        assertNull(myPlan.findIntersections(new Ray(p122,v010)),"  Ray is parallel and not included in the plane");

        //Group: Ray is orthogonal to the plane

        //TC05:  Ray is orthogonal and starts before the plane(0 points)
        var result5=myPlan.findIntersections(new Ray(new Point(1,1,0),v001));
        assertEquals(1,result5.size(), "Wrong number of points");
        assertEquals(p111,result5.get(0), " Ray is orthogonal and starts before the plane");

        //TC06:  Ray is orthogonal and starts in the plane(0 points)
        assertNull(myPlan.findIntersections(new Ray(p211,v001))," Ray is orthogonal and starts in the plane(0 points");

        //TC07:  Ray is orthogonal and starts after the plane(0 points)
        assertNull(myPlan.findIntersections(new Ray(new Point(1,1,-2),v001))," Ray is orthogonal and starts after the plane(0 points");

        //TC08:  Ray is neither orthogonal nor parallel (1 point)
        assertNull(myPlan.findIntersections(new Ray(p111,new Vector(1,0,1)))," Ray is neither orthogonal nor parallel");

        //TC09:  Ray starts in the plane (0 point)
        assertNull(myPlan.findIntersections(new Ray(p211,new Vector(1,1,0))),"Ray starts in the plane");



    }




}
