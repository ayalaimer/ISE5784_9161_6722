package renderer;

import static java.awt.Color.*;
import org.junit.jupiter.api.Test;
import geometries.*;
import lighting.*;
import primitives.*;
import scene.Scene;

public class HouseSceneTest {

    private final Scene scene = new Scene("House Scene");
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    @Test
    public void HouseScene() {

        // Create ground plane
        scene.geometries.add(
                new Plane(new Point(0, -50, 0), new Vector(0, 1, 0))
                        .setEmission(new Color(80, 160, 80)) // Green ground
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        );

        // Create house base
        double houseWidth = 200;
        double houseHeight = 150;

        // Front wall
        scene.geometries.add(
                new Polygon(
                        new Point(-houseWidth, -50, -400),
                        new Point(houseWidth, -50, -400),
                        new Point(houseWidth, houseHeight - 50, -400),
                        new Point(-houseWidth, houseHeight - 50, -400)
                ).setEmission(new Color(150, 75, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        );

        // Side wall
        scene.geometries.add(
                new Polygon(
                        new Point(houseWidth, -50, -400),
                        new Point(houseWidth + 50, -50, -450),
                        new Point(houseWidth + 50, houseHeight - 50, -450),
                        new Point(houseWidth, houseHeight - 50, -400)
                ).setEmission(new Color(139, 69, 19))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        );

        // Roof
        scene.geometries.add(
                new Triangle(
                        new Point(-houseWidth, houseHeight - 50, -400),
                        new Point(houseWidth, houseHeight - 50, -400),
                        new Point(0, houseHeight + 50 - 50, -400)
                ).setEmission(new Color(220, 20, 60))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        );

        // Side of the roof
        scene.geometries.add(
                new Triangle(
                        new Point(houseWidth, houseHeight - 50, -400),
                        new Point(houseWidth + 50, houseHeight - 50, -450),
                        new Point(0, houseHeight + 50 - 50, -400)  // Changed this point to match the peak of the main roof
                ).setEmission(new Color(200, 10, 50))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        );

        // Create door
        double doorWidth = 40;
        double doorHeight = 80;
        Point doorBottomLeft = new Point(-doorWidth / 2, -50, -399.9); // Slightly in front of the wall

        // Door frame
        scene.geometries.add(
                new Polygon(
                        doorBottomLeft,
                        doorBottomLeft.add(new Vector(doorWidth, 0, 0)),
                        doorBottomLeft.add(new Vector(doorWidth, doorHeight, 0)),
                        doorBottomLeft.add(new Vector(0, doorHeight, 0))
                ).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(0.6).setKs(0.4).setShininess(30))
        );

        // Door panel (slightly smaller and positioned inside the frame)
        double panelMargin = 2; // Margin for the panel inside the frame
        Point panelBottomLeft = doorBottomLeft.add(new Vector(panelMargin, panelMargin, 0.1));
        scene.geometries.add(
                new Polygon(
                        panelBottomLeft,
                        panelBottomLeft.add(new Vector(doorWidth - 2*panelMargin, 0, 0)),
                        panelBottomLeft.add(new Vector(doorWidth - 2*panelMargin, doorHeight - 2*panelMargin, 0)),
                        panelBottomLeft.add(new Vector(0, doorHeight - 2*panelMargin, 0))
                ).setEmission(new Color(160, 82, 45)) // Sienna color for the door panel
                        .setMaterial(new Material().setKd(0.6).setKs(0.4).setShininess(30))
        );

        // Door handle
        Point handleCenter = doorBottomLeft.add(new Vector(doorWidth * 0.8, doorHeight / 2, 0.2));
        scene.geometries.add(
                new Sphere(handleCenter, 2)
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(0.4).setKs(0.6).setShininess(100))
        );

        // Path to the door
        scene.geometries.add(
                new Polygon(
                        new Point(-50, -49.9, -399),
                        new Point(50, -49.9, -399),
                        new Point(100, -49.9, -200),
                        new Point(-100, -49.9, -200)
                ).setEmission(new Color(180, 180, 180))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        );

        // Left flower beds
        createFlowerBed(new Point(-houseWidth - 230, -50, -200));
        createFlowerBed(new Point(-houseWidth - 200, -50, -500));

        // Right flower beds
        createFlowerBed(new Point(houseWidth + 10, -50, -200));
        createFlowerBed(new Point(houseWidth + 50, -50, -500));

        // Right bushes
        createBush(new Point(70, -45, -350));
        createBush(new Point(120, -50, -300));
        createBush(new Point(100, -50, -250));

        // Left bushes
        createBush(new Point(-150, -45, -300));
        createBush(new Point(-100, -50, -350));

        // Left window
        createWindow(new Point(-houseWidth + 30, houseHeight/3, -399.8), 50, 50);

        // Right window
        createWindow(new Point(houseWidth - 70, houseHeight/3, -399.8), 50, 50);

        // Sun
        scene.geometries.add(
                new Sphere(new Point(150, 300, -600), 55d)
                        .setEmission(new Color(255, 255, 0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(0.6))
        );

        // Clouds
        createCloud(new Point(-100, 300, -500), 50);
        createCloud(new Point(100, 400, -550), 40);
        createCloud(new Point(200, 450, -500), 45);
        createCloud(new Point(-300, 350, -450), 55);
        createCloud(new Point(-500, 400, -550), 60);
        createCloud(new Point(350, 350, -500), 50);

        // Adding lights to the scene
        scene.lights.add(
                new DirectionalLight(new Color(255, 255, 224), new Vector(1, -1, -1))
        );
        scene.lights.add(
                new PointLight(new Color(255, 255, 255).scale(0.2), new Point(-200, 200, 0))
                        .setKl(4E-5).setKq(2E-7)
        );
        scene.lights.add(
                new PointLight(new Color(255, 255, 255).scale(0.2), new Point(200, 200, 0))
                        .setKl(4E-5).setKq(2E-7)
        );
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.015));

