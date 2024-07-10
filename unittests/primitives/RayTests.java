package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test class for testing the Ray class methods.
 */
public class RayTests {

    /**
     * Test method for {@link primitives.Ray#getPoint(double)()}
     */
    @Test
    public void testGetPoint() {
        // Create ray
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test for negative t value
        Point pointNegativeT = ray.getPoint(-2);
        assertEquals(new Point(-2, 0, 0),
                pointNegativeT,
                "TC01: Negative t value test");

        // TC02: Test for positive t value
        Point pointPositiveT = ray.getPoint(2);
        assertEquals(new Point(2, 0, 0),
                pointPositiveT,
                "TC02: Positive t value test");

        // =============== Boundary Values Tests ==================
        // TC11: Test for t = 0
        Point pointZeroT = ray.getPoint(0);
        assertEquals(new Point(0, 0, 0),
                pointZeroT,
                "TC11: t value zero test");
    }

    /**
     * Test method for {@link Ray#findClosestPoint(List)} method.
     * Specifically, it checks if the method returns the expected closest point when given a non-empty list of points,
     * an empty list of points, and when the closest point is in the beginning or end of the list.
     */
    @Test
    public void testFindClosestPoint() {
        Ray ray = new Ray(new Point(1, 0, 0), new Vector(1, 1, 0));
        Point closestPoint = new Point(1.5, 0.5, 0);
        List<Point> pointsList = List.of(new Point(2, 1, 0), closestPoint, new Point(3, 2, 0));
        assertEquals(closestPoint, ray.findClosestPoint(pointsList),
                "Wrong closest point (when the point is in the middle of the list)");

        // =============== Boundary Values Tests ==================
        // TC11: empty list
        List<Point> emptyList = new LinkedList<>();
        assertNull(ray.findClosestPoint(emptyList), "Wrong in Empty list");

        // TC12: the closest point is in the beginning of the list
        List<Point> pointsList1 = List.of(closestPoint, new Point(2, 1, 0), new Point(3, 2, 0));
        assertEquals(closestPoint, ray.findClosestPoint(pointsList1),
                "Wrong closest point (when the point is in the beginning of the list)");

        // TC13: the closest point is at the end of the list
        List<Point> pointsList2 = List.of(new Point(2, 1, 0), new Point(3, 2, 0), closestPoint);
        assertEquals(closestPoint, ray.findClosestPoint(pointsList2),
                "Wrong closest point (when the point is at the end of the list)");
    }
}
