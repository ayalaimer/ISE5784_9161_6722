package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * The Camera class represents a camera in 3D space with a location, direction, and view plane parameters.
 * It is used to construct rays through a view plane for ray tracing purposes.
 */
public class Camera implements Cloneable {
    private Point p0;
    private Vector vTo, vUp, vRight;

    private double viewPlaneWidth = 0.0, viewPlaneHeight = 0.0, viewPlaneDistance = 0.0;
    // The object used for writing the rendered image.
    private ImageWriter imageWriter;
    // The object used for tracing rays and computing colors.
    private RayTracerBase rayTracer;


    /**
     * Private constructor used by the Builder class.
     */
    private Camera() {
    }

    /**
     * Creates a new Builder instance for constructing a Camera.
     *
     * @return a new Builder instance
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Constructs a ray through a specified pixel on the view plane.
     *
     * @param nX number of pixels in the X direction
     * @param nY number of pixels in the Y direction
     * @param j  the column index of the pixel
     * @param i  the row index of the pixel
     * @return the constructed ray through the specified pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        if (nY == 0 || nX == 0)
            throw new IllegalArgumentException("It is impossible to divide by 0");

        Point Pc = p0.add(vTo.scale(viewPlaneDistance));
        double Rx = viewPlaneWidth / nX;
        double Ry = viewPlaneHeight / nY;
        Point Pij = Pc;

        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;

        if (!isZero(Xj)) {
            Pij = Pij.add(vRight.scale(Xj));
        }
        if (!isZero(Yi)) {
            Pij = Pij.add(vUp.scale(Yi));
        }

        return new Ray(p0, Pij.subtract(p0));
    }

    /**
     * Sets the distance from the camera to the view plane.
     *
     * @param distance the distance to set
     * @return the current Camera instance
     */
    public Camera setViewPlaneDistance(int distance) {
        this.viewPlaneDistance = distance;
        return this;
    }

    /**
     * Sets the size of the view plane.
     *
     * @param width  the width of the view plane
     * @param height the height of the view plane
     * @return the current Camera instance
     */
    public Camera setVpSize(int width, int height) {
        this.viewPlaneWidth = width;
        this.viewPlaneHeight = height;
        return this;
    }