        // Background
        scene.background = new Color(135, 206, 250); // Light sky blue background

        // Setting up the camera and rendering the image
        cameraBuilder.setLocation(new Point(0, 50, 700)).setVpDistance(500)
                .setVpSize(500, 500)
                .setImageWriter(new ImageWriter("HouseScene", 800, 800))
                .build()
                .renderImage(5)
                .writeToImage();
    }

    /**
     * Creates a flower bed around center point.
     * @param center The center point of the flower bed
     */
    private void createFlowerBed(Point center) {
        int flowerCount = 20; // Number of flowers in bed

        for (int i = 0; i < flowerCount; i++) {
            double offsetX = Math.random() * 200;
            double offsetY = Math.random();
            double offsetZ = Math.random() * 200;
            createFlower(center.add(new Vector(offsetX, offsetY, offsetZ)));
        }
    }

    /**
     * Creates one flower at the specified base point.
     * @param base The base point of the flower
     */
    private void createFlower(Point base) {
        double size = 10;
        Color[] petalColors = {
                new Color(255, 182, 193), // Light Pink
                new Color(255, 105, 180), // Hot Pink
                new Color(123, 104, 238), // Medium Slate Blue
                new Color(0, 255, 127),   // Spring Green
                new Color(135, 206, 235), // Sky Blue
                new Color(255, 160, 122), // Light Salmon
                new Color(255, 99, 71),   // Tomato
                new Color(218, 112, 214), // Orchid
                new Color(186, 85, 211),  // Medium Orchid
                new Color(147, 112, 219)  // Medium Purple
        };
        Color petalColor = petalColors[(int) (Math.random() * petalColors.length)];
        Color stemColor = new Color(0, 100, 0); // Dark green

        // Stem
        double stemWidth = size * 0.1;
        double stemHeight = size * 3;
        Point stemTopLeft = base.add(new Vector(-stemWidth / 2, 0, 0));
        scene.geometries.add(new Polygon(
                stemTopLeft,
                stemTopLeft.add(new Vector(stemWidth, 0, 0)),
                stemTopLeft.add(new Vector(stemWidth, stemHeight, 0)),
                stemTopLeft.add(new Vector(0, stemHeight, 0)))
                .setEmission(stemColor)
                .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(10)));

        // Flower center point
        Point flowerCenter = base.add(new Vector(0, size * 3, 0));

        // Flower center sphere
        scene.geometries.add(new Sphere(flowerCenter, size * 0.3)
                .setEmission(new Color(YELLOW))
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));

        // Petals
        int numPetals = 5;
        for (int i = 0; i < numPetals; i++) {
            double angle = 2 * Math.PI * i / numPetals;
            double x = Math.cos(angle) * size * 0.6;
            double y = Math.sin(angle) * size * 0.6;
            Point petalCenter = flowerCenter.add(new Vector(x, y, size * 0.1)); // Slightly forward in Z

            scene.geometries.add(new Sphere(petalCenter, size * 0.25)
                    .setEmission(petalColor)
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));
        }
    }

    /**
     * Creates a bush around center point.
     * @param center The center point of the bush
     */
    private void createBush(Point center) {
        for (int i = 0; i < 10; i++) {
            double offsetX = Math.random() * 8;
            double offsetY = Math.random() * 8;
            double offsetZ = Math.random() * 8;

            scene.geometries.add(
                    new Sphere(center.add(new Vector(offsetX, offsetY, offsetZ)), 12)
                            .setEmission(new Color(0, 100, 0)) // Green
                            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
            );
        }
    }

    /**
     * Creates a cloud around center point.
     * @param center The center point of the cloud
     * @param radius The radius of the spheres that make up the cloud
     */
    private void createCloud(Point center, double radius) {
        for (int i = 0; i < 8; i++) {
            double offsetX = (Math.random() - 0.5) * radius * 1.5;
            double offsetY = (Math.random() - 0.5) * radius * 0.5;
            double offsetZ = (Math.random() - 0.5) * radius * 0.5;
            double sphereRadius = radius * (0.5 + Math.random() * 0.5);
            scene.geometries.add(
                    new Sphere(center.add(new Vector(offsetX, offsetY, offsetZ)), sphereRadius)
                            .setEmission(new Color(250, 250, 250)) // Whiter color
                            .setMaterial(new Material().setKd(0.7).setKs(0.3).setShininess(100).setKt(0.3))
            );
        }
    }

    /**
     * Creates a simple window at the specified top-left point.
     * @param topLeft The top-left point of the window
     * @param width The width of the window
     * @param height The height of the window
     */
    private void createWindow(Point topLeft, double width, double height) {
        Color frameColor = new Color(BLACK);
        Color glassColor = new Color(173, 216, 230); // Light blue color for glass
        double frameThickness = 0.07 * width;

        // Frame (black rectangle)
        scene.geometries.add(new Polygon(
                topLeft,
                topLeft.add(new Vector(width, 0, 0)),
                topLeft.add(new Vector(width, -height, 0)),
                topLeft.add(new Vector(0, -height, 0)))
                .setEmission(frameColor)
                .setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(30)));

        // Four glass panels (light blue rectangles)
        double glassWidth = (width - 3 * frameThickness) / 2;
        double glassHeight = (height - 3 * frameThickness) / 2;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Point glassTopLeft = topLeft.add(new Vector(
                        frameThickness + i * (glassWidth + frameThickness),
                        -frameThickness - j * (glassHeight + frameThickness),
                        0.1)); // Slightly forward to be above the frame

                scene.geometries.add(new Polygon(
                        glassTopLeft,
                        glassTopLeft.add(new Vector(glassWidth, 0, 0)),
                        glassTopLeft.add(new Vector(glassWidth, -glassHeight, 0)),
                        glassTopLeft.add(new Vector(0, -glassHeight, 0)))
                        .setEmission(glassColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.9).setShininess(500).setKt(0.5).setKr(0.5))
                );
            }
        }
    }
}
