package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.MissingResourceException;

import static primitives.Util.isZero;

public class Camera implements Cloneable {
    private Point p0;
    private Vector vTo, vUp, vRight;
    private double viewPlaneWidth = 0.0, viewPlaneHeight = 0.0, viewPlaneDistance = 0.0;

    public Camera(Point p0, Vector vTo, Vector vUp) {
        this.p0 = p0;
        this.vTo = vTo;
        this.vUp = vUp;

        vRight = vTo.crossProduct(vUp).normalize();
    }

    private Camera() {
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Ray constructRay(int nX, int nY, int j, int i) {

        // Verify that nX and nY are not zero to avoid division by zero
        if (nY == 0 || nX == 0)
            throw new IllegalArgumentException("It is impossible to divide by 0");

        // Calculate the center point of the image plane (Pc) by moving from the camera location
        // along the viewing direction (vTo) by the specified distance
        Point Pc = p0.add(vTo.scale(viewPlaneDistance));

        // Calculate the width (Rx) and height (Ry) of a single pixel on the image plane
        double Rx = viewPlaneWidth / nX;
        double Ry = viewPlaneHeight / nY;

        // Initialize the point Pij to the center of the image plane (Pc)
        Point Pij = Pc;

        // Calculate the horizontal (Xj) and vertical (Yi) distances from the center to the pixel (j, i)
        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;

        // If Xj is not zero, move Pij horizontally by Xj along the right direction (vRight)
        if (!isZero(Xj)) {
            Pij = Pij.add(vRight.scale(Xj));
        }

        // If Yi is not zero, move Pij vertically by Yi along the up direction (vUp)
        if (!isZero(Yi)) {
            Pij = Pij.add(vUp.scale(Yi));
        }

        // Create and return a new Ray from the camera location (location) towards the calculated point (Pij)
        return new Ray(p0, Pij.subtract(p0));
    }

    public Camera setViewPlaneDistance(int distance) {
        this.viewPlaneDistance = distance;
        return this;
    }

    public Camera setVpSize(int width, int height) {
        this.viewPlaneWidth = width;
        this.viewPlaneHeight = height;
        return this;
    }

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

    public static class Builder {
        private static final String MISSING_DATA_ERROR = "Missing rendering data";
        private static final String CAMERA_CLASS_NAME = "Camera";
        private static final String POSITION_MISSING = "Camera position is missing";
        private static final String DIRECTION_MISSING = "Camera direction is missing";
        private static final String VPDIMENSIONS_MISSING = "View plane dimensions are missing";
        private static final String VPDISTANCE_MISSING = "View plane distance is missing";

        private final Camera camera;

        public Builder() {
            this.camera = new Camera();
        }
        public Builder(Camera myCamera) {
            this.camera = myCamera;
        }
        public Builder setLocation(Point p0) {
            if (p0 == null) {
                throw new IllegalArgumentException("The place cannot be null");
            }
            this.camera.p0 = p0;
            return this;
        }
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
        public Builder setVpSize(double width, double height) {
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("Height and Width have to be bigger than 0");
            }
            this.camera.viewPlaneWidth = width;
            this.camera.viewPlaneHeight = height;
            return this;
        }
        public Builder setVpDistance(double distance) {
            if (distance <= 0) {
                throw new IllegalArgumentException("The distance has to be bigger than 0");
            }
            this.camera.viewPlaneDistance = distance;
            return this;
        }
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
