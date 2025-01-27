package renderer;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing Camera Class.
 * This class contains unit tests for the {@link renderer.Camera} class,
 * specifically for the method {@link renderer.Camera#constructRay(int, int, int, int)}.
 * These tests cover both equivalence partitions and boundary values.
 * <p>
 * The camera is built using the {@link renderer.Camera.Builder} class.
 * <p>
 * The tests verify that the rays constructed by the camera are as expected for
 * different view plane configurations and pixel coordinates.
 */
class CameraTests {
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
     * Test method for {@link renderer.Camera#constructRay(int, int, int, int)}.
     * <p>
     * This test method includes several test cases:
     *
     * <ul>
     * <li>Equivalence Partitions Tests:
     * <ul>
     * <li>EP01: 4X4 Inside (1,1)</li>
     * </ul>
     * </li>
     * <li>Boundary Values Tests:
     * <ul>
     * <li>BV01: 4X4 Corner (0,0)</li>
     * <li>BV02: 4X4 Side (0,1)</li>
     * <li>BV03: 3X3 Center (1,1)</li>
     * <li>BV04: 3X3 Center of Upper Side (0,1)</li>
     * <li>BV05: 3X3 Center of Left Side (1,0)</li>
     * <li>BV06: 3X3 Corner (0,0)</li>
     * </ul>
     * </li>
     * </ul>
     *
     * @throws CloneNotSupportedException if an attempt to clone the camera fails
     */
    @Test
    void testConstructRay() throws CloneNotSupportedException {
        final String badRay = "Bad ray";

        // ============ Equivalence Partitions Tests ==============
        // EP01: 4X4 Inside (1,1)
        Camera camera1 = cameraBuilder.setVpSize(8, 8).build();
        assertEquals(new Ray(Point.ZERO, new Vector(1, -1, -10)),
                camera1.constructRay(4, 4, 1, 1), badRay);

        // =============== Boundary Values Tests ==================
        // BV01: 4X4 Corner (0,0)
        assertEquals(new Ray(Point.ZERO, new Vector(3, -3, -10)),
                camera1.constructRay(4, 4, 0, 0), badRay);

        // BV02: 4X4 Side (0,1)
        assertEquals(new Ray(Point.ZERO, new Vector(1, -3, -10)),
                camera1.constructRay(4, 4, 1, 0), badRay);

        // BV03: 3X3 Center (1,1)
        Camera camera2 = cameraBuilder.setVpSize(6, 6).build();
        assertEquals(new Ray(Point.ZERO, new Vector(0, 0, -10)),
                camera2.constructRay(3, 3, 1, 1), badRay);

        // BV04: 3X3 Center of Upper Side (0,1)
        assertEquals(new Ray(Point.ZERO, new Vector(0, -2, -10)),
                camera2.constructRay(3, 3, 1, 0), badRay);

        // BV05: 3X3 Center of Left Side (1,0)
        assertEquals(new Ray(Point.ZERO, new Vector(2, 0, -10)),
                camera2.constructRay(3, 3, 0, 1), badRay);

        // BV06: 3X3 Corner (0,0)
        assertEquals(new Ray(Point.ZERO, new Vector(2, -2, -10)),
                camera2.constructRay(3, 3, 0, 0), badRay);
    }
}
