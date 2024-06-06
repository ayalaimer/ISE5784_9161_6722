package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * The Camera class represents a camera in 3D space with a location, direction, and view plane parameters.
 * It is used to construct rays through a view plane for ray tracing purposes.
 *
 * @version 1.0
 * @since 2024-06-06
 */
public class Camera implements Cloneable {
    private Point p0;
    private Vector vTo, vUp, vRight;
    private double viewPlaneWidth = 0.0, viewPlaneHeight = 0.0, viewPlaneDistance = 0.0;

    /**
     * Constructs a new Camera with a specified location, viewing direction, and up direction.
     *
     * @param p0 the location of the camera
     * @param vTo the direction the camera is pointing
     * @param vUp the up direction relative to the camera's orientation
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        this.p0 = p0;
        this.vTo = vTo;
        this.vUp = vUp;
        vRight = vTo.crossProduct(vUp).normalize();
    }

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
     * @param j the column index of the pixel
     * @param i the row index of the pixel
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
     * @param width the width of the view plane
     * @param height the height of the view plane
     * @return the current Camera instance
     */
    public Camera setVpSize(int width, int height) {
        this.viewPlaneWidth = width;
        this.viewPlaneHeight = height;
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
     * The Builder class for constructing Camera instances.
     */
    public static class Builder {
        private static final String MISSING_DATA_ERROR = "Missing rendering data";
        private static final String CAMERA_CLASS_NAME = "Camera";
        private static final String POSITION_MISSING = "Camera position is missing";
        private static final String DIRECTION_MISSING = "Camera direction is missing";
        private static final String VPDIMENSIONS_MISSING = "View plane dimensions are missing";
        private static final String VPDISTANCE_MISSING = "View plane distance is missing";

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
         * @param width the width of the view plane
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
         * Builds and returns the Camera instance.
         *
         * @return the constructed Camera instance
         * @throws CloneNotSupportedException if the Camera instance cannot be cloned
         */
        public Camera build() throws CloneNotSupportedException {
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
            return (Camera) this.camera.clone();
        }
    }
}
