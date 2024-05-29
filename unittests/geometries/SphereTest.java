package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import  java.util.Comparator;
import  java.util.List;
//import  java.util.stream.Collectors;

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

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
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

    private final Point p001 = new Point(0, 0, 1);
    private final Point p100 = new Point(1, 0, 0);
    private final Vector v001 = new Vector(0, 0, 1);

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(p100, 1d);
        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final var exp = List.of(gp1, gp2);
        final Vector v310 = new Vector(3, 1, 0);
        final Vector vm100 = new Vector(-1, 0, 0);
        final Vector v100 = new Vector(1, 0, 0);
        final Vector v010 = new Vector(0, 1, 0);
        final Vector v110 = new Vector(1, 1, 0);
        final Point p01 = new Point(-1, 0, 0);
        final Point p200=new Point(2,0,0);
        final Point p300=new Point(3,0,0);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        final var result1 = sphere.findIntersections(new Ray(p01, v310))
                .stream()
                .sorted(Comparator.comparingDouble(p -> p.distance(p01)))
                .toList();
        assertEquals(2, result1.size(), "Wrong number of points");
        assertEquals(exp, result1, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        List<Point> result2=sphere.findIntersections(new Ray(new Point(1.5,-0.7,0.5),new Vector(-0.34,1.02,0.43)));
        assertEquals(1,result2.size(), "Wrong number of points");
        assertEquals(new Point(1.1582,0.32536,0.93226),result2.get(0), " Ray starts inside the sphere");


        // TC04: Ray starts after the sphere (0 points)
        List<Point> result3=sphere.findIntersections(new Ray(new Point(10,3,6),v310));
        assertNull(result3, "Ray starts after the sphere");


        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        List<Point> result4=sphere.findIntersections(new Ray(p200,new Vector(-1,0,1)));
        assertEquals(1,result2.size(), "Wrong number of points");
        assertEquals(new Point(1,0,1),result4.get(0), " Ray starts  at sphere and goes inside");

        // TC12: Ray starts at sphere and goes outside (0 points)
        List<Point> result5=sphere.findIntersections(new Ray(p200,new Vector(0,1,1)));
        assertNull(result5, " Ray starts at sphere and goes outside ");
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        List <Point> result6 = sphere.findIntersections(new Ray(p300, vm100))
                .stream()
                .sorted(Comparator.comparingDouble(p -> p.distance(p300)))
                .toList();
        assertEquals(2,result6.size(), "Wrong number of points");
        final var exp1 = List.of(p200, new Point(0,0,0));
        assertEquals(exp1,result6.get(0), "Ray starts before the sphere");

        // TC14: Ray starts at sphere and goes inside (1 points)
        List <Point> result7 = sphere.findIntersections(new Ray(new Point(-1,-1,0), vm100));
        assertEquals(1,result7.size(), "Wrong number of points");
        assertEquals(new Point(1,1,0),result7.get(0), "Ray starts at sphere and goes inside ");

        // TC15: Ray starts inside (1 points)
        List <Point> result8 = sphere.findIntersections(new Ray(new Point(1,0.5,0), v010));
        assertEquals(1,result8.size(), "Wrong number of points");
        assertEquals(new Point(1,1,0),result7.get(0), "Ray starts inside (1 points) ");

        // TC16: Ray starts at the center (1 points)
        List <Point> result9 = sphere.findIntersections(new Ray(p100, v100));
        assertEquals(1,result9.size(), "Wrong number of points");
        assertEquals(new Point(1,1,0),result9.get(0), "Ray starts at the center (1 points) ");

        // TC17: Ray starts at sphere and goes outside (0 points)
        List <Point> result10 = sphere.findIntersections(new Ray(p200, v100));
        assertNull(result10, "Ray starts at sphere and goes outside ");

        // TC18: Ray starts after sphere (0 points)
        List <Point> result11 = sphere.findIntersections(new Ray(p300, v100));
        assertNull(result11, " Ray starts after sphere ");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point
        List <Point> result12 = sphere.findIntersections(new Ray(new Point(2,-1,0),v010));
        assertNull(result12, "Ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        List <Point> result13 = sphere.findIntersections(new Ray(p200,v010));
        assertNull(result13, "Ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        List <Point> result14 = sphere.findIntersections(new Ray(new Point(2,1,0),v010));
        assertNull(result14, "Ray starts after the tangent point");

        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        List <Point> result15 = sphere.findIntersections(new Ray(new Point(-1,0,0),v001));
        assertNull(result15, "Ray's line is outside, ray is orthogonal to ray start to sphere's center line");
    }
}