    /**
     * Sets the ImageWriter instance for the camera.
     *
     * @param imageWriter the ImageWriter instance to set
     * @return the current Camera instance
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Sets the RayTracerBase instance for the camera.
     *
     * @param rayTracer the RayTracerBase instance to set
     * @return the current Camera instance
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * Creates a copy of the current Camera instance.
     *
     * @return a clone of the current Camera instance
     */
    @Override
    public Camera clone() {
        try {
            Camera clone = (Camera) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * Prints a grid on the view plane at a given interval and color.
     *
     * @param interval the interval between each line of the grid.
     * @param color    the color of the grid.
     * @throws MissingResourceException if the image writer field is not initialized.
     */
    public Camera printGrid(int interval, Color color) {

        if (this.imageWriter == null)
            throw new MissingResourceException("The field is not initialized", "Camera", "imageWriter");

        for (int i = 0; i < imageWriter.getNy(); i++) {
            for (int j = 0; j < imageWriter.getNx(); j++) {

                if (i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(j, i, color);
            }
        }
        writeToImage();
        return this;
    }

    /**
     * Writes the image to a file using the image writer.
     *
     * @throws MissingResourceException if the image writer field is not initialized.
     */
    public void writeToImage() {

        if (this.imageWriter == null)
            throw new MissingResourceException("The field is not initialized", "Camera", "imageWriter");

        imageWriter.writeToImage();
    }

    /**
     * Casts rays through each pixel on the view plane and computes their colors using the ray tracer.
     * Each pixel's color is then written to the image using the image writer.
     *
     * @param nX the number of pixels in the X direction
     * @param nY the number of pixels in the Y direction
     * @param j  the column index of the pixel
     * @param i  the row index of the pixel
     */
    private void castRay(int nX, int nY, int j, int i) {
        // Constructs a ray through the center of the pixel
        Ray ray = constructRay(nX, nY, j, i);
        // Computes the color of the ray using traceRay
        Color pixelColor = rayTracer.traceRay(ray);
        // Writes the pixel with the computed color
        imageWriter.writePixel(j, i, pixelColor);
    }

    /**
     * Renders the entire image by iterating over each pixel on the view plane,
     * casting rays through them, and computing their colors.
     * Uses the castRay method to cast rays and writePixel method to write colors to the image.
     *
     * @throws MissingResourceException if ImageWriter or RayTracerBase is not initialized
     */
    public Camera renderImage() {
        if (imageWriter == null || rayTracer == null) {
            throw new MissingResourceException("ImageWriter or RayTracerBase not initialized", "Camera", "");
        }

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        // Iterates over each pixel on the view plane
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                castRay(nX, nY, j, i);
            }
        }
        return this;
    }

    /**
     * The Builder class for constructing Camera instances.
     */
    public static class Builder {
        private static final String MISSING_DATA_ERROR = "Missing rendering data";
        private static final String CAMERA_CLASS_NAME = "Camera";
        private static final String POSITION_MISSING = "Camera position is missing";
        private static final String DIRECTION_MISSING = "Camera direction is missing";
        private static final String VPDIMENSIONS_MISSING = "View plane dimensions are missing";
        private static final String VPDISTANCE_MISSING = "View plane distance is missing";
        private static final String IMAGE_WRITER_MISSING = "ImageWriter instance is missing";
        private static final String RAY_TRACER_MISSING = "RayTracerBase instance is missing";

        private final Camera camera;

        /**
         * Constructs a new Builder instance.
         */
        public Builder() {
            this.camera = new Camera();
        }

        /**
         * Constructs a new Builder instance with a given Camera instance.
         *
         * @param myCamera the Camera instance to use
         */
        public Builder(Camera myCamera) {
            this.camera = myCamera;
        }

        /**
         * Sets the location of the camera.
         *
         * @param p0 the location to set
         * @return the current Builder instance
         */
        public Builder setLocation(Point p0) {
            if (p0 == null) {
                throw new IllegalArgumentException("The place cannot be null");
            }
            this.camera.p0 = p0;
            return this;
        }

        /**
         * Sets the direction of the camera.
         *
         * @param vTo the direction the camera is pointing
         * @param vUp the up direction relative to the camera's orientation
         * @return the current Builder instance
         */
        public Builder setDirection(Vector vTo, Vector vUp) {
            if (vTo == null || vUp == null) {
                throw new IllegalArgumentException("The vectors of direction cannot be null");
            }
            if (vTo.dotProduct(vUp) != 0) {
                throw new IllegalArgumentException("The vectors have to be orthogonal");
            }
            this.camera.vTo = vTo.normalize();
            this.camera.vUp = vUp.normalize();
            this.camera.vRight = vTo.crossProduct(vUp).normalize();
            return this;
        }

        /**
         * Sets the size of the view plane.
         *
         * @param width  the width of the view plane
         * @param height the height of the view plane
         * @return the current Builder instance
         */
        public Builder setVpSize(double width, double height) {
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("Height and Width have to be bigger than 0");
            }
            this.camera.viewPlaneWidth = width;
            this.camera.viewPlaneHeight = height;
            return this;
        }

        /**
         * Sets the distance from the camera to the view plane.
         *
         * @param distance the distance to set
         * @return the current Builder instance
         */
        public Builder setVpDistance(double distance) {
            if (distance <= 0) {
                throw new IllegalArgumentException("The distance has to be bigger than 0");
            }
            this.camera.viewPlaneDistance = distance;
            return this;
        }

        /**
         * Sets the ImageWriter instance for the camera.
         *
         * @param imageWriter the ImageWriter instance to set
         * @return the current Builder instance
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            if (imageWriter == null) {
                throw new IllegalArgumentException("ImageWriter cannot be null");
            }
            this.camera.imageWriter = imageWriter;
            return this;
        }

        /**
         * Sets the RayTracerBase instance for the camera.
         *
         * @param rayTracer the RayTracerBase instance to set
         * @return the current Builder instance
         */
        public Builder setRayTracer(RayTracerBase rayTracer) {
            if (rayTracer == null) {
                throw new IllegalArgumentException("RayTracerBase cannot be null");
            }
            this.camera.rayTracer = rayTracer;
            return this;
        }

        /**
         * Builds and returns the Camera instance.
         *
         * @return the constructed Camera instance
         * @throws CloneNotSupportedException if the Camera instance cannot be cloned
         */
        public Camera build() {
            if (this.camera.p0 == null) {
                throw new MissingResourceException(MISSING_DATA_ERROR, CAMERA_CLASS_NAME, POSITION_MISSING);
            }
            if (this.camera.vTo == null || this.camera.vUp == null || this.camera.vRight == null) {
                throw new MissingResourceException(MISSING_DATA_ERROR, CAMERA_CLASS_NAME, DIRECTION_MISSING);
            }
            if (this.camera.viewPlaneWidth == 0 || this.camera.viewPlaneHeight == 0) {
                throw new MissingResourceException(MISSING_DATA_ERROR, CAMERA_CLASS_NAME, VPDIMENSIONS_MISSING);
            }
            if (this.camera.viewPlaneDistance == 0) {
                throw new MissingResourceException(MISSING_DATA_ERROR, CAMERA_CLASS_NAME, VPDISTANCE_MISSING);
            }
            if (this.camera.imageWriter == null) {
                throw new MissingResourceException(MISSING_DATA_ERROR, CAMERA_CLASS_NAME, IMAGE_WRITER_MISSING);
            }
            if (this.camera.rayTracer == null) {
                throw new MissingResourceException(MISSING_DATA_ERROR, CAMERA_CLASS_NAME, RAY_TRACER_MISSING);
            }
            return (Camera) this.camera.clone();
        }
    }

}
