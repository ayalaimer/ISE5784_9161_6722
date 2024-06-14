package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

import static primitives.Color.BLACK;

/**
 * Represents a scene in the 3D world, containing geometries, lighting, and background color.
 */
public class Scene {
    /** The name of the scene */
    public String name;

    /** The background color of the scene, default is black */
    public Color background = BLACK;

    /** The ambient light of the scene, default is no ambient light */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /** The collection of geometries in the scene */
    public Geometries geometries;

    /**
     * Sets the background color of the scene.
     *
     * @param background The background color to set.
     * @return The scene object, for chaining.
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight The ambient light to set.
     * @return The scene object, for chaining.
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Sets the geometries of the scene.
     *
     * @param geometries The geometries to set.
     * @return The scene object, for chaining.
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * Constructs a new Scene with the specified name.
     *
     * @param name The name of the scene.
     */
    public Scene(String name) {
        this.name = name;
        this.geometries = new Geometries();
    }
}
