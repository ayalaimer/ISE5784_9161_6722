package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import java.util.List;
import geometries.Intersectable.GeoPoint;

/**
 * A basic Ray Tracer implementation that extends the RayTracerBase abstract class.
 * This ray tracer searches for intersections between the ray and the 3D model of the scene.
 * If no intersections are found, it returns the background color of the scene.
 * Otherwise, it finds the closest intersection point using the method added to the Ray class,
 * calculates the color at that point using the private method calcColor, and returns it.
 */
public class SimpleRayTracer extends RayTracerBase {

    /**
     * Constructor for the SimpleRayTracer class.
     * @param scene The Scene object containing all the objects to be rendered.
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        // Find intersection points between the ray and the scene geometries
        List<Point> intersectionPoints = scene.geometries.findIntersections(ray);

        // If no intersections found, return the background color
        if (intersectionPoints == null)
            return scene.background;

        // Find the closest intersection point to the ray's origin
        Point closestPoint = ray.findClosestPoint(intersectionPoints);

        // Calculate and return the color at the closest intersection point
        return calcColor(closestPoint);
    }

    /**
     * Method to calculate the color of an object at a specific intersection point.
     * For this stage of the mini-project, the method returns the ambient light intensity
     * of the scene, as we are not yet using the given point parameter.
     * @param point The Point object representing the intersection point with the Ray.
     * @return The Color of the object at the intersection point.
     */

    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }

}
