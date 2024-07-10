package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Interface representing a light source in the scene.
 * Provides methods to get the intensity of the light and the direction of the light
 * at a given point in the scene.
 */
public interface LightSource {

    /**
     * Gets the intensity of the light at a given point.
     *
     * @param p the point at which to get the light intensity
     * @return the color representing the light intensity at the given point
     */
    public Color getIntensity(Point p);

    /**
     * Gets the direction of the light at a given point.
     *
     * @param p the point at which to get the light direction
     * @return the vector representing the direction of the light at the given point
     */
    public Vector getL(Point p);

    public double getDistance(Point point);
}
