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
        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);

        if (points == null)
            return this.scene.background;

        return calcColor(ray.findClosestGeoPoint(points), ray);
    }


    private Color calcColor(GeoPoint GeoPoint,Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(GeoPoint.geometry.getEmission());
    }


}
