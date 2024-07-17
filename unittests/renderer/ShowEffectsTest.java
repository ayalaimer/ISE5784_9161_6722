package renderer;

import static java.awt.Color.*;

import lighting.DirectionalLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.AmbientLight;
import primitives.*;
import scene.Scene;

/**
 * This class is used to demonstrate various effects such as transparency, reflection,
 * and shading using a simple scene.
 */
public class ShowEffectsTest {

    private final Scene scene = new Scene("Show Effects Scene");
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    /**
     * This test method creates a scene with various geometries and lights,
     * then renders the scene to an image.
     */
    @Test
    public void ShowEffects() {
        // Adding geometries to the scene
        scene.geometries.add(
                new Sphere(new Point(0, 0, -50), 25d)
                        .setEmission(new Color(MAGENTA))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(0.4).setKr(0.3)),
                new Triangle(new Point(-10, -80, -80), new Point(50, -50, -60), new Point(0, 50, -60))
                        .setEmission(new Color(GREEN))
                        .setMaterial(new Material().setKd(0.9).setKs(0.5).setShininess(60)),
                new Sphere(new Point(100, 100, -300), 75d)
                        .setEmission(new Color(CYAN))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(40).setKt(0.6).setKr(0.3)),
                new Sphere(new Point(50, 50, -100), 30d)
                        .setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(50).setKt(0.2).setKr(0.3))
        );

        // Adding lights to the scene
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.1));
        scene.lights.add(
                new SpotLight(new Color(150, 100, 100), new Point(60, 50, 50), new Vector(0, 0, -1))
                        .setKl(4E-5).setKq(2E-7)
        );
        scene.lights.add(
                new DirectionalLight(new Color(100, 50, 50), new Vector(1, -1, -1))
        );

        // Setting up the camera and rendering the image
        cameraBuilder.setLocation(new Point(0, 0, 1500)).setVpDistance(1000)
                .setVpSize(200, 200)
                .setImageWriter(new ImageWriter("ShowEffectsScene", 600, 600))
                .build()
                .renderImage()
                .writeToImage();
    }
}
