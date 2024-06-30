package renderer;


import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * An abstract class that serves as a base for Ray Tracing implementations.
 * Ray tracing is a rendering technique for generating an image by tracing the path of light
 * as pixels in the image plane and simulating the effects of its encounters with virtual objects.
 */
public abstract class RayTracerBase {

    /**
     * The Scene object containing all the objects to be rendered.
     */
    protected Scene scene;

    /**
     * Constructor for the RayTracerBase class.
     *
     * @param scene The Scene object containing all the objects to be rendered.
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }


    /**
     * Abstract method to trace a Ray and calculate the resulting color.
     *
     * @param ray The Ray object to trace.
     * @return The Color of the object at the intersection point with the Ray.
     */
    public abstract Color traceRay(Ray ray);
}
