package lighting;

import primitives.Color;
import primitives.Double3;

import static primitives.Color.BLACK;

/** The AmbientLight class represents a type of light source that illuminates
 *  the entire scene with a constant intensity, regardless of the position
 *  or orientation of the objects in the scene. */
public class AmbientLight {

    /** The intensity of the ambient light. */
private final Color intensity;

    /** A constant representing no ambient light. */
    public static final AmbientLight NONE = new AmbientLight(BLACK, Double3.ZERO);

    /** Constructs an AmbientLight object with the given intensity and attenuation factor.
     * @param iA the color of the light.
     * @param kA the attenuation factor of the light. */
    public AmbientLight(Color iA, Double3 kA) {
        intensity = iA.scale(kA);
    }

    /** Constructs an AmbientLight object with the given intensity and attenuation factor.
     * @param iA the color of the light.
     * @param kA the attenuation factor of the light. */
    public AmbientLight(Color iA, double kA) {
        intensity = iA.scale(kA);
    }

    /** @return the intensity of the ambient light. */
    public Color getIntensity() {
        return this.intensity;
    }

}
