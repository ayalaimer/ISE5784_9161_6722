package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;

import primitives.*;

/**
 * The class is a JUnit test class used to test the functionality of the Sphere class.
 */
class SphereTests {

    /**
     * Test method for {@link Sphere#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        Sphere sphere = new Sphere(1, new Point(0, 0, 0));
        Point p = new Point(1, 0, 0);
        Vector result = sphere.getNormal(p);
        // ensure normal = (1, 0 , 0)
        assertEquals(new Vector(1, 0, 0), result, "Sphere's Normal illegal");
        // ensure |result| = 1 , Verify that the length of the computed normal vector is equal to 1
        assertEquals(1, result.length(), 0.00000001, "Triangle's normal is not a unit vector");
    }

    /** Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}. */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        List<Point> result1 = sphere.findIntersections(new Ray(new Point(1.5, 0, 0), new Vector(-0.5, 1, 0)));
        assertEquals(1, result1.size(), "wrong number of points!");
        assertEquals(new Point(1, 1, 0), result1.getFirst(), "wrong intersection!");

        // TC04: Ray starts after the sphere (0 points)
        //we take the opposite of the vector in TC02, because if the ray hit twice the sphere in TC02, so the opposite
        //direction, will give us 0 intersection.

        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(-3, -1, 0))), "wrong! there is no intersection in this case");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)

        // TC11: Ray starts at sphere and goes inside (1 point)
        result1 = sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, -4, -2)));
        assertEquals(1, result1.size(), "wrong number of points!");
        assertEquals(new Point(1, -3 / 5d, -4 / 5d), result1.getFirst(),
                "wrong in case that ray starts at sphere and goes inside");

        // TC12: Ray starts at sphere and goes outside (0 point)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, 4, 2))),
                "In this case there is no intersection");

        // **** Group: Ray's line goes through the center

        // TC13: Ray starts before the sphere (2 points)
        List<Point> result2 = sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(-1, 0, 0)));
        assertEquals(2, result2.size(), "wrong number of points");
        if (result2.get(0).getX() > result2.get(1).getX())
            result2 = List.of(result2.get(1), result2.get(0));
        assertEquals(List.of(new Point(0, 0, 0), new Point(2, 0, 0)), result2,
                "wrong in the case that ray starts before the sphere, and goes through the center");

        // TC14: Ray starts at sphere and goes inside (1 point)
        result1 = sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(-1, 0, 0)));
        assertEquals(1, result1.size(), "wrong number of points!");
        assertEquals(new Point(0, 0, 0), result1.getFirst(),
                "Ray starts at sphere and goes inside");

        // TC15: Ray starts inside (1 point)
        result1 = sphere.findIntersections(new Ray(new Point(1, 0.5, 0), new Vector(0, 1, 0)));
        assertEquals(1, result1.size(), "wrong number of points!");
        assertEquals(new Point(1, 1, 0), result1.getFirst(),
                "Ray starts inside");

        // TC16: Ray starts at the center (1 point)
        result1 = sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 0, 1)));
        assertEquals(1, result1.size(), "wrong number of points!");
        assertEquals(new Point(1, 0, 1), result1.getFirst(),
                "Ray starts at the center");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, 1, 0))),
                "Ray starts at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 0, 2), new Vector(0, 0, 1))),
                "Ray starts after sphere ");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        Vector v = new Vector(1, 4, -3); //the tangent vector

        // TC19: Ray starts before the tangent point
        Point q1 = new Point(0.5, -13 / 5, 7 / 10);
        assertNull(sphere.findIntersections(new Ray(q1, v)), "Ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        Point q2 = new Point(1, -3 / 5d, -4 / 5d);
        assertNull(sphere.findIntersections(new Ray(q2, v)), "Ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        Point q3 = new Point(3, 37 / 5, -26 / 5);
        assertNull(sphere.findIntersections(new Ray(q3, v)), "Ray starts after the tangent point");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(0, 0, 1))),
                "Ray's line is outside, ray is orthogonal to ray start to sphere's center line");
    }
}
