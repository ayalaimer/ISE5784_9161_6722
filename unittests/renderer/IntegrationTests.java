package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * test the integration between the constructRay and geometric entities.
 */
public class IntegrationTests {

    /**
     * Camera builder for the tests.
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(new Scene("empty")))
            .setLocation(Point.ZERO)
            .setDirection(new Vector(0, 0, -1), new Vector(0, -1, 0))
            .setVpDistance(10)
            .setVpSize(3, 3)
            .setImageWriter(new ImageWriter("empty", 1, 1));

    /**
     * Determine how many intersection points a geometric entity should have with rays emitted from a camera.
     * For each ray, this method collects the intersection points with the given geometric entity and counts them.
     *
     * @param expected the expected number of intersection points.
     * @param camera   the camera from which rays are constructed.
     * @param geo      the geometric entity being tested for intersections.
     * @param nX
     * @param nY
     * @param message  the name of the test calling this function.
     */
    private void assertCountIntersections(int expected, Camera camera, Intersectable geo, int nX, int nY, String message) {

        // Counter for intersection points.
        int countIntersection = 0;


        // Iterate over each pixel on the view plane.
        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                // Construct a ray for the current pixel.
                Ray ray = camera.constructRay(nX, nY, j, i);

                // Find intersection points of the ray with the geometric entity.
                List<Point> intersectionPoints = geo.findIntersections(ray);

                // If there are intersection points, add their count to the total.
                countIntersection += ((intersectionPoints == null) ? 0 : intersectionPoints.size());
            }
        }

        // Assert that the counted intersections match the expected value.
        assertEquals(expected, countIntersection, "Wrong amount of intersections with "
                + geo.getClass() + ": " + message);
    }

    /**
     * Test for camera-ray-sphere integration.
     */
    @Test
    public void cameraRaySphereIntegration() throws CloneNotSupportedException {
        // Camera creation checks
        Camera camera1 = cameraBuilder.setLocation(Point.ZERO).setVpDistance(1).build();
        Camera camera2 = cameraBuilder.setLocation(new Point(0, 0, 0.5)).setVpDistance(1).build();

        // TC01: Sphere in front of the camera (2 points)
        assertCountIntersections(2, camera1, new Sphere(new Point(0, 0, -3), 1), 3, 3, "TC01: Sphere in front of the camera");

        // TC02: Sphere intersects the view plane before the camera (18 points)
        assertCountIntersections(18, camera2, new Sphere(new Point(0, 0, -2.5), 2.5), 3, 3, "TC02: Sphere intersects the view plane before the camera");

        // TC03: Sphere intersects the view plane before the camera (10 points)
        assertCountIntersections(10, camera2, new Sphere(new Point(0, 0, -2), 2), 3, 3, "TC03: Sphere intersects the view plane before the camera");

        // TC04: Sphere contains the view plane and the camera (9 points)
        assertCountIntersections(9, camera1, new Sphere(Point.ZERO, 4), 3, 3, "TC04: Sphere contains the view plane and the camera");

        // TC05: Sphere behind the camera (0 points)
        assertCountIntersections(0, camera1, new Sphere(new Point(0, 0, 1), 0.5), 3, 3, "TC05: Sphere behind the camera");
    }

    /**
     * Test for camera-ray-plane integration.
     */
    @Test
    public void cameraRayPlaneIntegration() throws CloneNotSupportedException {
        // Camera creation check
        Camera camera = cameraBuilder.setLocation(Point.ZERO).setVpDistance(1).build();

        // TC01: Plane in front of the camera, parallel to the view plane (9 points)
        assertCountIntersections(9, camera, new Plane(new Point(0, 0, -5), new Vector(0, 0, 1)), 3, 3, "TC01: Plane in front of the camera, parallel to the view plane");

        // TC02: Plane has acute angle to the view plane, all rays intersect (9 points)
        assertCountIntersections(9, camera, new Plane(new Point(0, 0, -5), new Vector(0, 1, 2)), 3, 3, "TC02: Plane has acute angle to the view plane, all rays intersect");

        // TC03: Plane has obtuse angle to the view plane, parallel to lower rays (6 points)
        assertCountIntersections(6, camera, new Plane(new Point(0, 0, -2), new Vector(0, 1, -1)), 3, 3, "TC03: Plane has obtuse angle to the view plane, parallel to lower rays");

        // TC04: Plane beyond the view plane (0 points)
        assertCountIntersections(0, camera, new Plane(new Point(0, 0, 1), new Vector(0, 0, 1)), 3, 3, "TC04: Plane beyond the view plane");
    }

    /**
     * Test for camera-ray-triangle integration.
     */
    @Test
    public void cameraRayTriangleIntegration() throws CloneNotSupportedException {
        // Camera creation check
        Camera camera = cameraBuilder.setLocation(Point.ZERO).setVpDistance(1).build();

        // TC01: Small triangle in front of the view plane (1 point)
        assertCountIntersections(1, camera, new Triangle(new Point(-1, -1, -2), new Point(1, -1, -2), new Point(0, 1, -2)), 3, 3, "TC01: Small triangle in front of the view plane");

        // TC02: Large triangle in front of the view plane (2 points)
        assertCountIntersections(2, camera, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -20, -2)), 3, 3, "TC02: Large triangle in front of the view plane ");
    }
}
